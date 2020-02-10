
package de.hpi.bpmn2_0.model.data_object;

/*-
 * #%L
 * Signavio Core Components
 * %%
 * Copyright (C) 2006 - 2020 Philipp Berger, Martin Czuchra, Gero Decker,
 * Ole Eckermann, Lutz Gericke,
 * Alexander Hold, Alexander Koglin, Oliver Kopp, Stefan Krumnow,
 * Matthias Kunze, Philipp Maschke, Falko Menge, Christoph Neijenhuis,
 * Hagen Overdick, Zhen Peng, Nicolas Peters, Kerstin Pfitzner, Daniel Polak,
 * Steffen Ryll, Kai Schlichting, Jan-Felix Schwarz, Daniel Taschik,
 * Willi Tscheschner, Björn Wagner, Sven Wagner-Boysen, Matthias Weidlich
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 * 
 * 
 * 
 * Ext JS (http://extjs.com/) is used under the terms of the Open Source LGPL 3.0
 * license.
 * The license and the source files can be found in our SVN repository at:
 * http://oryx-editor.googlecode.com/.
 * #L%
 */

import de.hpi.bpmn2_0.model.BaseElement;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for tOutputSet complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="tOutputSet">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.omg.org/bpmn20}tBaseElement">
 *       &lt;sequence>
 *         &lt;element name="dataOutputRefs" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="optionalOutputRefs" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="whileExecutingOutputRefs" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="inputSetRefs" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tOutputSet", propOrder = {
        "dataOutputRefs",
        "optionalOutputRefs",
        "whileExecutingOutputRefs",
        "inputSetRefs"
})
public class OutputSet
        extends BaseElement {

    @XmlIDREF
    @XmlElement(type = DataOutput.class)
    protected List<DataOutput> dataOutputRefs;
    @XmlIDREF
    @XmlElement(type = DataOutput.class)
    protected List<DataOutput> optionalOutputRefs;
    @XmlIDREF
    @XmlElement(type = DataOutput.class)
    protected List<DataOutput> whileExecutingOutputRefs;
    @XmlIDREF
    @XmlElement(type = InputSet.class)
    protected List<InputSet> inputSetRefs;
    @XmlAttribute
    protected String name;

    /**
     * Gets the value of the dataOutputRefs property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataOutputRefs property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataOutputRefs().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DataOutput}
     */
    public List<DataOutput> getDataOutputRefs() {
        if (dataOutputRefs == null) {
            dataOutputRefs = new ArrayList<DataOutput>();
        }
        return this.dataOutputRefs;
    }

    /**
     * Gets the value of the optionalOutputRefs property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the optionalOutputRefs property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptionalOutputRefs().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DataOutput}
     */
    public List<DataOutput> getOptionalOutputRefs() {
        if (optionalOutputRefs == null) {
            optionalOutputRefs = new ArrayList<DataOutput>();
        }
        return this.optionalOutputRefs;
    }

    /**
     * Gets the value of the whileExecutingOutputRefs property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the whileExecutingOutputRefs property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWhileExecutingOutputRefs().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DataOutput}
     */
    public List<DataOutput> getWhileExecutingOutputRefs() {
        if (whileExecutingOutputRefs == null) {
            whileExecutingOutputRefs = new ArrayList<DataOutput>();
        }
        return this.whileExecutingOutputRefs;
    }

    /**
     * Gets the value of the inputSetRefs property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inputSetRefs property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInputSetRefs().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link InputSet}
     */
    public List<InputSet> getInputSetRefs() {
        if (inputSetRefs == null) {
            inputSetRefs = new ArrayList<InputSet>();
        }
        return this.inputSetRefs;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

}
