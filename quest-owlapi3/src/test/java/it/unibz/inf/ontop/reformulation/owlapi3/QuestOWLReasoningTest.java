package it.unibz.inf.ontop.reformulation.owlapi3;

import static org.junit.Assert.*;
import static org.semanticweb.owlapi.apibinding.OWLFunctionalSyntaxFactory.Class;
import static org.semanticweb.owlapi.apibinding.OWLFunctionalSyntaxFactory.ObjectProperty;
import static org.semanticweb.owlapi.apibinding.OWLFunctionalSyntaxFactory.ObjectInverseOf;
import static org.semanticweb.owlapi.apibinding.OWLFunctionalSyntaxFactory.Declaration;
import static org.semanticweb.owlapi.apibinding.OWLFunctionalSyntaxFactory.Ontology;
import static org.semanticweb.owlapi.apibinding.OWLFunctionalSyntaxFactory.EquivalentClasses;
import static org.semanticweb.owlapi.apibinding.OWLFunctionalSyntaxFactory.EquivalentObjectProperties;

import org.junit.Before;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLFunctionalSyntaxFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectInverseOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.Node;

import it.unibz.inf.ontop.owlrefplatform.core.QuestConstants;
import it.unibz.inf.ontop.owlrefplatform.core.QuestPreferences;
import it.unibz.inf.ontop.owlrefplatform.owlapi.QuestOWL;
import it.unibz.inf.ontop.owlrefplatform.owlapi.QuestOWLConfiguration;
import it.unibz.inf.ontop.owlrefplatform.owlapi.QuestOWLFactory;

/**
 * Test implementation of OWL reasoner in QuestOWL.
 * {@link QuestOWL}
 */
public class QuestOWLReasoningTest {
	
	private QuestOWL reasoner;
	private OWLOntology ontology;
	private OWLOntologyManager manager;

	/*
	 * Create Objects that will be used in ontology assertions.
	 */
	String prefix = "http://www.example.org/";
	OWLClass cDirect = Class(IRI.create(prefix + "cDirect"));
	OWLClass cNotDirect = Class(IRI.create(prefix + "cNotDirect"));

	OWLClass cA = Class(IRI.create(prefix + "A"));
	OWLClass cB = Class(IRI.create(prefix + "B"));
	OWLClass cC = Class(IRI.create(prefix + "C"));
	OWLClass cH = Class(IRI.create(prefix + "H"));

	
	OWLObjectProperty oD = ObjectProperty(IRI.create(prefix + "D"));
	OWLObjectInverseOf oDInv = ObjectInverseOf(oD);
	OWLObjectProperty oDirect = ObjectProperty(IRI.create(prefix + "oDirect"));
	OWLObjectInverseOf oDirectInv = ObjectInverseOf(oDirect);
	OWLObjectProperty oF = ObjectProperty(IRI.create(prefix + "F"));
	OWLObjectInverseOf oFInv = ObjectInverseOf(oF);
	OWLObjectProperty oNotDirect = ObjectProperty(IRI.create(prefix + "oNotDirect"));
	OWLObjectInverseOf oNotDirectInv = ObjectInverseOf(oNotDirect);
	OWLObjectProperty oG = ObjectProperty(IRI.create(prefix + "G"));
	OWLObjectInverseOf oGInv = ObjectInverseOf(oG);
	OWLObjectProperty oI = ObjectProperty(IRI.create(prefix + "I"));
	OWLObjectInverseOf oIInv = ObjectInverseOf(oI);


	@Before
	public void setUp() throws Exception {
		manager = OWLManager.createOWLOntologyManager();
		ontology = Ontology(manager, 
				Declaration(cDirect),
				Declaration(cNotDirect),
				Declaration(cA),
				Declaration(cB),
				Declaration(cC),
				Declaration(cH),

				
				Declaration(oD),
				Declaration(oDirect),
				Declaration(oF),
				Declaration(oG),
				Declaration(oI)
				);
	}
	
	
	private void startReasoner() {
		QuestPreferences p = new QuestPreferences();
		p.setCurrentValueOf(QuestPreferences.ABOX_MODE, QuestConstants.CLASSIC);

		QuestOWLFactory factory = new QuestOWLFactory();
        QuestOWLConfiguration config = QuestOWLConfiguration.builder().preferences(p).build();
        reasoner = factory.createReasoner(ontology, config);
	}
	
	/*
	 * CLASS EXPRESIONS
	 */
	/**
	 * TODO: Add test for invalid types of class expressions.
	 */
	
	@Test
	public void testGetSubClasses() throws OWLOntologyCreationException {

		//cDirect 	 [Sub] 	 cB
		//cA 		 [Equiv] cDirect
		//cNotDirect [Sub] 	 cDirect
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cDirect,cB));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cDirect,cA));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cA, cDirect));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cNotDirect,cDirect));

		startReasoner();
		
		//Get only direct subclass of cB: cDirect, cA
		NodeSet<OWLClass> subClasses = reasoner.getSubClasses(cB, true);
		assertTrue(subClasses.containsEntity(cDirect));
		assertTrue(subClasses.containsEntity(cA));
		assertFalse(subClasses.containsEntity(cNotDirect));
		assertFalse(subClasses.containsEntity(cB));

		
		//Get all subclass of cB: cDirect, cA, cNotDirect
		NodeSet<OWLClass> subNotDirectClasses = reasoner.getSubClasses(cB, false);
		assertTrue(subNotDirectClasses.containsEntity(cDirect));
		assertTrue(subClasses.containsEntity(cA));
		assertTrue(subNotDirectClasses.containsEntity(cNotDirect));
		assertFalse(subClasses.containsEntity(cB));

	} 
	
	@Test
	public void testGetSuperClasses() throws OWLOntologyCreationException {

		//cB 		[Sub] 	 cDirect
		//cA 		[Equiv]  cDirect
		//cDirect 	[Sub] 	 cNotDirect
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cB, cDirect));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cDirect,cA));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cA, cDirect));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cDirect, cNotDirect));

		startReasoner();
		
		//Get only direct superclass of cB: cDirect, cA
		NodeSet<OWLClass> superClasses = reasoner.getSuperClasses(cB, true);
		assertTrue(superClasses.containsEntity(cDirect));
		assertTrue(superClasses.containsEntity(cA));
		assertFalse(superClasses.containsEntity(cNotDirect));
		assertFalse(superClasses.containsEntity(cB));
		
		//Get all superclass of cB: cDirect, cA, cNotDirect
		NodeSet<OWLClass> superNotDirectClasses = reasoner.getSuperClasses(cB, false);
		assertTrue(superNotDirectClasses.containsEntity(cDirect));
		assertTrue(superClasses.containsEntity(cA));
		assertTrue(superNotDirectClasses.containsEntity(cNotDirect));
		assertFalse(superClasses.containsEntity(cB));

	} 
	
	@Test
	public void testGetEquivClasses() throws OWLOntologyCreationException {

		// cA [Equiv] 	cB
		// cA [Sub]		cC
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cA,cB));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cB, cA));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cA, cC));
		
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cA,cH));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubClassOf(cH, cA));

		startReasoner();
		
		//Get equivalent classes of cA: cA,cB
		Node<OWLClass> equivClasses = reasoner.getEquivalentClasses(cA);
		assertTrue(equivClasses.contains(cA));
		assertTrue(equivClasses.contains(cB));
		assertFalse(equivClasses.contains(cC));
		
		//Get equivalent classes of cB: cA, cB
		equivClasses = reasoner.getEquivalentClasses(cB);
		assertTrue(equivClasses.contains(cA));
		assertTrue(equivClasses.contains(cB));
		assertFalse(equivClasses.contains(cC));
		
		assertTrue(reasoner.isEntailed(EquivalentClasses(cA,cB,cH)));
		assertFalse(reasoner.isEntailed(EquivalentClasses(cA,cB,cC)));

	} 


	/*
	 * OBJECT PROPERTY EXPRESIONS
	 */
	@Test
	public void testGetSubObjProperty(){
		
		//oNotDirect [Sub] oF [Equiv] oDirect [Sub] oD
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oDirect, oD));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oF, oDirect));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oDirect, oF));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oNotDirect, oF));
		
		startReasoner();
		
		// Direct sub object properties of oD: oDirect, oF
		NodeSet<OWLObjectPropertyExpression> subClasses = reasoner.getSubObjectProperties(oD, true);
		assertTrue(subClasses.containsEntity(oDirect));
		assertTrue(subClasses.containsEntity(oF));
		assertFalse(subClasses.containsEntity(oD));
		assertFalse(subClasses.containsEntity(oNotDirect));
		// -> check that for inverse properties it does not hold
		assertFalse(subClasses.containsEntity(oDirectInv));
		assertFalse(subClasses.containsEntity(oFInv));
		assertFalse(subClasses.containsEntity(oDInv));
		assertFalse(subClasses.containsEntity(oNotDirectInv));


		// Sub object properties of inverse of oD: oDirectInv, oFInv, oNotDirectInv
		subClasses = reasoner.getSubObjectProperties(oDInv, true);
		assertTrue(subClasses.containsEntity(oDirectInv));
		assertTrue(subClasses.containsEntity(oFInv));
		assertFalse(subClasses.containsEntity(oNotDirectInv));
		assertFalse(subClasses.containsEntity(oDInv));
		// -> check that for the simple properties it does not hold
		assertFalse(subClasses.containsEntity(oDirect));
		assertFalse(subClasses.containsEntity(oF));
		assertFalse(subClasses.containsEntity(oD));
		assertFalse(subClasses.containsEntity(oNotDirect));
	}
	
	@Test
	public void testGetSuperObjProperty(){
		
		// oD [Sub] oDirect 	[Equiv] oF
		// oF [Sub] oNotDirect
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oD, oDirect));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oDirect, oF));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oF, oDirect));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oF, oNotDirect));
		
		startReasoner();
		
		// Direct super object properties of oD: oDirect, oF
		NodeSet<OWLObjectPropertyExpression> superClasses = reasoner.getSuperObjectProperties(oD, true);
		assertTrue(superClasses.containsEntity(oDirect));
		assertTrue(superClasses.containsEntity(oF));
		assertFalse(superClasses.containsEntity(oD));
		assertFalse(superClasses.containsEntity(oNotDirect));
		// -> check that for inverse properties it does not hold
		assertFalse(superClasses.containsEntity(oDirectInv));
		assertFalse(superClasses.containsEntity(oFInv));
		assertFalse(superClasses.containsEntity(oDInv));
		assertFalse(superClasses.containsEntity(oNotDirectInv));


		// Super object properties of inverse of oD: oDirectInv, oFInv, oNotDirectInv
		superClasses = reasoner.getSuperObjectProperties(oDInv, true);
		assertTrue(superClasses.containsEntity(oDirectInv));
		assertTrue(superClasses.containsEntity(oFInv));
		assertFalse(superClasses.containsEntity(oNotDirectInv));
		assertFalse(superClasses.containsEntity(oDInv));
		// -> check that for the simple properties it does not hold
		assertFalse(superClasses.containsEntity(oDirect));
		assertFalse(superClasses.containsEntity(oF));
		assertFalse(superClasses.containsEntity(oD));
		assertFalse(superClasses.containsEntity(oNotDirect));
	}
	
	
	@Test
	public void testGetEquivObjProperty() throws OWLOntologyCreationException {

		// oD [Equiv] 	oF
		// oD [Sub]		oG
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oD,oF));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oF,oD));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oD,oG));
		
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oD,oI));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oI,oD));

		startReasoner();
		
		//Get equivalent object properties of oD: oD,oF
		Node<OWLObjectPropertyExpression> equivObjProperties = reasoner.getEquivalentObjectProperties(oD);
		assertTrue(equivObjProperties.contains(oD));
		assertTrue(equivObjProperties.contains(oF));
		assertFalse(equivObjProperties.contains(oG));
		assertFalse(equivObjProperties.contains(oGInv));
		assertFalse(equivObjProperties.contains(oDInv));
		assertFalse(equivObjProperties.contains(oFInv));
		
		//Get equivalent object properties of inverse of oF: oFInv, oDInv
		equivObjProperties = reasoner.getEquivalentObjectProperties(oFInv);
		assertTrue(equivObjProperties.contains(oDInv));
		assertTrue(equivObjProperties.contains(oFInv));
		assertFalse(equivObjProperties.contains(oG));
		assertFalse(equivObjProperties.contains(oGInv));
		assertFalse(equivObjProperties.contains(oD));
		assertFalse(equivObjProperties.contains(oF));
		
		assertTrue(reasoner.isEntailed(EquivalentObjectProperties(oI,oD,oF)));
		assertFalse(reasoner.isEntailed(EquivalentObjectProperties(oI,oG,oF)));


	} 
	
	@Test
	public void testGetInverseObjectPropeties() {
		// oD [Equiv] 	oF
		// oD [Sub]		oG
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oD,oF));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oF,oD));
		manager.addAxiom(ontology, OWLFunctionalSyntaxFactory.SubObjectPropertyOf(oD,oG));

		startReasoner();
		
		//Get inverse object properties of oD: oDInv,oFInv
		Node<OWLObjectPropertyExpression> invObjProperties = reasoner.getInverseObjectProperties(oD);
		assertFalse(invObjProperties.contains(oD));
		assertFalse(invObjProperties.contains(oF));
		assertFalse(invObjProperties.contains(oG));
		assertFalse(invObjProperties.contains(oGInv));
		assertTrue(invObjProperties.contains(oDInv));
		assertTrue(invObjProperties.contains(oFInv));
		
		//Get inverse object properties of inverse of oF: oF, oD
		invObjProperties = reasoner.getInverseObjectProperties(oFInv);
		assertFalse(invObjProperties.contains(oDInv));
		assertFalse(invObjProperties.contains(oFInv));
		assertFalse(invObjProperties.contains(oG));
		assertFalse(invObjProperties.contains(oGInv));
		assertTrue(invObjProperties.contains(oD));
		assertTrue(invObjProperties.contains(oF));
	}
}
