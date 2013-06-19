package org.apromore.canoniser.bpmn;

// Java 2 Standard packages
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// Local packages
import org.apromore.anf.AnnotationsType;
import org.apromore.canoniser.DefaultAbstractCanoniser;
import org.apromore.canoniser.bpmn.anf.AnfAnnotationsType;
import org.apromore.canoniser.bpmn.bpmn.BpmnDefinitions;
import org.apromore.canoniser.bpmn.cpf.CpfCanonicalProcessType;
import org.apromore.canoniser.exception.CanoniserException;
import org.apromore.canoniser.result.CanoniserMetadataResult;
import org.apromore.cpf.CanonicalProcessType;
import org.apromore.plugin.PluginRequest;
import org.apromore.plugin.PluginResult;
import org.apromore.plugin.PluginResultImpl;
import org.omg.spec.bpmn._20100524.di.BPMNDiagram;
import org.omg.spec.bpmn._20100524.di.BPMNPlane;
import org.springframework.stereotype.Component;

/**
 * Canoniser for Business Process Model and Notation (BPMN) 2.0.
 *
 * @see <a href="http://www.bpmn.org">Object Management Group BPMN site</a>
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 * @since 0.4
 */
@Component("bpmnCanoniser")
public class BPMN20Canoniser extends DefaultAbstractCanoniser {

    /** Generator of identifiers for @uri scoped across all generated CPF and ANF documents. */
    private final IdFactory linkUriFactory = new IdFactory();

    // Methods implementing Canoniser interface

    /** {@inheritDoc} */
    @Override
    public PluginResult canonise(final InputStream                bpmnInput,
                                 final List<AnnotationsType>      annotationFormat,
                                 final List<CanonicalProcessType> canonicalFormat,
                                 final PluginRequest request) throws CanoniserException {

        try {
            BpmnDefinitions definitions = BpmnDefinitions.newInstance(bpmnInput, false);

            // Create the CPF
            CanonicalProcessType cpf = new CpfCanonicalProcessType(definitions);
            cpf.setUri(linkUriFactory.newId(null));
            canonicalFormat.add(cpf);

            // Create the ANF
            if (definitions.getBPMNDiagram().size() == 0) {
                final AnnotationsType anf = new AnfAnnotationsType(definitions, null);
                anf.setUri(cpf.getUri());
                annotationFormat.add(anf);
            }
            for (BPMNDiagram diagram : definitions.getBPMNDiagram()) {
                final AnnotationsType anf = new AnfAnnotationsType(definitions, diagram);
                anf.setUri(cpf.getUri());
                annotationFormat.add(anf);
            }

            // Return a result
            PluginResultImpl result = new PluginResultImpl();
            //result.addPluginMessage("BPMN 2.0 canonised OK");
            return result;

        } catch (Exception e) {
            throw new CanoniserException("Could not canonise to BPMN stream", e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * Each empty document generated by this method will have a UUID as its target namespace, such that
     * BPMN QName identifiers will be unique even for short IDs within the document.
     * In other words, IDs need not be universally unique, only unique within the document.
     */
    @Override
    public PluginResult createInitialNativeFormat(final OutputStream bpmnOutput,
                                                  final String processName,
                                                  final String processVersion,
                                                  final String processAuthor,
                                                  final Date processCreated,
                                                  final PluginRequest request) {

        PluginResultImpl result = newPluginResult();
        try {
            // Construct an empty BPMN model
            BpmnDefinitions definitions = new BpmnDefinitions();
            String id = UUID.randomUUID().toString();
            definitions.setId(id);
            definitions.setName(processName);
            definitions.setExporter(getClass().getCanonicalName());
            definitions.setExporterVersion("1.0");
            definitions.setTargetNamespace("http://apromore.org/" + id + "#");

            BPMNDiagram diagram = new BPMNDiagram();
            diagram.setId(UUID.randomUUID().toString());

            BPMNPlane plane = new BPMNPlane();
            plane.setId(id);
            diagram.setBPMNPlane(plane);
            definitions.getBPMNDiagram().add(diagram);

            // Serialize out the BPMN model
            definitions.marshal(bpmnOutput, false);

        } catch (Exception e) {
            result.addPluginMessage("Failed to create empty BPMN model: {0}", e.getMessage());
        }
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public PluginResult deCanonise(final CanonicalProcessType canonicalFormat,
                                   final AnnotationsType      annotationFormat,
                                   final OutputStream         bpmnOutput,
                                   final PluginRequest        request) throws CanoniserException {

        try {
            BpmnDefinitions.newInstance(CpfCanonicalProcessType.remarshal(canonicalFormat), annotationFormat)
                           .marshal(bpmnOutput, false);

            // Return a result
            return new PluginResultImpl();

        } catch (Exception e) {
            throw new CanoniserException("Could not decanonise from BPMN stream", e);
        }
    }

    /**
     * {@inheritDoc}
     * @return a result expressing just the name of the BPMN process, or <code>null</code> if any exception occurs internally
     */
    @Override
    public CanoniserMetadataResult readMetaData(final InputStream bpmnInput, final PluginRequest request) {
        try {
            BpmnDefinitions definitions = BpmnDefinitions.newInstance(bpmnInput, false);

            // Fill in the metadata
            CanoniserMetadataResult result = new CanoniserMetadataResult();
            result.setProcessName(definitions.getName());

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    // Miscellaneous method - TODO - find a better home for it

    /**
     * This method centralizes the policy of filling in absent names with a zero-length
     * string in cases where CPF requires a name which is optional in BPMN.
     *
     * @param name  a name which might be absent in the source language
     * @return <code>name</code> if present, otherwise <code>""</code> (the zero-length string).
     */
    public static String requiredName(final String name) {
        return (name == null ? "" : name);
    }
}
