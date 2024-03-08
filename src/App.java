import cnf.ClauseSet;
import formula.*;
import resolution.Resolution;
import java.util.Scanner;
import antlr4.ParseFormula;

/**
 * 
 * this class is used to verify the satisfiability 
 * of a formula taken as input (stdin).
 * 
 * The main method prints to stdout: 
 * - the input formula.
 * - the corresponding clause set (cnf).
 * - and finally prints whether it is satisfiable or not.
 */
public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String formulaStr = sc.nextLine();
        sc.close();

        Formula f = ParseFormula.parse(formulaStr);

        if (f == null) {
            System.out.println("\nYour formula in input is not valid.");
            return;
        }

        System.out.println("\nYour formula in input:");
        System.out.println(f);

        ClauseSet cnf = f.toCnf();
            
        System.out.println("\nThe corresponding clause set is:");
        System.out.println(cnf);
        System.out.println();

        if (Resolution.isSatisfiable(cnf, false)) {
            System.out.println("SATISFIABLE");
        } else {
            System.out.println("UNSATISFIABLE");
        }
    }
}
