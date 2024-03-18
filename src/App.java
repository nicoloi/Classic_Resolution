import cnf.ClauseSet;
import connective.Connective;
import formula.*;
import resolution.Resolution;
import java.util.Scanner;
import antlr4.ParseFormula;

/**
 * this class is used to check whether a formula taken
 * as input is a tautology or not.
 * 
 * The main method prints to stdout: 
 * - the input formula f.
 * - the corresponding clause set of ~f.
 * - and finally prints whether f is a tautology or not
 */
public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String formulaStr = sc.nextLine();
        sc.close();

        Formula f = ParseFormula.parse(formulaStr);

        if (f == null) {
            System.out.println("\nYour formula in input is not a well-formed formula");
            return;
        }

        System.out.println("\nYour formula in input:");
        System.out.println(f);

        //negate formula f
        Formula not_f = new CompoundFormula(Connective.NOT, f);

        ClauseSet cnf = not_f.toCnf();
            
        System.out.println("\nThe corresponding clause set of the negation is:");
        System.out.println(cnf);
        System.out.println();

        boolean step = false;
        if (args.length != 0 && args[0].equals("-v")) {
            step = true;
        }

        if (Resolution.isSatisfiable(cnf, step)) {
            System.out.println("The formula is not a tautology");
        } else {
            System.out.println("The formula is a tautology");
        }
    }
}
