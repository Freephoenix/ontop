package it.unibz.inf.ontop.owlrefplatform.core.mappingprocessing;

import it.unibz.inf.ontop.model.OBDAException;
import it.unibz.inf.ontop.ontology.OntologyFactory;
import it.unibz.inf.ontop.ontology.OntologyVocabulary;
import it.unibz.inf.ontop.ontology.impl.OntologyFactoryImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TMappingExclusionConfigTest {

    @Test
    public void testParseFile() throws Exception {
        OntologyFactory factory = OntologyFactoryImpl.getInstance();
        TMappingExclusionConfig conf = TMappingExclusionConfig.parseFile("src/test/resources/tmappingExclusionConf/good.conf");
        OntologyVocabulary voc = factory.createVocabulary();
        // in the config
        assertTrue(conf.contains(voc.createClass("http://www.example.org/A")));
        // not in the config
        assertFalse(conf.contains(voc.createClass("http://wwww.example.org/B")));
        // wrong type
        assertFalse(conf.contains(voc.createObjectProperty("http://wwww.example.org/B")));
        // in the config
        assertTrue(conf.contains(voc.createObjectProperty("http://www.example.org/P")));
        // not in the config
        assertFalse(conf.contains(voc.createObjectProperty("http://wwww.example.org/Q")));
        // wrong type
        assertFalse(conf.contains(voc.createClass("http://wwww.example.org/P")));
    }

    // File not found
    @Test(expected = OBDAException.class)
    public void testNotExistingFile() throws Exception {
        TMappingExclusionConfig.parseFile("not_existing.conf");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testBadFile() throws Exception {
        TMappingExclusionConfig.parseFile("src/test/resources/tmappingExclusionConf/bad.conf");
    }
}