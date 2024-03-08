package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.*;
import antlr4.ParseFormula;
import formula.*;
import resolution.Resolution;


public class ResolutionTest {

    @Test
    public void testSat1() {
        String sat1 = "~(c -> ~(a & b))";

        Formula f = ParseFormula.parse(sat1);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertTrue(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testSat2() {
        String sat2 = "((a & b) -> (c | ~d)) & ((a | c) & ~(b -> d))";

        Formula f = ParseFormula.parse(sat2);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertTrue(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testSat3() {
        String sat3 = "(~p & ~q) & (~r | ~(p | ~(~r -> q)))";

        Formula f = ParseFormula.parse(sat3);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertTrue(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testSat4() {
        String sat4 = "~(a | b) <-> (b & ~c)";

        Formula f = ParseFormula.parse(sat4);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertTrue(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testSat5() {
        String sat5 = "((a & b) -> (c | ~d)) | a";

        Formula f = ParseFormula.parse(sat5);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertTrue(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testSat6() {
        String sat6 = "(a -> b) <-> (~b -> ~a)";

        Formula f = ParseFormula.parse(sat6);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertTrue(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testSat7() {
        String sat7 = "~(a & ~b) <-> (~a | b)";

        Formula f = ParseFormula.parse(sat7);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertTrue(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testUnsat1() {
        String unsat1 = "((a & b) -> (c | ~d)) & ((a | c) & ~(b -> d)) & d";

        Formula f = ParseFormula.parse(unsat1);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertFalse(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testUnsat2() {
        String unsat2 = "(a -> b) & a & ~b";

        Formula f = ParseFormula.parse(unsat2);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertFalse(Resolution.isSatisfiable(f.toCnf(), false));
    }


    @Test
    public void testUnsat3() {
        String unsat3 = "~(a | ~a)";

        Formula f = ParseFormula.parse(unsat3);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertFalse(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testUnsat4() {
        String unsat4 = "~(((a & b) -> (c | ~d)) | a)";

        Formula f = ParseFormula.parse(unsat4);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertFalse(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testUnsat5() {
        String unsat5 = "~((a -> b) <-> (~b -> ~a))";

        Formula f = ParseFormula.parse(unsat5);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertFalse(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testUnsat6() {
        String unsat6 = "(a | b) & ~a & ~b";

        Formula f = ParseFormula.parse(unsat6);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertFalse(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testUnsat7() {
        String unsat7 = "~(~(a & ~b) <-> (~a | b))";

        Formula f = ParseFormula.parse(unsat7);

        if (f == null) {
            fail("The input formula is not valid");
        }

        assertFalse(Resolution.isSatisfiable(f.toCnf(), false));
    }

    @Test
    public void testInvalidFormula() {
        String invalid = "% & # ?";

        Formula f = ParseFormula.parse(invalid);

        assertEquals(f, null);
    }
}
