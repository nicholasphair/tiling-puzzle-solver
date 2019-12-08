package edu.uva.cs6161;

import com.google.common.io.Resources;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Test the solutions to the puzzles at http://www.cs.virginia.edu/~robins/puzzles/.
 */
@Ignore
public class AppTest {
    private static final App App = new App();

    @Test
    public void testInputFileCheckerboard() {
        String filename = Resources.getResource("checkerboard").getPath();
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFileTest1() {
        String filename = Resources.getResource("test1").getPath();
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFileTest2() {
        String filename = Resources.getResource("test2").getPath();
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFileTrivial() {
        String filename = Resources.getResource("trivial").getPath();
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    /**
     * Can't be parsed...
     */
    @Ignore
    @Test
    public void testInputFileIQ_creator() {
        String filename = Resources.getResource("IQ_creator").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    /**
     * Can't be parsed...
     */
    @Ignore
    @Test
    public void testInputFileLucky13() {
        String filename = Resources.getResource("lucky13").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    /**
     * Takes sometime - doesn't find a solution
     */
    @Test
    public void testInputFilePartial_cross() {
        String filename = Resources.getResource("partial_cross").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes3x10() {
        String filename = Resources.getResource("pentominoes3x10").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes3x20() {
        String filename = Resources.getResource("pentominoes3x20").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes6x10() {
        String filename = Resources.getResource("pentominoes6x10").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes8x8_middle_missing() {
        String filename = Resources.getResource("pentominoes8x8_middle_missing").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes8x8_corner_missing() {
        String filename = Resources.getResource("pentominoes8x8_corner_missing").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_corners() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_corners").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_diagonal() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_diagonal").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_near_corners() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_near_corners").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_near_middle() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_near_middle").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_offset_near_corners() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_offset_near_corners").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes8x8_four_missing_offset_near_middle() {
        String filename = Resources.getResource("pentominoes8x8_four_missing_offset_near_middle").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes8x8_side_missing() {
        String filename = Resources.getResource("pentominoes8x8_side_missing").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes8x8_side_offset_missing() {
        String filename = Resources.getResource("pentominoes8x8_side_offset_missing").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoesThirteen_holes() {
        String filename = Resources.getResource("thirteen_holes").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes4x15() {
        String filename = Resources.getResource("pentominoes4x15").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }

    @Test
    public void testInputFilePentominoes5x12() {
        String filename = Resources.getResource("pentominoes5x12").getPath();
        System.out.println(filename);
        long startTime = System.currentTimeMillis();
        List<String[][]> solutions = App.run(filename, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
        System.out.println(solutions.size());
    }
}
