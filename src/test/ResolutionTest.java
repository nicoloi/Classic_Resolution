package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.io.IOException;
import org.junit.*;
import antlr4.ParseFormula;
import connective.Connective;
import formula.*;
import resolution.Resolution;


public class ResolutionTest {

    @Test
    public void testTaut1() {
        
        String taut1 = readFile("taut1.txt");  // a | ~a

        if (taut1 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut1);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testTaut2() {
        
        String taut2 = readFile("taut2.txt"); // Modus ponens

        if (taut2 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut2);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testTaut3() {
        
        String taut3 = readFile("taut3.txt"); // Contraposition

        if (taut3 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut3);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testTaut4() {
        
        String taut4 = readFile("taut4.txt"); // Material implication

        if (taut4 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut4);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testTaut5() {
        
        String taut5 = readFile("taut5.txt"); // Modus tollens

        if (taut5 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut5);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testTaut6() {
        
        String taut6 = readFile("taut6.txt"); // De Morgan laws

        if (taut6 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut6);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testTaut7() {
        
        String taut7 = readFile("taut7.txt"); //absorption law

        if (taut7 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut7);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testTaut8() {
        
        String taut8 = readFile("taut8.txt"); //Transitive property of implication

        if (taut8 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut8);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testTaut9() {
        
        String taut9 = readFile("taut9.txt"); //Distributive property

        if (taut9 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut9);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testTaut10() {
        
        String taut10 = readFile("taut10.txt"); // Peirce law

        if (taut10 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut10);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testTaut11() {
        
        String taut10 = readFile("taut11.txt"); // (a -> b) | (b -> a)

        if (taut10 == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(taut10);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertFalse(Resolution.isSatisfiable(not_f.toCnf(), false));
    }


    @Test
    public void testNonTaut1() {
        
        String nt = readFile("non-taut1.txt");

        if (nt == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(nt);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertTrue(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testNonTaut2() {
        
        String nt = readFile("non-taut2.txt");

        if (nt == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(nt);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertTrue(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testNonTaut3() {
        
        String nt = readFile("non-taut3.txt");

        if (nt == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(nt);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertTrue(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testNonTaut4() {
        
        String nt = readFile("non-taut4.txt");

        if (nt == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(nt);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertTrue(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testNonTaut5() {
        
        String nt = readFile("non-taut5.txt");

        if (nt == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(nt);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertTrue(Resolution.isSatisfiable(not_f.toCnf(), false));
    }
    
    @Test
    public void testNonTaut6() {
        
        String nt = readFile("non-taut6.txt");

        if (nt == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(nt);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertTrue(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testNonTaut7() {
        
        String nt = readFile("non-taut7.txt");

        if (nt == null) {
            fail("The file doesn't exist");
        }

        Formula f = ParseFormula.parse(nt);

        if (f == null) {
            fail("Parsing error of formula f");
        }

        Formula not_f = new CompoundFormula(Connective.NOT, f);

        assertTrue(Resolution.isSatisfiable(not_f.toCnf(), false));
    }

    @Test
    public void testInvalidFormula() {
        String invalid = "% & # ?";

        Formula f = ParseFormula.parse(invalid);

        assertEquals(f, null);
    }

    /**
     * 
     * @param fileName the name of the text file to read.
     * @return the string containing the text of the read file.
     */
    public String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();

        try {
            FileReader file = new FileReader("./src/test/" + fileName);
            int data = file.read();

            while (data != -1) {
                sb.append( (char) data );
                data = file.read();
            }

            file.close();
        } catch (IOException e) {
            return null;
        }
        
        return sb.toString();
    }
}
