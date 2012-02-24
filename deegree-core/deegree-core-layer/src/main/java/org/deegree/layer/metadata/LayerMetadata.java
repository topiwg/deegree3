//$HeadURL: svn+ssh://aschmitz@wald.intevation.org/deegree/deegree3/trunk/deegree-core/deegree-core-layer/src/main/java/org/deegree/layer/LayerMetadata.java $
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2010 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package org.deegree.layer.metadata;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.deegree.commons.utils.DoublePair;
import org.deegree.feature.types.FeatureType;
import org.deegree.geometry.metadata.SpatialMetadata;
import org.deegree.layer.dims.Dimension;
import org.deegree.protocol.ows.metadata.Description;
import org.deegree.rendering.r2d.context.MapOptions;
import org.deegree.style.se.unevaluated.Style;

/**
 * 
 * @author <a href="mailto:schmitz@lat-lon.de">Andreas Schmitz</a>
 * @author last edited by: $Author: aschmitz $
 * 
 * @version $Revision: 31393 $, $Date: 2011-08-01 20:19:40 +0200 (Mon, 01 Aug 2011) $
 */
public class LayerMetadata {

    private String name;

    private Description description;

    private String metadataId;

    private SpatialMetadata spatialMetadata;

    private DoublePair scaleDenominators = new DoublePair( NEGATIVE_INFINITY, POSITIVE_INFINITY );

    private boolean queryable = true;

    private List<FeatureType> featureTypes = new ArrayList<FeatureType>();

    private int cascaded;

    private Map<String, Dimension<?>> dimensions = new LinkedHashMap<String, Dimension<?>>();

    private Map<String, Style> styles = new LinkedHashMap<String, Style>(),
                            legendStyles = new LinkedHashMap<String, Style>();

    private MapOptions mapOptions;

    public LayerMetadata( String name, Description description, SpatialMetadata spatialMetadata ) {
        this.name = name;
        this.description = description;
        this.spatialMetadata = spatialMetadata;
    }

    public void setDescription( Description description ) {
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * @param scaleDenominators
     *            the scaleDenominators to set, SLD style
     */
    public void setScaleDenominators( DoublePair scaleDenominators ) {
        this.scaleDenominators = scaleDenominators;
    }

    /**
     * @return the scaleDenominators, SLD style
     */
    public DoublePair getScaleDenominators() {
        return scaleDenominators;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * @return the cascaded
     */
    public int getCascaded() {
        return cascaded;
    }

    /**
     * @param cascaded
     *            the cascaded to set
     */
    public void setCascaded( int cascaded ) {
        this.cascaded = cascaded;
    }

    /**
     * @return the queryable
     */
    public boolean isQueryable() {
        return queryable;
    }

    /**
     * @param queryable
     *            the queryable to set
     */
    public void setQueryable( boolean queryable ) {
        this.queryable = queryable;
    }

    /**
     * @return the spatialMetadata
     */
    public SpatialMetadata getSpatialMetadata() {
        return spatialMetadata;
    }

    /**
     * @param spatialMetadata
     *            the spatialMetadata to set
     */
    public void setSpatialMetadata( SpatialMetadata spatialMetadata ) {
        this.spatialMetadata = spatialMetadata;
    }

    /**
     * Copies any fields from md which are currently not set (applies to description and spatial metadata only).
     * 
     * @param md
     */
    public void merge( LayerMetadata md ) {
        if ( description == null ) {
            description = md.getDescription();
        } else {
            if ( md.getDescription() != null ) {
                if ( description.getTitles() == null || description.getTitles().isEmpty() ) {
                    description.setTitles( md.getDescription().getTitles() );
                }
                if ( description.getAbstracts() == null || description.getAbstracts().isEmpty() ) {
                    description.setAbstracts( md.getDescription().getAbstracts() );
                }
                if ( description.getKeywords() == null || description.getKeywords().isEmpty() ) {
                    description.setKeywords( md.getDescription().getKeywords() );
                }
            }
        }
        if ( spatialMetadata == null ) {
            spatialMetadata = md.getSpatialMetadata();
        } else {
            if ( md.getSpatialMetadata() != null ) {
                if ( spatialMetadata.getCoordinateSystems() == null || spatialMetadata.getCoordinateSystems().isEmpty() ) {
                    spatialMetadata.setCoordinateSystems( md.getSpatialMetadata().getCoordinateSystems() );
                }
                if ( spatialMetadata.getEnvelope() == null ) {
                    spatialMetadata.setEnvelope( md.getSpatialMetadata().getEnvelope() );
                }
            }
        }
    }

    /**
     * @return the dimensions
     */
    public Map<String, Dimension<?>> getDimensions() {
        return dimensions;
    }

    /**
     * @param dimensions
     *            the dimensions to set
     */
    public void setDimensions( Map<String, Dimension<?>> dimensions ) {
        this.dimensions = dimensions;
    }

    /**
     * @return the styles
     */
    public Map<String, Style> getStyles() {
        return styles;
    }

    /**
     * @param styles
     *            the styles to set
     */
    public void setStyles( Map<String, Style> styles ) {
        this.styles = styles;
    }

    /**
     * @return the legendStyles
     */
    public Map<String, Style> getLegendStyles() {
        return legendStyles;
    }

    /**
     * @param legendStyles
     *            the legendStyles to set
     */
    public void setLegendStyles( Map<String, Style> legendStyles ) {
        this.legendStyles = legendStyles;
    }

    /**
     * @return the featureTypes
     */
    public List<FeatureType> getFeatureTypes() {
        return featureTypes;
    }

    /**
     * @param featureTypes
     *            the featureTypes to set
     */
    public void setFeatureTypes( List<FeatureType> featureTypes ) {
        this.featureTypes = featureTypes;
    }

    /**
     * @return the mapOptions
     */
    public MapOptions getMapOptions() {
        return mapOptions;
    }

    /**
     * @param mapOptions
     *            the mapOptions to set
     */
    public void setMapOptions( MapOptions mapOptions ) {
        this.mapOptions = mapOptions;
    }

    /**
     * @return the metadataId
     */
    public String getMetadataId() {
        return metadataId;
    }

    /**
     * @param metadataId
     *            the metadataId to set
     */
    public void setMetadataId( String metadataId ) {
        this.metadataId = metadataId;
    }

}
