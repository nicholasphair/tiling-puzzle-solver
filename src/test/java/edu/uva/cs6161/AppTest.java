package edu.uva.cs6161;

import com.google.common.io.Resources;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Test the solutions to the puzzles at http://www.cs.virginia.edu/~robins/puzzles/.
 */
public class AppTest {
    private static final App App = new App();

    @Test
    public void testInputFileCheckerboard() {
        String filename = Resources.getResource("checkerboard").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFileTest1() {
        String filename = Resources.getResource("test1").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFileTest2() {
        String filename = Resources.getResource("test2").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFileTrivial() {
        String filename = Resources.getResource("trivial").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFileIQ_creator() {
        String filename = Resources.getResource("IQ_creator").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Ignore
    @Test
    public void testInputFileLucky13() {
        String filename = Resources.getResource("lucky13").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePartial_cross() {
        String filename = Resources.getResource("partial_cross").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, true);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes8x8_middle_missing() {
        String filename = Resources.getResource("pentominoes8x8_middle_missing").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes8x8_corner_missing() {
        String filename = Resources.getResource("pentominoes8x8_corner_missing").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_corners() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_corners").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_diagonal() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_diagonal").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_near_corners() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_near_corners").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_near_middle() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_near_middle").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_offset_near_corners() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_offset_near_corners").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_offset_near_middle() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_offset_near_middle").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes8x8_side_missing() {
        String filename = Resources.getResource("pentominoes8x8_side_missing").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes8x8_side_offset_missing() {
        String filename = Resources.getResource("pentominoes8x8_side_offset_missing").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoesThirteen_holes() {
        String filename = Resources.getResource("thirteen_holes").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
    }

    @Test
    public void testInputFilePentominoes3x20() {
        String filename = Resources.getResource("pentominoes3x20").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
        assertEquals(2, App.uniqueSolutions(solutions).size());
    }


    @Test
    public void testInputFilePentominoes4x15() {
        String filename = Resources.getResource("pentominoes4x15").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
        assertEquals(368, App.uniqueSolutions(solutions).size());
    }

    @Test
    public void testInputFilePentominoes5x12() {
        String filename = Resources.getResource("pentominoes5x12").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
        assertEquals(1010, App.uniqueSolutions(solutions).size());
    }

    @Test
    public void testInputFilePentominoes6x10() {
        String filename = Resources.getResource("pentominoes6x10").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
        assertEquals(2339, App.uniqueSolutions(solutions).size());
    }

    /* https://isomerdesign.com/Pentomino/3x21d/index.html */
    @Test
    public void testInputFilePentominoes3x21D() {
        String filename = Resources.getResource("pentominoes3x21D").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
        assertEquals(6, App.uniqueSolutions(solutions).size());
    }

    /* https://isomerdesign.com/Pentomino/4x16b/index.html */
    @Test
    public void testInputFilePentominoes4x16B() {
        String filename = Resources.getResource("pentominoes4x16B").getPath();
        System.out.println(filename);
        List<String[][]> solutions = App.run(filename, false);
        print2Solutions(solutions);
        assertEquals(56, App.uniqueSolutions(solutions).size());
    }

    private static void print2Solutions(List<String[][]> solutions) {
        int i = 0;
        for(String[][] string : solutions) {
            if(i++ == 2) return;
            for(String[] row : string) {
                for(String col : row) {
                    System.out.print(col + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }



}
