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
 * <p>Java class for MeasurePropertyMappingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MeasurePropertyMappingType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.deegree.org/feature/featuretype}PropertyMappingType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.deegree.org/feature/featuretype}DBColumn"/>
 *         &lt;element ref="{http://www.deegree.org/feature/featuretype}PropertyTable"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeasurePropertyMappingType", propOrder = {
    "dbColumn",
    "propertyTable"
})
public class MeasurePropertyMappingType
    extends PropertyMappingType
{

    @XmlElement(name = "DBColumn")
    protected DBColumn dbColumn;
    @XmlElement(name = "PropertyTable")
    protected PropertyTable propertyTable;

    /**
     * Gets the value of the dbColumn property.
     * 
     * @return
     *     possible object is
     *     {@link DBColumn }
     *     
     */
    public DBColumn getDBColumn() {
        return dbColumn;
    }

    /**
     * Sets the value of the dbColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link DBColumn }
     *     
     */
    public void setDBColumn(DBColumn value) {
        this.dbColumn = value;
    }

    /**
     * Gets the value of the propertyTable property.
     * 
     * @return
     *     possible object is
     *     {@link PropertyTable }
     *     
     */
    public PropertyTable getPropertyTable() {
        return propertyTable;
    }

    /**
     * Sets the value of the propertyTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyTable }
     *     
     */
    public void setPropertyTable(PropertyTable value) {
        this.propertyTable = value;
    }

}
