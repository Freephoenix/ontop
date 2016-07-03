package it.unibz.inf.ontop.owlrefplatform.owlapi;

import org.semanticweb.owlapi.model.OWLAxiom;

/**
 * Interface for classes that implement
 * axiom entailment checking.
 * 
 * @author Ana Oliveira da Costa
 */
public interface AxiomEntailmentMethod {

	public boolean isEntailedByOntology(QuestOWL reasoner, OWLAxiom axiom);
}
