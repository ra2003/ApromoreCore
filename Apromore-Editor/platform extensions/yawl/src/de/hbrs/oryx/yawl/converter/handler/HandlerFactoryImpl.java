/**
 * Copyright (c) 2011-2012 Felix Mannhardt, felix.mannhardt@smail.wir.h-brs.de
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See: http://www.gnu.org/licenses/lgpl-3.0
 *
 */
package de.hbrs.oryx.yawl.converter.handler;

import de.hbrs.oryx.yawl.converter.context.OryxConversionContext;
import de.hbrs.oryx.yawl.converter.context.YAWLConversionContext;
import de.hbrs.oryx.yawl.converter.handler.oryx.*;
import de.hbrs.oryx.yawl.converter.handler.yawl.SpecificationHandler;
import de.hbrs.oryx.yawl.converter.handler.yawl.YAWLHandler;
import de.hbrs.oryx.yawl.converter.handler.yawl.decomposition.NetHandler;
import de.hbrs.oryx.yawl.converter.handler.yawl.decomposition.RootNetHandler;
import de.hbrs.oryx.yawl.converter.handler.yawl.element.*;
import de.hbrs.oryx.yawl.converter.handler.yawl.flow.FlowHandler;
import org.oryxeditor.server.diagram.basic.BasicDiagram;
import org.oryxeditor.server.diagram.basic.BasicEdge;
import org.oryxeditor.server.diagram.basic.BasicShape;
import org.yawlfoundation.yawl.elements.*;

/**
 * Default conversion strategy of YAWL workflows to Oryx diagrams and Oryx
 * diagrams to YAWL workflows.
 *
 * @author Felix Mannhardt (Bonn-Rhein-Sieg University of Applied Sciences)
 */
public class HandlerFactoryImpl implements HandlerFactory {

    private YAWLConversionContext yawlContext;
    private OryxConversionContext oryxContext;

    public HandlerFactoryImpl(YAWLConversionContext yawlContext, OryxConversionContext oryxContext) {
        super();
        yawlContext.setHandlerFactory(this);
        oryxContext.setHandlerFactory(this);
        this.yawlContext = yawlContext;
        this.oryxContext = oryxContext;
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * de.hbrs.oryx.yawl.converter.handler.HandlerFactory#createYAWLConverter
      * (org.yawlfoundation.yawl.elements.YSpecification)
      */
    @Override
    public YAWLHandler createYAWLConverter(YSpecification ySpec) {
        return new SpecificationHandler(yawlContext, ySpec);
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * de.hbrs.oryx.yawl.converter.handler.YAWLHandlerFactory#createConverter
      * (org.yawlfoundation.yawl.elements.YDecomposition)
      */
    @Override
    public YAWLHandler createYAWLConverter(YDecomposition decomposition) {

        if (decomposition instanceof YNet) {

            if (isRootNet(decomposition)) {

                return new RootNetHandler(yawlContext, (YNet) decomposition);

            } else {
                return new NetHandler(yawlContext, decomposition);
            }
        }

        // If everything else does not apply
        return new NotImplementedHandler();
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * de.hbrs.oryx.yawl.converter.handler.YAWLHandlerFactory#createConverter
      * (org.yawlfoundation.yawl.elements.YNetElement)
      */
    @Override
    public YAWLHandler createYAWLConverter(YNetElement netElement) {

        if (netElement instanceof YAtomicTask) {
            if (((YAtomicTask) netElement).isMultiInstance()) {
                return new MultiInstanceAtomicTaskHandler(yawlContext, (YAtomicTask) netElement);
            } else {
                return new AtomicTaskHandler(yawlContext, (YAtomicTask) netElement);
            }
        } else if (netElement instanceof YCompositeTask) {
            if (((YCompositeTask) netElement).isMultiInstance()) {
                return new MultiInstanceCompositeTaskHandler(yawlContext, (YCompositeTask) netElement);
            } else {
                return new CompositeTaskHandler(yawlContext, (YCompositeTask) netElement);
            }
        } else if (netElement instanceof YInputCondition) {
            return new InputConditionHandler(yawlContext, netElement);
        } else if (netElement instanceof YOutputCondition) {
            return new OutputConditionHandler(yawlContext, netElement);
        } else if (netElement instanceof YCondition) {
            return new ConditionHandler(yawlContext, netElement);
        }

        // If everything else does not apply
        return new NotImplementedHandler();
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * de.hbrs.oryx.yawl.converter.handler.YAWLHandlerFactory#createConverter
      * (org.yawlfoundation.yawl.elements.YFlow)
      */
    @Override
    public YAWLHandler createYAWLConverter(YFlow yFlow) {
        return new FlowHandler(yawlContext, yFlow);
    }

    private boolean isRootNet(YDecomposition decomposition) {
        return decomposition.getAttribute("isRootNet") != null && decomposition.getAttribute("isRootNet").equals("true");
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * de.hbrs.oryx.yawl.converter.handler.HandlerFactory#createOryxConverter
      * (org.oryxeditor.server.diagram.Diagram)
      */
    @Override
    public OryxHandler createOryxConverter(BasicDiagram diagramShape) {
        return new OryxDiagramHandler(oryxContext, diagramShape);
    }

    /*
      * (non-Javadoc)
      *
      * @see
      * de.hbrs.oryx.yawl.converter.handler.HandlerFactory#createOryxConverter
      * (org.oryxeditor.server.diagram.Shape)
      */
    @Override
    public OryxHandler createOryxConverter(BasicShape shape) {
        if (shape.getStencilId().equals("AtomicTask")) {
            return new OryxAtomicTaskHandler(oryxContext, shape);
        } else if (shape.getStencilId().equals("AtomicMultipleTask")) {
            return new OryxAtomicMultipleTaskHandler(oryxContext, shape);
        } else if (shape.getStencilId().equals("CompositeTask")) {
            return new OryxCompositeTaskHandler(oryxContext, shape);
        } else if (shape.getStencilId().equals("CompositeMultipleTask")) {
            return new OryxCompositeMultipleTaskHandler(oryxContext, shape);
        } else if (shape.getStencilId().equals("Condition")) {
            return new OryxConditionHandler(oryxContext, shape);
        } else if (shape.getStencilId().equals("InputCondition")) {
            return new OryxInputConditionHandler(oryxContext, shape);
        } else if (shape.getStencilId().equals("OutputCondition")) {
            return new OryxOutputConditionHandler(oryxContext, shape);
        } else if (shape.getStencilId().equals("Diagram")) {
            return new OryxNetHandler(oryxContext, shape);
        } else {
            return new NotImplementedHandler();
        }
    }

    @Override
    public OryxHandler createOryxConverter(BasicEdge flowShape, BasicShape netShape) {
        return new OryxFlowHandler(oryxContext, flowShape, netShape);
    }

}
