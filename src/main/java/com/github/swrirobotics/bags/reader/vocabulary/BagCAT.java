package com.github.swrirobotics.bags.reader.vocabulary;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;

import java.io.File;
import java.io.FileWriter;

/**
 * Constants for the W3C Data Catalog Vocabulary.
 *
 * @see <a href="https://www.w3.org/TR/vocab-dcat/">Data Catalog Vocabulary</a>
 *
 *
 *
 *
 *
 * https://github.com/swri-robotics/bag-database
 *
 * This Project provides a BAG-Database like the discontinued "BAG Bunker" (https://github.com/bosch-ros-pkg/bagbunker).
 *
 *
 *
 *
 *
 */
public class BagCAT {

    public static Model loadCatalogData() {

        Model model = ModelFactory.createDefaultModel();

        File f = new File( "./out/_ciw_metastore");

        System.out.println(">>> CATALOG folder: " + f.getAbsolutePath() );

        if ( f.exists() ) {
            File[] modelFragments = f.listFiles( new TTLFileFilter() );

            for( File ttF : modelFragments ) {
                System.out.println(">>> load model fragment : " + ttF.getAbsolutePath() );
                model.read( "file://" + ttF.getAbsolutePath() );
            }

            InfModel inf = ModelFactory.createRDFSModel( model );

            String queryString = "SELECT ?x ?y ?z WHERE { ?x ?y ?z }";

            Query query = QueryFactory.create(queryString);
            QueryExecution qexec = QueryExecutionFactory.create(query, inf );
            try {
                ResultSet results = qexec.execSelect();
                while(results.hasNext()) {
                    QuerySolution soln = results.nextSolution();
                    System.out.println(soln.toString());
                }
            } finally {
                qexec.close();
            }

        }
        else {

        }

        return model;

    }

    synchronized public static void persistModel( Model m ) throws Exception {

        File f = new File( "out/_ciw_metastore");

        if ( f.exists() ) {


        }
        else {

            f.mkdirs();

        }



        // WRITE RDF GRAPH to ingest stage
        File kbSubgraph = new File( f.getAbsolutePath() + "/BagCAT_" + System.currentTimeMillis() + ".ttl" );
        FileWriter fw = new FileWriter( kbSubgraph );

        String syntax3 = "TURTLE"; // also try "N-TRIPLE" and "TURTLE"
        m.write(fw, syntax3);
        fw.close();

        // WRITE CLOUDERA NAVIGATOR METADATA ... (optionally)

        /**
         * https://www.cloudera.com/documentation/enterprise/latest/topics/navigator_search.html#concept_mnd_f5z_vl__section_msx_c23_rt
         */

        // https://www.cloudera.com/documentation/enterprise/latest/topics/cn_nav_hive-hdfs_api.html#tag_hive_hdfs_json_apis



        // TRANSFER TO CLUSTER (optionally).



    }

    private static final Model m = ModelFactory.createDefaultModel();

    public static final String NS = "http://www.w3.org/ns/bagcat#";
    public static final Resource NAMESPACE = m.createResource(NS);

    /**
     * Returns the URI for this schema
     * @return URI
     */
    public static String getURI() {
        return NS;
    }

    // Classes
    public static final Resource Catalog = m.createResource(NS + "Catalog");
    public static final Resource CatalogRecord = m.createResource(NS + "CatalogRecord");
    public static final Resource Dataset = m.createResource(NS + "Dataset");
    public static final Resource Distribution = m.createResource(NS + "Distribution");

    // Properties
    public static final Property accessURL = m.createProperty(NS + "accessURL");
    public static final Property byteSize = m.createProperty(NS + "byteSize");
    public static final Property contactPoint = m.createProperty(NS + "contactPoint");
    public static final Property dataset = m.createProperty(NS + "dataset");
    public static final Property distribution = m.createProperty(NS + "distribution");
    public static final Property downloadURL = m.createProperty(NS + "downloadURL");
    public static final Property keyword = m.createProperty(NS + "keyword");
    public static final Property landingPage = m.createProperty(NS + "landingPage");
    public static final Property mediaType = m.createProperty(NS + "mediaType");
    public static final Property record = m.createProperty(NS + "record");
    public static final Property theme = m.createProperty(NS + "theme");
    public static final Property themeTaxonomy = m.createProperty(NS + "themeTaxonomy");


    // Classes
    public static final Resource BagFile = m.createResource(NS + "BagFile");
    public static final Resource BagFileTopic = m.createResource(NS + "BagFileTopic");
    public static final Resource BagFileType = m.createResource(NS + "BagFileType");

    // Properties
    public static final Property name = m.createProperty(NS + "name");
    public static final Property filename = m.createProperty(NS + "filename");
    public static final Property version = m.createProperty(NS + "version");
    public static final Property compression = m.createProperty(NS + "compression");
    public static final Property duration = m.createProperty(NS + "duration");
    public static final Property start = m.createProperty(NS + "start");
    public static final Property end = m.createProperty(NS + "end");
    public static final Property sizeInMB = m.createProperty(NS + "sizeInMB");
    public static final Property nrOfMessages = m.createProperty(NS + "nrOfMessages");

    public static final Property availableInBagCatalog = m.createProperty(NS + "availableInBagCatalog");
    public static final Property availableInBagFile = m.createProperty(NS + "topicAvailableInBagFile");

    public static final Property topicName = m.createProperty(NS + "topicName");
    public static final Property packageName = m.createProperty(NS + "packageName");
    public static final Property md5 = m.createProperty(NS + "md5");
    public static final Property nrOfConnections = m.createProperty(NS + "nrOfConnections");

    public static final Property messageType = m.createProperty(NS + "messageType");





}