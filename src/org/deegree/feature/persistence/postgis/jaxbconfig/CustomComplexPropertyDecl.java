//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-792 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.12 at 06:11:20 PM MEZ 
//


package org.deegree.feature.persistence.postgis.jaxbconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Definition of a complex-valued property of a
 *           feature type.
 * 
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.deegree.org/feature/featuretype}AbstractPropertyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.deegree.org/feature/featuretype}CustomPropertyMapping"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customPropertyMapping"
})
public class CustomComplexPropertyDecl
    extends AbstractPropertyDecl
{

    @XmlElement(name = "CustomPropertyMapping", required = true)
    protected CustomPropertyMappingType customPropertyMapping;

    /**
     * Gets the value of the customPropertyMapping property.
     * 
     * @return
     *     possible object is
     *     {@link CustomPropertyMappingType }
     *     
     */
    public CustomPropertyMappingType getCustomPropertyMapping() {
        return customPropertyMapping;
    }

    /**
     * Sets the value of the customPropertyMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomPropertyMappingType }
     *     
     */
    public void setCustomPropertyMapping(CustomPropertyMappingType value) {
        this.customPropertyMapping = value;
    }

}
