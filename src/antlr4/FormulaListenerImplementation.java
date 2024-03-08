package antlr4;

import java.util.Stack;
import antlr4.FormulaExpressionParser.Atomic_formulaContext;
import antlr4.FormulaExpressionParser.FormulaContext;
import formula.*;
import connective.*;

/**
 * this class extends the FormulaExpression base listener,
 * in order to parse the input formula
 */
public class FormulaListenerImplementation extends FormulaExpressionBaseListener {

    private Stack<Formula> stack = new Stack<>(); 

    @Override
    public void exitAtomic_formula(Atomic_formulaContext ctx) {
        // create new atomic formula and adds it on the stack
        AtomicFormula af = new AtomicFormula(ctx.getText());

        stack.push(af);
    }
    
    @Override
    public void exitFormula(FormulaContext ctx) {

        if (ctx.unary_connective() != null) {
            Formula f = stack.pop();
            Formula not_f = new CompoundFormula(Connective.NOT, f);
            stack.push(not_f);

        } else if (ctx.binary_connective() != null) {
            String conn = ctx.binary_connective().getText();

            Formula right = stack.pop();
            Formula left = stack.pop();

            switch (conn) {
                case "&":
                    CompoundFormula and = new CompoundFormula(Connective.AND, left, right);
                    stack.push(and);
                    return;
                case "|":
                    CompoundFormula or = new CompoundFormula(Connective.OR, left, right);
                    stack.push(or);
                    return;
                case "->":
                    CompoundFormula impl = new CompoundFormula(Connective.IMPLIES, left, right);
                    stack.push(impl);
                    return;
                case "<->":
                    CompoundFormula iff = new CompoundFormula(Connective.IFF, left, right);
                    stack.push(iff);
                    return;
            }
        }
    }

    /**
     * @return the formula obtained by parsing the input.
     */
    public Formula getFormula() {
        return stack.peek();
    }
}
