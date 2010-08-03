//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2009 by:
 Department of Geography, University of Bonn
 and
 lat/lon GmbH

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
package org.deegree.tools.feature.persistence;

import static org.deegree.feature.persistence.FeatureStoreTransaction.IDGenMode.GENERATE_NEW;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.deegree.commons.jdbc.ConnectionManager;
import org.deegree.commons.xml.XMLParsingException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreException;
import org.deegree.feature.persistence.FeatureStoreManager;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.feature.persistence.FeatureStoreTransaction.IDGenMode;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.deegree.tools.CommandUtils;
import org.deegree.tools.annotations.Tool;
import org.deegree.tools.i18n.Messages;

/**
 * Imports feature datasets into a feature store.
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider </a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 */
@Tool("Imports feature datasets into a feature store")
public class FeatureStoreLoader {

    // command line parameters
    private static final String OPT_ACTION = "action";

    private static final String OPT_JDBC_CONFIG_FILE = "jdbcconfig";

    private static final String OPT_FS_CONFIG_FILE = "fsconfig";

    private static final String OPT_DATASET_FILE = "dataset";

    private static final String OPT_INPUT_FORMAT = "format";

    private enum Action {
        insert, stats
    }

    private static void initJDBCConnections( String jdbcConfigFile )
                            throws JAXBException, MalformedURLException {
        File f = new File( jdbcConfigFile );
        String connId = f.getName();
        int delimPos = connId.indexOf( '.' );
        connId = connId.substring( 0, delimPos );
        URL configURL = f.toURI().toURL();
        ConnectionManager.addConnection( configURL, connId );
    }

    private static FeatureStore initFeatureStore( String fsConfigFile )
                            throws MalformedURLException, FeatureStoreException {
        File f = new File( fsConfigFile );
        URL configURL = f.toURI().toURL();
        FeatureStore fs = FeatureStoreManager.create( configURL );
        fs.init();
        return fs;
    }

    private static void insert( FeatureStore fs, String datasetFile, GMLVersion gmlVersion )
                            throws XMLStreamException, FactoryConfigurationError, IOException, XMLParsingException,
                            UnknownCRSException, FeatureStoreException {

        File f = new File( datasetFile );
        URL url = f.toURI().toURL();
        GMLStreamReader gmlReader = GMLInputFactory.createGMLStreamReader( gmlVersion, url );
        FeatureCollection fc = gmlReader.readFeatureCollection();

        FeatureStoreTransaction ta = null;
        try {
            ta = fs.acquireTransaction();
            ta.performInsert( fc, GENERATE_NEW );
            System.out.println( "Insert succeeded. Committing transaction." );
            ta.commit();
        } catch ( Exception e ) {
            e.printStackTrace();
            System.err.println( "Error performing insert: " + e.getMessage() );
            if ( ta != null ) {
                ta.rollback();
            }
        }
    }

    /**
     * @param args
     * @throws FeatureStoreException
     * @throws JAXBException
     * @throws UnknownCRSException
     * @throws IOException
     * @throws FactoryConfigurationError
     * @throws XMLStreamException
     * @throws XMLParsingException
     */
    public static void main( String[] args )
                            throws FeatureStoreException, JAXBException, XMLParsingException, XMLStreamException,
                            FactoryConfigurationError, IOException, UnknownCRSException {

        Options options = initOptions();

        // for the moment, using the CLI API there is no way to respond to a help argument; see
        // https://issues.apache.org/jira/browse/CLI-179
        if ( args.length == 0 || ( args.length > 0 && ( args[0].contains( "help" ) || args[0].contains( "?" ) ) ) ) {
            printHelp( options );
        }

        try {
            new PosixParser().parse( options, args );

            Action action = null;
            try {
                action = Action.valueOf( options.getOption( OPT_ACTION ).getValue() );
            } catch ( IllegalArgumentException e ) {
                System.out.println( "Unknown action '" + options.getOption( OPT_ACTION ).getValue()
                                    + "'. Call with '-help' for displaying available actions." );
                System.exit( 0 );
            }

            String jdbcConfigFileName = options.getOption( OPT_JDBC_CONFIG_FILE ).getValue();
            String fsConfigFileName = options.getOption( OPT_FS_CONFIG_FILE ).getValue();

            GMLVersion format = null;
            try {
                format = GMLVersion.valueOf( options.getOption( OPT_INPUT_FORMAT ).getValue() );
            } catch ( IllegalArgumentException e ) {
                System.out.println( "Unknown input format '" + options.getOption( OPT_INPUT_FORMAT ).getValue()
                                    + "'. Call with '-help' for displaying valid formats." );
                System.exit( 0 );
            }

            String inputFileName = options.getOption( OPT_DATASET_FILE ).getValue();

            if ( jdbcConfigFileName != null ) {
                initJDBCConnections( jdbcConfigFileName );
            }
            FeatureStore fs = initFeatureStore( fsConfigFileName );

            switch ( action ) {
            case insert:
                insert( fs, inputFileName, format );
                break;
            case stats:
                System.out.println( "Stats..." );
                break;
            }
        } catch ( ParseException exp ) {
            System.err.println( Messages.getMessage( "TOOL_COMMANDLINE_ERROR", exp.getMessage() ) );
            // printHelp( options );
        }
    }

    private static Options initOptions() {

        Options opts = new Options();

        String actionsList = "";
        Action[] actions = Action.values();
        actionsList += actions[0].toString();
        for ( int i = 1; i < actions.length; i++ ) {
            actionsList += ", " + actions[i];
        }

        Option opt = new Option( OPT_ACTION, true, "action, one of: " + actionsList + "" );
        opt.setRequired( true );
        opts.addOption( opt );

        String formatsList = "";
        GMLVersion[] formats = GMLVersion.values();
        formatsList += formats[0].name();
        for ( int i = 1; i < formats.length; i++ ) {
            formatsList += ", " + formats[i].name();
        }

        opt = new Option( OPT_JDBC_CONFIG_FILE, true, "jdbc config filename" );
        opt.setRequired( false );
        opts.addOption( opt );

        opt = new Option( OPT_FS_CONFIG_FILE, true, "feature store config filename" );
        opt.setRequired( true );
        opts.addOption( opt );

        opt = new Option( OPT_INPUT_FORMAT, true, "input format, one of: " + formatsList + "" );
        opt.setRequired( true );
        opts.addOption( opt );

        opt = new Option( OPT_DATASET_FILE, true, "dataset filename" );
        opt.setRequired( true );
        opts.addOption( opt );

        CommandUtils.addDefaultOptions( opts );
        return opts;
    }

    private static void printHelp( Options options ) {
        CommandUtils.printHelp( options, FeatureStoreLoader.class.getSimpleName(), null, null );
    }
}
