/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.owlrefplatform.core.tboxprocessing;

import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.ontology.Axiom;
import it.unibz.krdb.obda.ontology.BasicClassDescription;
import it.unibz.krdb.obda.ontology.Description;
import it.unibz.krdb.obda.ontology.Ontology;
import it.unibz.krdb.obda.ontology.OntologyFactory;
import it.unibz.krdb.obda.ontology.Property;
import it.unibz.krdb.obda.ontology.PropertySomeRestriction;
import it.unibz.krdb.obda.ontology.impl.OntologyFactoryImpl;
import it.unibz.krdb.obda.owlrefplatform.core.dagjgrapht.Equivalences;
import it.unibz.krdb.obda.owlrefplatform.core.dagjgrapht.TBoxReasonerImpl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prune Ontology for redundant assertions based on dependencies
 */
public class SigmaTBoxOptimizer {

	private final TBoxReasonerImpl isa;
	private final TBoxReasonerImpl isaChain;
	private final TBoxReasonerImpl sigmaChain;

	private static final OntologyFactory fac = OntologyFactoryImpl.getInstance();
	private static final Logger	log	= LoggerFactory.getLogger(SigmaTBoxOptimizer.class);

	private Set<Predicate> vocabulary;
	private Ontology optimizedTBox = null;

	public SigmaTBoxOptimizer(Ontology isat, Ontology sigmat) {
		
		vocabulary = isat.getVocabulary();
		
		isa = new TBoxReasonerImpl(isat);
		//DAGImpl isaChainDAG = isa.getChainDAG();
		isaChain = TBoxReasonerImpl.getChainReasoner(isat);
		
		//TBoxReasonerImpl reasonerSigma = new TBoxReasonerImpl(sigmat);		
		//DAGImpl sigmaChainDAG =  reasonerSigma.getChainDAG();
		sigmaChain = TBoxReasonerImpl.getChainReasoner(sigmat);
	}

	public Ontology getReducedOntology() {
		if (optimizedTBox == null) {
			optimizedTBox = fac.createOntology("http://it.unibz.krdb/obda/auxontology");
			optimizedTBox.addEntities(vocabulary);

			log.debug("Starting semantic-reduction");

			for(Equivalences<Description> nodes: isa.getNodes()) {
				Description node = nodes.getRepresentative();
				for (Equivalences<Description> descendants : isa.getDescendants(node)) {
					Description descendant = descendants.getRepresentative();
					if (!descendant.equals(node)) 
						addToTBox(optimizedTBox, descendant, node);
				}
				
				for (Description equivalent : nodes) {
					if (!equivalent.equals(node)) {
						addToTBox(optimizedTBox, node, equivalent);					
						addToTBox(optimizedTBox, equivalent, node);
					}
				}
			}
		}
		return optimizedTBox;
	}

	private void addToTBox(Ontology rv, Description sub, Description sup) {
		
		if (sub instanceof BasicClassDescription) {
			if (!check_redundant(sup, sub)) 
				rv.addAssertion(fac.createSubClassAxiom((BasicClassDescription) sub, (BasicClassDescription) sup));
		} 
		else {
			if (!check_redundant_role((Property)sup, (Property)sub)) 
				rv.addAssertion(fac.createSubPropertyAxiom((Property) sub, (Property) sup));
		}
	}
	
	
	
	
	
	
	
	
	private boolean check_redundant_role(Property parent, Property child) {

		if (check_directly_redundant_role(parent, child))
			return true;
		else {
//			log.debug("Not directly redundant role {} {}", parent, child);
			for (Equivalences<Description> children_prime : isa.getDirectChildren(parent)) {
				Property child_prime = (Property) children_prime.getRepresentative();

				if (!child_prime.equals(child) && 
						check_directly_redundant_role(child_prime, child) && 
						!check_redundant(child_prime, parent)) {
					return true;
				}
			}
		}
//		log.debug("Not redundant role {} {}", parent, child);

		return false;
	}

	private boolean check_directly_redundant_role(Property parent, Property child) {

		PropertySomeRestriction existParentDesc = 
				fac.getPropertySomeRestriction(parent.getPredicate(), parent.isInverse());
		PropertySomeRestriction existChildDesc = 
				fac.getPropertySomeRestriction(child.getPredicate(), child.isInverse());

		return check_directly_redundant(parent, child) && 
				check_directly_redundant(existParentDesc, existChildDesc);
	}

	private boolean check_redundant(Description parent, Description child) {
		if (check_directly_redundant(parent, child))
			return true;
		else {
			for (Equivalences<Description> children_prime : isa.getDirectChildren(parent)) {
			Description child_prime = children_prime.getRepresentative();

				if (!child_prime.equals(child) && 
						check_directly_redundant(child_prime, child) && 
						!check_redundant(child_prime, parent)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean check_directly_redundant(Description parent, Description child) {
		
		Description sp = sigmaChain.getRepresentativeFor(parent);
		Description sc = sigmaChain.getRepresentativeFor(child);
		
		// if one of them is not in the respective DAG
		if (sp == null || sc == null) 
			return false;

		Set<Equivalences<Description>> spChildren =  sigmaChain.getDirectChildren(sp);
		Equivalences<Description> scEquivalent = sigmaChain.getEquivalences(sc);
		
		if (!spChildren.contains(scEquivalent))
			return false;
		
		
		
		Description tc = isaChain.getRepresentativeFor(child);
		// if one of them is not in the respective DAG
		if (tc == null) 
			return false;
		
		Set<Equivalences<Description>> scChildren = sigmaChain.getDescendants(sc);
		Set<Equivalences<Description>> tcChildren = isaChain.getDescendants(tc);

		return scChildren.containsAll(tcChildren);
	}
	
	
	
	
	
	
	public static Ontology getSigmaOntology(TBoxReasonerImpl reasoner) {
		OntologyFactory descFactory = new OntologyFactoryImpl();
		Ontology sigma = descFactory.createOntology("sigma");

		for (Equivalences<Description> nodes : reasoner.getNodes()) {
			Description node = nodes.getRepresentative();
			for (Equivalences<Description> descendants : reasoner.getDescendants(node)) {
				Description descendant = descendants.getRepresentative();

				if (!descendant.equals(node)) 
					addToSigma(sigma, descendant, node);
			}
			for (Description equivalent : nodes) {
				if (!equivalent.equals(node))  {
					addToSigma(sigma, node, equivalent);
					addToSigma(sigma, equivalent, node);					
				}
			}
		}

		return sigma;
	}
	
	/**
	 * Creating subClassOf or subPropertyOf axioms
	 */
	
	private static void addToSigma(Ontology sigma, Description subn, Description supern) {
		
		if (subn instanceof BasicClassDescription) {
			if (!(supern instanceof PropertySomeRestriction)) {
				Axiom ax = fac.createSubClassAxiom((BasicClassDescription) subn, (BasicClassDescription) supern);
				sigma.addEntities(ax.getReferencedEntities());
				sigma.addAssertion(ax);						
			}
		} 
		else {
			Axiom ax = fac.createSubPropertyAxiom((Property) subn, (Property) supern);
			sigma.addEntities(ax.getReferencedEntities());
			sigma.addAssertion(ax);						
		}		
	}
	
	
}
