/*
 * Copyright © 2009-2015 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.04.15 at 11:16:00 AM EST 
//

package epml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for typeGraphics complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="typeGraphics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="position" type="{http://www.epml.de}typePosition" minOccurs="0"/>
 *         &lt;element name="fill" type="{http://www.epml.de}typeFill" minOccurs="0"/>
 *         &lt;element name="line" type="{http://www.epml.de}typeLine" minOccurs="0"/>
 *         &lt;element name="font" type="{http://www.epml.de}typeFont" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeGraphics", propOrder = { "position", "fill", "line",
		"font" })
public class TypeGraphics {

	protected TypePosition position;
	protected TypeFill fill;
	protected TypeLine line;
	protected TypeFont font;

	/**
	 * Gets the value of the position property.
	 * 
	 * @return possible object is {@link TypePosition }
	 * 
	 */
	public TypePosition getPosition() {
		return position;
	}

	/**
	 * Sets the value of the position property.
	 * 
	 * @param value
	 *            allowed object is {@link TypePosition }
	 * 
	 */
	public void setPosition(TypePosition value) {
		this.position = value;
	}

	/**
	 * Gets the value of the fill property.
	 * 
	 * @return possible object is {@link TypeFill }
	 * 
	 */
	public TypeFill getFill() {
		return fill;
	}

	/**
	 * Sets the value of the fill property.
	 * 
	 * @param value
	 *            allowed object is {@link TypeFill }
	 * 
	 */
	public void setFill(TypeFill value) {
		this.fill = value;
	}

	/**
	 * Gets the value of the line property.
	 * 
	 * @return possible object is {@link TypeLine }
	 * 
	 */
	public TypeLine getLine() {
		return line;
	}

	/**
	 * Sets the value of the line property.
	 * 
	 * @param value
	 *            allowed object is {@link TypeLine }
	 * 
	 */
	public void setLine(TypeLine value) {
		this.line = value;
	}

	/**
	 * Gets the value of the font property.
	 * 
	 * @return possible object is {@link TypeFont }
	 * 
	 */
	public TypeFont getFont() {
		return font;
	}

	/**
	 * Sets the value of the font property.
	 * 
	 * @param value
	 *            allowed object is {@link TypeFont }
	 * 
	 */
	public void setFont(TypeFont value) {
		this.font = value;
	}

}
