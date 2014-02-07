/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.owlrefplatform.core.mappingprocessing;

import it.unibz.krdb.obda.model.BuiltinPredicate;
import it.unibz.krdb.obda.model.CQIE;
import it.unibz.krdb.obda.model.Constant;
import it.unibz.krdb.obda.model.DatalogProgram;
import it.unibz.krdb.obda.model.Function;
import it.unibz.krdb.obda.model.Term;
import it.unibz.krdb.obda.model.OBDADataFactory;
import it.unibz.krdb.obda.model.OBDAException;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.Variable;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;
import it.unibz.krdb.obda.ontology.ClassDescription;
import it.unibz.krdb.obda.ontology.Description;
import it.unibz.krdb.obda.ontology.OClass;
import it.unibz.krdb.obda.ontology.Ontology;
import it.unibz.krdb.obda.ontology.Property;
import it.unibz.krdb.obda.ontology.PropertySomeRestriction;
import it.unibz.krdb.obda.owlrefplatform.core.basicoperations.CQCUtilities;
import it.unibz.krdb.obda.owlrefplatform.core.basicoperations.Unifier;
import it.unibz.krdb.obda.owlrefplatform.core.dagjgrapht.Equivalences;
import it.unibz.krdb.obda.owlrefplatform.core.dagjgrapht.TBoxReasonerImpl;
import it.unibz.krdb.obda.owlrefplatform.core.tboxprocessing.SigmaTBoxOptimizer;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TMappingProcessor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6032320436478004010L;

	private static final OBDADataFactory fac = OBDADataFactoryImpl.getInstance();

	private final Ontology aboxDependencies;
	
	private final TBoxReasonerImpl reasoner;

	// private final static Logger log = LoggerFactory.getLogger(TMappingProcessor.class);
	
	boolean optimize = true;

	public TMappingProcessor(Ontology tbox, boolean optmize) {
		this.optimize = optmize;
		
		reasoner = new TBoxReasonerImpl(tbox);

		aboxDependencies =  SigmaTBoxOptimizer.getSigmaOntology(reasoner);
	}

	/***
	 * Creates an index of all mappings based on the predicate of the head of
	 * the mapping. The returned map can be used for fast access to the mapping
	 * list.
	 * 
	 * @param mappings
	 *            A set of mapping given as CQIEs
	 * @return A map from a predicate to the list of mappings that have that
	 *         predicate in the head atom.
	 */
	private Map<Predicate, Set<CQIE>> getMappingIndex(DatalogProgram mappings) {
		Map<Predicate, Set<CQIE>> mappingIndex = new HashMap<Predicate, Set<CQIE>>();

		for (CQIE mapping : mappings.getRules()) {
			Set<CQIE> set = mappingIndex.get(mapping.getHead().getPredicate());
			if (set == null) {
				set = new HashSet<CQIE>();
				mappingIndex.put(mapping.getHead().getPredicate(), set);
			}
			set.add(mapping);
		}

		return mappingIndex;
	}

	public Ontology getABoxDependencies() {
		return aboxDependencies;
	}

	/***
	 * 
	 * This is an optimization mechanism that allows T-mappings to produce a
	 * smaller number of mappings, and hence, the unfolding will be able to
	 * produce fewer queries.
	 * 
	 * Given a set of mappings for a class/property A in {@link currentMappings}
	 * , this method tries to add a the data coming from a new mapping for A in
	 * an optimal way, that is, this method will attempt to include the content
	 * of coming from {@link newmapping} by modifying an existing mapping
	 * instead of adding a new mapping.
	 * 
	 * <p/>
	 * 
	 * To do this, this method will strip {@link newmapping} from any
	 * (in)equality conditions that hold over the variables of the query,
	 * leaving only the raw body. Then it will look for another "stripped"
	 * mapping <bold>m</bold> in {@link currentMappings} such that m is
	 * equivalent to stripped(newmapping). If such a m is found, this method
	 * will add the extra semantics of newmapping to "m" by appending
	 * newmapping's conditions into an OR atom, together with the existing
	 * conditions of m.
	 * 
	 * </p>
	 * If no such m is found, then this method simply adds newmmapping to
	 * currentMappings.
	 * 
	 * 
	 * <p/>
	 * For example. If new mapping is equal to
	 * <p/>
	 * 
	 * S(x,z) :- R(x,y,z), y = 2
	 * 
	 * <p/>
	 * and there exists a mapping m
	 * <p/>
	 * S(x,z) :- R(x,y,z), y > 7
	 * 
	 * This method would modify 'm' as follows:
	 * 
	 * <p/>
	 * S(x,z) :- R(x,y,z), OR(y > 7, y = 2)
	 * 
	 * <p/>
	 * 
	 * @param currentMappings
	 *            The set of existing mappings for A/P
	 * @param newmapping
	 *            The new mapping for A/P
	 */
	public void mergeMappingsWithCQC(Set<CQIE> currentMappings, CQIE newmapping) {
		List<Function> strippedNewConditions = new LinkedList<Function>();
		CQIE strippedNewMapping = getStrippedMapping(newmapping, strippedNewConditions);
		Ontology sigma = null;
		
		CQCUtilities cqc1 = new CQCUtilities(strippedNewMapping, sigma);
		Iterator<CQIE> mappingIterator = currentMappings.iterator();
		
		/***
		 * Facts are just added
		 */
		if (newmapping.getBody().size() == 0) {
			currentMappings.add(newmapping);
			return;
		}
		
		while (mappingIterator.hasNext()) {
			CQIE currentMapping = mappingIterator.next();
			List<Function> strippedExistingConditions = new LinkedList<Function>();
			CQIE strippedCurrentMapping = getStrippedMapping(currentMapping, strippedExistingConditions);

			if (!cqc1.isContainedIn(strippedCurrentMapping))
				continue;

			CQCUtilities cqc = new CQCUtilities(strippedCurrentMapping, sigma);
			if (!cqc.isContainedIn(strippedNewMapping))
				continue;

			/*
			 * We found an equivalence, we will try to merge the conditions of
			 * newmapping into the currentMapping.
			 */
			if (strippedNewConditions.size() != 0 && strippedExistingConditions.size() == 0) {
				/*
				 * There is a containment and there is no need to add the new
				 * mapping since there there is no extra conditions in the new
				 * mapping
				 */
				return;
			} else if (strippedNewConditions.size() == 0 && strippedExistingConditions.size() != 0) {
				/*
				 * The existing query is more specific than the new query, so we
				 * need to add the new query and remove the old
				 */
				mappingIterator.remove();
				break;
			} else if (strippedNewConditions.size() == 0 && strippedExistingConditions.size() == 0) {
				/*
				 * There are no conditions, and the new mapping is redundant, do not add anything
				 */
				return;
			} else {
				/*
				 * Here we can merge conditions of the new query with the one we
				 * just found.
				 */
				
				Map<Variable,Term> mgu = null;
				if (strippedCurrentMapping.getBody().size() == 1) {
					mgu = Unifier.getMGU(strippedCurrentMapping.getBody().get(0), strippedNewMapping.getBody().get(0));
				}			
				Function newconditions = mergeConditions(strippedNewConditions);
				Function existingconditions = mergeConditions(strippedExistingConditions);
				Term newconditionsTerm = fac.getFunction(newconditions.getPredicate(), newconditions.getTerms());
				Term existingconditionsTerm = fac.getFunction(existingconditions.getPredicate(), existingconditions.getTerms());
				Function orAtom = fac.getFunctionOR(existingconditionsTerm, newconditionsTerm);
				strippedCurrentMapping.getBody().add(orAtom);
				mappingIterator.remove();
				newmapping = strippedCurrentMapping;
				
				if (mgu != null) {
					newmapping = Unifier.applyUnifier(newmapping, mgu);
				}
				break;
			}
		}
		currentMappings.add(newmapping);
	}

	/***
	 * Takes a conjunctive boolean atoms and returns one single atom
	 * representing the conjunction (it might be a single atom if
	 * conditions.size() == 1.
	 * 
	 * @param conditions
	 * @return
	 */
	private Function mergeConditions(List<Function> conditions) {
		if (conditions.size() == 1)
			return conditions.get(0);
		Function atom0 = conditions.remove(0);
		Function atom1 = conditions.remove(0);
		Term f0 = fac.getFunction(atom0.getPredicate(), atom0.getTerms());
		Term f1 = fac.getFunction(atom1.getPredicate(), atom1.getTerms());
		Function nestedAnd = fac.getFunctionAND(f0, f1);
		while (conditions.size() != 0) {
			Function condition = conditions.remove(0);
			Term term0 = nestedAnd.getTerm(1);
			Term term1 = fac.getFunction(condition.getPredicate(), condition.getTerms());
			Term newAND = fac.getFunctionAND(term0, term1);
			nestedAnd.setTerm(1, newAND);
		}
		return nestedAnd;
	}

	/***
	 * Returns a new CQIE obtained from {@link mapping} where all builtin
	 * predicates (conditionals) have been removed. The removed conditional
	 * atoms will be added to the {@link strippedConditionsHolder} list.
	 * 
	 * <p>
	 * This method is used by mergeMappingsWithCQC to test for containtment of
	 * the stripped bodies.
	 * 
	 * @param mapping
	 * @param strippedConditionsHolder
	 * @return
	 */
	private CQIE getStrippedMapping(CQIE mapping, List<Function> strippedConditionsHolder) {
		strippedConditionsHolder.clear();
		Function head = (Function)mapping.getHead().clone();
		List<Function> body = mapping.getBody();
		List<Function> newbody = new LinkedList<Function>();
		for (int i = 0; i < body.size(); i++) {
			Function atom = body.get(i);
			Function clone = (Function)atom.clone();
			if (clone.getPredicate() instanceof BuiltinPredicate) {
				strippedConditionsHolder.add(clone);
			} else {
				newbody.add(clone);
			}
		}
		return fac.getCQIE(head, newbody);
	}

	/***
	 * Given a set of mappings in {@link originalMappings}, this method will
	 * return a new set of mappings in which no constants appear in the body of
	 * database predicates. This is done by replacing the constant occurence
	 * with a fresh variable, and adding a new equality condition to the body of
	 * the mapping.
	 * <p/>
	 * 
	 * For example, let the mapping m be
	 * <p/>
	 * A(x) :- T(x,y,22)
	 * 
	 * <p>
	 * Then this method will replace m by the mapping m'
	 * <p>
	 * A(x) :- T(x,y,z), EQ(z,22)
	 * 
	 * @param originalMappings
	 * @return A new DatalogProgram that has been normalized in the way
	 *         described above.
	 */
	public DatalogProgram normalizeConstants(DatalogProgram originalMappings) {
		DatalogProgram newProgram = fac.getDatalogProgram();
		newProgram.setQueryModifiers(originalMappings.getQueryModifiers());
		for (CQIE currentMapping : originalMappings.getRules()) {
			int freshVarCount = 0;

			Function head = (Function)currentMapping.getHead().clone();
			List<Function> newBody = new LinkedList<Function>();
			for (Function currentAtom : currentMapping.getBody()) {
				if (!(currentAtom.getPredicate() instanceof BuiltinPredicate)) {
					Function clone = (Function)currentAtom.clone();
					for (int i = 0; i < clone.getTerms().size(); i++) {
						Term term = clone.getTerm(i);
						if (term instanceof Constant) {
							/*
							 * Found a constant, replacing with a fresh variable
							 * and adding the new equality atom.
							 */
							freshVarCount += 1;
							Variable freshVariable = fac.getVariable("?FreshVar" + freshVarCount);
							newBody.add(fac.getFunctionEQ(freshVariable, term));
							clone.setTerm(i, freshVariable);
						}
					}
					newBody.add(clone);
				} else {
					newBody.add((Function)currentAtom.clone());
				}
			}
			CQIE normalizedMapping = fac.getCQIE(head, newBody);
			newProgram.appendRule(normalizedMapping);
		}
		return newProgram;
	}

	public DatalogProgram getTMappings(DatalogProgram originalMappings, boolean full) throws OBDAException {

		/*
		 * Normalizing constants
		 */
		originalMappings = normalizeConstants(originalMappings);
		Map<Predicate, Set<CQIE>> mappingIndex = getMappingIndex(originalMappings);
		
		/*
		 * Merge original mappings that have similar source query.
		 */
		if (optimize)
		optimizeMappingProgram(mappingIndex);
		
		/*
		 * Processing mappings for all Properties
		 */

		/*
		 * We process the mappings for the descendents of the current node,
		 * adding them to the list of mappings of the current node as defined in
		 * the TMappings specification.
		 */

		/*
		 * We start with the property mappings, since class t-mappings require
		 * that these are already processed. We start with the leafs.
		 */

		for (Property currentProperty : reasoner.getPropertyNames()) {
			/* setting up the queue for the next iteration */

			/* we only create mappings for named properties and not considering equivalences*/
			if (!reasoner.isCanonicalRepresentative(currentProperty)) 
				continue;
			

			/* Getting the current node mappings */
			Predicate currentPredicate = currentProperty.getPredicate();
			Set<CQIE> currentNodeMappings = mappingIndex.get(currentPredicate);	
			if (currentNodeMappings == null) {
				currentNodeMappings = new LinkedHashSet<CQIE>();
				mappingIndex.put(currentPredicate, currentNodeMappings);
			}


			for (Equivalences<Description> descendants : reasoner.getDescendants(currentProperty)) {
				for(Description descendant: descendants){

				/*
				 * adding the mappings of the children as own mappings, the new
				 * mappings use the current predicate instead of the childs
				 * predicate and, if the child is inverse and the current is
				 * positive, it will also invert the terms in the head
				 */
				Property childproperty = (Property) descendant;
				List<CQIE> childMappings = originalMappings.getRules(childproperty.getPredicate());

				boolean requiresInverse = (currentProperty.isInverse() != childproperty.isInverse());

				for (CQIE childmapping : childMappings) {
					CQIE newmapping = null;
					Function newMappingHead = null;
					Function oldMappingHead = childmapping.getHead();
					if (!requiresInverse) {

						if (!full)
							continue;


						newMappingHead = fac.getFunction(currentPredicate, oldMappingHead.getTerms());
					} else {
						Term term0 = oldMappingHead.getTerms().get(1);
						Term term1 = oldMappingHead.getTerms().get(0);
						newMappingHead = fac.getFunction(currentPredicate, term0, term1);
					}
					newmapping = fac.getCQIE(newMappingHead, childmapping.getBody());

					if (optimize)
					  mergeMappingsWithCQC(currentNodeMappings, newmapping);
					else 
						currentNodeMappings.add(newmapping);
				}

			}
			}

			/* Setting up mappings for the equivalent classes */
			for (Description equiv : reasoner.getEquivalences(currentProperty)) {
				if(equiv.equals(currentProperty))
					continue;

				Property equivProperty = (Property) equiv;
				// if (equivProperty.isInverse())
				// continue;
				Predicate p = ((Property) equiv).getPredicate();
				Set<CQIE> equivalentPropertyMappings = mappingIndex.get(p);
				if (equivalentPropertyMappings == null) {
					equivalentPropertyMappings = new LinkedHashSet<CQIE>();
					mappingIndex.put(p, equivalentPropertyMappings);
				}

				for (CQIE currentNodeMapping : currentNodeMappings) {

					if (equivProperty.isInverse() == currentProperty.isInverse()) {
						Function newhead = fac.getFunction(p, currentNodeMapping.getHead().getTerms());
						CQIE newmapping = fac.getCQIE(newhead, currentNodeMapping.getBody());

						if (optimize)
						mergeMappingsWithCQC(equivalentPropertyMappings, newmapping);
						else 
							equivalentPropertyMappings.add(newmapping);
					} else {
						Term term0 = currentNodeMapping.getHead().getTerms().get(1);
						Term term1 = currentNodeMapping.getHead().getTerms().get(0);
						Function newhead = fac.getFunction(p, term0, term1);
						CQIE newmapping = fac.getCQIE(newhead, currentNodeMapping.getBody());
						if (optimize)
							mergeMappingsWithCQC(equivalentPropertyMappings, newmapping);
							else 
								equivalentPropertyMappings.add(newmapping);					}
				}
			}
		} // Properties loop ended

		/*
		 * Property t-mappings are done, we now continue with class t-mappings.
		 * Starting with the leafs.
		 */

		for (OClass currentProperty : reasoner.getClassNames()) {

			if (!reasoner.isCanonicalRepresentative(currentProperty)) //don't consider the equivalences
				continue;



			/* Getting the current node mappings */
			Predicate currentPredicate = currentProperty.getPredicate();
			Set<CQIE> currentNodeMappings = mappingIndex.get(currentPredicate);
			if (currentNodeMappings == null) {
				currentNodeMappings = new LinkedHashSet<CQIE>();
				mappingIndex.put(currentPredicate, currentNodeMappings);
			}

			for (Equivalences<Description> descendants : reasoner.getDescendants(currentProperty)) {
				for(Description descendant: descendants){
				
					

				/*
				 * adding the mappings of the children as own mappings, the new
				 * mappings. There are three cases, when the child is a named
				 * class, or when it is an \exists P or \exists \inv P.
				 */
				ClassDescription childDescription = (ClassDescription) descendant;
				Predicate childPredicate = null;
				boolean isClass = true;
				boolean isInverse = false;
				if (childDescription instanceof OClass) {
					if (!full)
						continue;
					childPredicate = ((OClass) childDescription).getPredicate();
				} else if (childDescription instanceof PropertySomeRestriction) {
					childPredicate = ((PropertySomeRestriction) childDescription).getPredicate();
					isInverse = ((PropertySomeRestriction) childDescription).isInverse();
					isClass = false;
				} else {
					throw new RuntimeException("Unknown type of node in DAG: " + descendant);
				}

				List<CQIE> desendantMappings = originalMappings.getRules(childPredicate);

				for (CQIE childmapping : desendantMappings) {
					CQIE newmapping = null;
					Function newMappingHead = null;
					Function oldMappingHead = childmapping.getHead();

					if (isClass) {
						newMappingHead = fac.getFunction(currentPredicate, oldMappingHead.getTerms());
					} else {
						if (!isInverse) {
							newMappingHead = fac.getFunction(currentPredicate, oldMappingHead.getTerms().get(0));
						} else {
							newMappingHead = fac.getFunction(currentPredicate, oldMappingHead.getTerms().get(1));
						}
					}
					newmapping = fac.getCQIE(newMappingHead, childmapping.getBody());
					
					if (optimize)
					mergeMappingsWithCQC(currentNodeMappings, newmapping);
					else
						currentNodeMappings.add(newmapping);

				}
				}}

				// TODO HACK! Remove when the API of DAG is clean, the following
				// is a hack due to a bug in the DAG API.
				/*
				 * Currently, the DAG.buildDescendants() call has an error with
				 * \exists R. If a node A has an equivalent node \exists R, and
				 * R has equivalent roles P,S, we will have that \exists P and
				 * \exists S do not appear as descendants of A altough they
				 * should. Hence, we have to collect them manually with the next
				 * code block.
				 */

//				if (descendant instanceof PropertySomeRestriction) {
//					for (Description descendant2 : reasoner.getEquivalences(descendant,false)) {

//						childDescription = (ClassDescription) descendant2;
//						childPredicate = null;
//						isClass = true;
//						isInverse = false;
//						if (childDescription instanceof OClass) {
//							childPredicate = ((OClass) childDescription).getPredicate();
//						} else if (childDescription instanceof PropertySomeRestriction) {
//							childPredicate = ((PropertySomeRestriction) childDescription).getPredicate();
//							isInverse = ((PropertySomeRestriction) childDescription).isInverse();
//							isClass = false;
//						} else {
//							throw new RuntimeException("Unknown type of node in DAG: " + descendant2);
//						}
//


//						desendantMappings = originalMappings.getRules(childPredicate);
//

//						for (CQIE childmapping : desendantMappings) {
//							CQIE newmapping = null;
//							Atom newMappingHead = null;
//							Atom oldMappingHead = childmapping.getHead();
//

//							if (isClass) {
//								newMappingHead = fac.getAtom(currentPredicate, oldMappingHead.getTerms());
//							} else {
//								if (!isInverse) {
//									newMappingHead = fac.getAtom(currentPredicate, oldMappingHead.getTerms().get(0));
//								} else {

//									newMappingHead = fac.getAtom(currentPredicate, oldMappingHead.getTerms().get(1));
//								}
//							}


//							newmapping = fac.getCQIE(newMappingHead, childmapping.getBody());
//							if (optimize)
//								mergeMappingsWithCQC(currentNodeMappings, newmapping);
//								else

//									currentNodeMappings.add(newmapping);						}
//					}
//				}
//			}
			




			/* Setting up mappings for the equivalent classes */
			for (Description equiv : reasoner.getEquivalences(currentProperty)) {

				if (!(equiv instanceof OClass) || equiv.equals(currentProperty))
					continue;
				Predicate p = ((OClass) equiv).getPredicate();
				Set<CQIE> equivalentClassMappings = mappingIndex.get(p);

				if (equivalentClassMappings == null) {
					equivalentClassMappings = new LinkedHashSet<CQIE>();
					mappingIndex.put(p, equivalentClassMappings);
				}

				for (CQIE currentNodeMapping : currentNodeMappings) {
					Function newhead = fac.getFunction(p, currentNodeMapping.getHead().getTerms());
					CQIE newmapping = fac.getCQIE(newhead, currentNodeMapping.getBody());
					
					if (optimize)
						mergeMappingsWithCQC(equivalentClassMappings, newmapping);
						else
							equivalentClassMappings.add(newmapping);
					
				}
			}
		}
		DatalogProgram tmappingsProgram = fac.getDatalogProgram();
		for (Predicate key : mappingIndex.keySet()) {
			for (CQIE mapping : mappingIndex.get(key)) {
				tmappingsProgram.appendRule(mapping);
			}
		}
		return tmappingsProgram;
	}

	private void optimizeMappingProgram(Map<Predicate, Set<CQIE>> mappingIndex) {
		for (Predicate p : mappingIndex.keySet()) {
			Set<CQIE> similarMappings = mappingIndex.get(p);
			Set<CQIE> result = new HashSet<CQIE>();
			Iterator<CQIE> iterSimilarMappings = similarMappings.iterator();
			while (iterSimilarMappings.hasNext()) {
				CQIE candidate = iterSimilarMappings.next();
				iterSimilarMappings.remove();
				
				if (candidate.getBody().size() > 0) {
					mergeMappingsWithCQC(result, candidate);
				} else {
					result.add(candidate);
				}
			}
			mappingIndex.put(p, result);
		}
	}

	private List<Description> getLeafs(Collection<Description> nodes) {
		LinkedList<Description> leafs = new LinkedList<Description>();
		for (Description node : nodes) {
			if (reasoner.getDirectChildren(node).isEmpty())

				leafs.add(node);
		}
		return leafs;
	}

}
