//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-792 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.12 at 06:11:20 PM MEZ 
//


package org.deegree.feature.persistence.postgis.jaxbconfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * Definition of a feature-valued property of a
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
 *         &lt;element ref="{http://www.deegree.org/feature/featuretype}FeaturePropertyMapping"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "featurePropertyMapping"
})
public class FeaturePropertyDecl
    extends AbstractPropertyDecl
{

    @XmlElement(name = "FeaturePropertyMapping", required = true)
    protected FeaturePropertyMappingType featurePropertyMapping;
    @XmlAttribute
    protected QName type;

    /**
     * Gets the value of the featurePropertyMapping property.
     * 
     * @return
     *     possible object is
     *     {@link FeaturePropertyMappingType }
     *     
     */
    public FeaturePropertyMappingType getFeaturePropertyMapping() {
        return featurePropertyMapping;
    }

    /**
     * Sets the value of the featurePropertyMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeaturePropertyMappingType }
     *     
     */
    public void setFeaturePropertyMapping(FeaturePropertyMappingType value) {
        this.featurePropertyMapping = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setType(QName value) {
        this.type = value;
    }

}
