package it.unibz.inf.ontop.owlrefplatform.owlapi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nonnull;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.Node;

/**
 * Singleton class that maps {@link AxiomType} to
 * a method that implements entailment check for that 
 * type.
 * <br><br>
 * This class can be use for either:
 * <ul>
 * <li> to check if a given 
 * {@link AxiomType} is supported by QuestOWL 
 * ({@link #isAxiomTypeSupported(AxiomType)}) or
 * <li> to check entailment of a given axiom and instance
 * of QuestOWL ({@link #isEntailed(QuestOWL, OWLAxiom)}).
 * 
 * @author Ana Oliveira da Costa
 */
public class AxiomEntailmentQuestOWL {
	
	private Map<AxiomType<?>, AxiomEntailmentMethod> entailmentMethodMap;
	
	// only instance of AxiomEntailmentQuestOWL
	private static AxiomEntailmentQuestOWL instance = null;

	/**
	 * Initializes the map between {@link AxiomType} and methods that
	 * implement entailment check for that specific axiom.
	 * <br>
	 * This method is private to prevent instantiation from outside,
	 * This class follows the singleton design pattern.
	 */
	private AxiomEntailmentQuestOWL(){
		
		entailmentMethodMap = new HashMap<AxiomType<?>, AxiomEntailmentMethod>(); 
	
		// EquivalentClasses := 'EquivalentClasses' '(' axiomAnnotations 
		//						 	subClassExpression subClassExpression { subClassExpression } ')'
		entailmentMethodMap.put(AxiomType.EQUIVALENT_CLASSES, new AxiomEntailmentMethod() {
		    public boolean isEntailedByOntology(QuestOWL reasoner, OWLAxiom axiom) {
		    	return isEntailedEquivalentClasses(reasoner, axiom);
		    }
		});
		
		// EquivalentObjectProperties := 'EquivalentObjectProperties' '(' axiomAnnotations 
		// 									ObjectPropertyExpression ObjectPropertyExpression { ObjectPropertyExpression } ')'
		entailmentMethodMap.put(AxiomType.EQUIVALENT_OBJECT_PROPERTIES, new AxiomEntailmentMethod() {
		    public boolean isEntailedByOntology(QuestOWL reasoner, OWLAxiom axiom) {
		    	return isEntailedEquivalentObjectProperties(reasoner, axiom);
		    }
		});
		
			
	}

	/**
	 * Get the only instance of AxiomEntailmentQuestOWL.
	 */
	public static AxiomEntailmentQuestOWL getInstance() {
		if(instance == null) 
			instance = new AxiomEntailmentQuestOWL();
		
		return instance;
	}
	
	/**
	 * Checks if there exists a method that checks for the given {@link AxiomType}.
	 * 
	 * @param axiomType
	 * @return True if the {@link AxiomType} is supported; false otherwise.
	 */
	public boolean isAxiomTypeSupported(@Nonnull AxiomType<?> axiomType){
		return entailmentMethodMap.containsKey(axiomType);
	}
	
	/**
	 * Check if the axiom is entailed by the ontology in <code>reasoner</code>
	 * by calling the specific method for the {@link AxiomType} of 
	 * the given <code>axiom</code>
	 * <br>
	 * If the {@link AxiomType} is not supported it returns false.
	 * <br>
	 * It is recommended to check if an  {@link AxiomType} is supported
	 * before calling this method by using {@link #isAxiomTypeSupported(AxiomType)}.
	 * 
	 * @param reasoner
	 * @param axiom
	 * @return True if the axiom is entailed; false if either 
	 * the axiomType is not supported or the axiom is not entailed.
	 */
	public boolean isEntailed(QuestOWL reasoner, OWLAxiom axiom) {
		final AxiomEntailmentMethod entailmentMethod = entailmentMethodMap.get(axiom.getAxiomType());
		if(entailmentMethod != null) {
			return entailmentMethod.isEntailedByOntology(reasoner, axiom);
		}
		
		//if isAxiomTypeSupported is verify before each entailment check
		//this should never happen
		return false;
	}
	
	/**
	 * Entailment check for {@link AxiomType.EQUIVALENT_CLASSES}.
	 * <br>
	 * <b>Note: It is assumed that the check for the {@link AxiomType}
	 * was done before calling this method. <code>axiom</code> is cast
	 * to {@link OWLEquivalentClassesAxiom} inside this method.
	 * 
	 * @param reasoner
	 * @param axiom
	 * @return True if the axiom is entailed; false otherwise
	 */
	private boolean isEntailedEquivalentClasses(QuestOWL reasoner, OWLAxiom axiom) {
		
		boolean isEntailed = true;
    	OWLEquivalentClassesAxiom axiomEquivClasses = (OWLEquivalentClassesAxiom) axiom;
    	Iterator<OWLClassExpression> it = axiomEquivClasses.getClassExpressions().iterator();
    	
    	OWLClassExpression previous = it.next();
		while (it.hasNext()) {
			OWLClassExpression current = it.next();
			Node<OWLClass> equivCurrent = reasoner.getEquivalentClasses(current);
			
			if(!equivCurrent.contains(previous.asOWLClass())) {
				isEntailed = false;
				break;
			}
			
			previous = current;
		}
		
		return isEntailed;
	}
	
	/**
	 * Entailment check for {@link AxiomType.EQUIVALENT_OBJECT_PROPERTIES}.
	 * <br>
	 * <b>Note: It is assumed that the check for the {@link AxiomType}
	 * was done before calling this method. <code>axiom</code> is cast
	 * to {@link OWLEquivalentObjectPropertiesAxiom} inside this method.
	 * 
	 * @param reasoner
	 * @param axiom
	 * @return True if the axiom is entailed; false otherwise
	 */
	private boolean isEntailedEquivalentObjectProperties(QuestOWL reasoner, OWLAxiom axiom) {
		
		boolean isEntailed = true;
		OWLEquivalentObjectPropertiesAxiom axiomEquivOP = (OWLEquivalentObjectPropertiesAxiom) axiom;
		Iterator<OWLObjectPropertyExpression> it = axiomEquivOP.getProperties().iterator();
		OWLObjectPropertyExpression previous = it.next();
		
		while (it.hasNext()) {
			OWLObjectPropertyExpression current = it.next();
			Node<OWLObjectPropertyExpression>equivCurrent = reasoner.getEquivalentObjectProperties(current);
			
			if(!equivCurrent.contains(previous.asOWLObjectProperty())) {
				isEntailed = false;
				break;
			}
			
			previous = current;
		}
		
		return isEntailed;
	}

	

}
