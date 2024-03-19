package resolution;

import cnf.*;
import literal.Literal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * This class without constructors contains methods that implement 
 * the resolution method in propositional logic for a set of clauses.
 */
public class Resolution {

    private static Map<Integer, Set<Integer>> visited;
    private static List<Step> trace;
    private static boolean enableSteps = false;

    /**
     * Checks whether a set of clauses is satisfiable using the resolution method.
     *
     * This method determines whether a given set of clauses is satisfiable by applying
     * the resolution method. The method analyzes the given set of clauses
     * to find a contradiction, indicating that the set of clauses is unsatisfiable.
     * If no contradiction is found, the set of clauses is satisfiable.
     *
     * If tracing of resolution steps is enabled, the method prints a trace of the steps
     * performed by the resolution method. Tracing can be enabled using the
     * {@link #setEnableSteps(boolean)} method.
     *
     * @param s the set of clauses to be analyzed for satisfiability.
     * @return true if the set of clauses is satisfiable, false otherwise.
     * @throws NullPointerException if the input clause set is null.
     * @throws IllegalArgumentException if the input clause set is empty.
     * @see #setEnableSteps(boolean)
     */
    public static boolean isSatisfiable(ClauseSet s) {

        Objects.requireNonNull(s);
        if (s.isEmpty()) throw new IllegalArgumentException("the clause set in input is empty");

        s.removeTautologies();

        if (s.isEmpty()) {
            //in this case s contains only tautologies.
            return true;
        }

        visited = new HashMap<>();
        trace = new ArrayList<>();
        List<Clause> listCl = new ArrayList<>(); 

        for (Clause c : s) {
            //initializes the map, with an empty set for each index of the clauses in s
            visited.put(c.getIndex(), new HashSet<>());
            listCl.add(c);
        }

        for (int i = 0; i < listCl.size(); i++) {

            Clause c1 = listCl.get(i);
            int index1 = c1.getIndex();

            for (int j = 0; j < listCl.size(); j++) {

                Clause c2 = listCl.get(j);
                int index2 = c2.getIndex();

                if ((i != j) && !alreadyVisited(c1, c2)) {

                    Literal complemLit = getComplementaryLiterals(c1, c2);

                    if (complemLit != null) {
                        //adds the greater index to the set of indexes already visited by the lower index
                        if (index1 < index2) {
                            (visited.get(index1)).add(index2);
                        } else {
                            (visited.get(index2)).add(index1);
                        }

                        Clause newClause = resolRule(c1, c2, complemLit);

                        Step step = null;
                        if (enableSteps) {
                            //create a new step and insert it into the trace list.
                            step = new Step(c1, c2, newClause, complemLit);
                            trace.add(step);
                        }

                        /*
                        * if the resolving clause is empty, then we have found a contradiction 
                        * which proves that the set s is unsatisfiable.
                        */
                        if (newClause.isEmpty()) {
                            if (enableSteps) printTrace();
                            return false;
                        } 

                        if (newClause.isTautology()) {
                            if (enableSteps)
                                step.setTautology();
                        } else if (listCl.contains(newClause)) {
                            if (enableSteps)
                                step.setAlreadyPresent();
                        } else {
                            visited.put(newClause.getIndex(), new HashSet<>());
                            listCl.add(newClause);
                        }
                    }
                }
            }
        }

        if (enableSteps) printTrace();

        /*
         * if after analyzing all the pairs of clauses in s, 
         * the contradiction is not found, then s is satisfiable
         */
        return true;
    }

    /**
     * Enables or disables the tracing of resolution steps.
     *
     * This method enables or disables the tracing of resolution steps performed
     * by the {@link #isSatisfiable(ClauseSet)} method. When tracing is enabled, the
     * resolution steps will be printed to the Stdout. When tracing is disabled,
     * no steps will be printed.
     *
     * @param enableStepsValue true to enable tracing of resolution steps,
     *                         false to disable it.
     */
    public static void setEnableSteps(boolean enableStepsValue) {
        enableSteps = enableStepsValue;
    }

    /**
     *
     * This method checks whether two clauses have at least one literal in common
     * where one is the opposite of the other, so that the resolution rule for the 
     * two clauses can be executed.
     * 
     * @param c1 the first clause
     * @param c2 the second clause
     * @return the literal in common, that is complementary in the two clauses.
     * @return null, if the literal to search is not present.     
     */
    private static Literal getComplementaryLiterals(Clause c1, Clause c2) { 
        for (Literal l1 : c1) {
            for (Literal l2 : c2) { 
                if (l1.equals(l2.getOpposite())) return l1;
            }
        }

        return null;
    }

    /**
     * 
     * @param c1 the first clause.
     * @param c2 the second clause.
     * @return true, if the two clauses c1 and c2 are already compared previously
     *         by the resolution method. false otherwise.
     */
    private static boolean alreadyVisited(Clause c1, Clause c2) {
        int i1 = c1.getIndex();
        int i2 = c2.getIndex();

        if (i1 < i2) {
            return (visited.get(i1)).contains(i2);
        }

        //case i1 > i2
        return (visited.get(i2)).contains(i1);
    }


    /**
     * 
     * This method implements the resolution rule, in which two premise clauses are considered,
     * a pair of complementary literals in the two clauses are deleted, 
     * and the remaining literals are put into logical disjunction, 
     * thus forming the resolving clause.
     * 
     * @param c1 the first clause.
     * @param c2 the second clause.
     * @param lit the literal that must be deleted, with its opposite. 
     * @return the clause obtained by disjuncting the two clauses and 
     *         deleting the pair of literals
     */
    private static Clause resolRule(Clause c1, Clause c2, Literal lit) {
        Clause result = c1.union(c2);

        result.remove(lit);
        result.remove(lit.getOpposite());
        
        return result;
    }

    /**
     * prints the trace of the steps that have been performed by the resolution method.
     */
    private static void printTrace() {
        for (Step st : trace) {
            System.out.println(st.toString());
        }
    }
}
