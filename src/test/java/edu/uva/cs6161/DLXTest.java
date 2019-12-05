package edu.uva.cs6161;

import edu.uva.cs6161.handlers.CollectSolutionsHandler;
import edu.uva.cs6161.handlers.SolutionsHandler;
import edu.uva.cs6161.handlers.StdoutSolutionsHandler;
import edu.uva.cs6161.structures.ColumnObject;
import edu.uva.cs6161.structures.QuadLinkedList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DLXTest {
    public final static int[][] KNUTH_EXAMPLE = {
            {0, 0, 1, 0, 1, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 1}
    };

    public final static String[] KNUTH_EXAMPLE_NAMES = { "A", "B", "C", "D", "E", "F", "G"};

    @Test
    public void searchKnuthExample() {
        QuadLinkedList quadLinkedList = new QuadLinkedList(KNUTH_EXAMPLE, KNUTH_EXAMPLE_NAMES);

        CollectSolutionsHandler collector = new CollectSolutionsHandler();
        DLX solver = new DLX(false, collector);
        solver.search(quadLinkedList);

        List<String> solutions = collector.collect();

        String expectedSolution = new StringBuilder()
                .append("A D\n")
                .append("B G\n")
                .append("C E F")
                .toString();

        System.out.println(solutions.get(0));
        assertEquals(expectedSolution, solutions.get(0));
    }

    /**
     * https://en.wikipedia.org/wiki/Exact_cover#Basic_examples
     */
    @Test
    public void searchWikiBasicExample() {
        final int[][] wikiExample = {
         /*      {1, 2, 3, 4}  */
         /* N */ {0, 0, 0, 0},
         /* O */ {1, 0, 1, 0},
         /* P */ {0, 1, 1, 0},
         /* E */ {0, 1, 0, 1}
        };

        final String[] wikiNames = { "1", "2", "3", "4"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(wikiExample, wikiNames);

        CollectSolutionsHandler collector = new CollectSolutionsHandler();
        DLX solver = new DLX(false, collector);
        solver.search(quadLinkedList);

        List<String> solutions = collector.collect();

        String expectedSolution = new StringBuilder()
                .append("1 3\n")
                .append("2 4")
                .toString();

        assertEquals(expectedSolution, solutions.get(0));
    }

    /**
     * https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X#Example
     */
    @Test
    public void searchWikiDetailedExample() {
        final int[][] wikiExample = {
                {1, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1},
                {0, 0, 1, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 1}
        };

        final String[] wikiNames = { "1", "2", "3", "4", "5", "6", "7"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(wikiExample, wikiNames);

        CollectSolutionsHandler collector = new CollectSolutionsHandler();
        DLX solver = new DLX(false, collector);
        solver.search(quadLinkedList);

        List<String> solutions = collector.collect();

        String expectedSolution = new StringBuilder()
                .append("1 4\n")
                .append("2 7\n")
                .append("3 5 6")
                .toString();

        assertEquals(expectedSolution, solutions.get(0));
    }

    /**
     * http://www.ams.org/publicoutreach/feature-column/fcarc-kanoodle
     */
    @Test
    public void searchAMSExample() {
        final int[][] amsExample = {
                /*      {1, 2, 3, 4, 5}  */
                /* A */ {1, 0, 0, 0, 1},
                /* B */ {0, 1, 0, 1, 0},
                /* C */ {0, 1, 1, 0, 0},
                /* D */ {0, 0, 1, 0, 0},
                /* E */ {1, 0, 0, 1, 1}
        };

        final String[] amsNames = { "1", "2", "3", "4", "5"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(amsExample, amsNames);

        CollectSolutionsHandler collector = new CollectSolutionsHandler();
        DLX solver = new DLX(false, collector);
        solver.search(quadLinkedList);

        List<String> solutions = collector.collect();

        String expectedSolution1 = new StringBuilder()
                .append("1 5\n")
                .append("2 4\n")
                .append("3")
                .toString();

        String expectedSolution2 = new StringBuilder()
                .append("1 4 5\n")
                .append("2 3")
                .toString();

        assertEquals(expectedSolution1, solutions.get(0));
        assertEquals(expectedSolution2, solutions.get(1));
    }


    @Test
    public void searchNoSolutions() {

        int[][] matrix = {
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 1, 0}
        };

        String[] names = {"Moe", "Larry", "Curly", "Schemp"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix, names);


        CollectSolutionsHandler collector = new CollectSolutionsHandler();
        DLX solver = new DLX(false, collector);
        solver.search(quadLinkedList);

        List<String> solutions = collector.collect();
        assertTrue(solutions.isEmpty());
    }

    @Test
    public void testChooseCMinimizeBranchingTrue() {
        int[][] matrix = {
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 0},
                {1, 1, 1}
        };

        String[] names = {"MostOnes", "foobar", "LeastOnes"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix, names);

        DLX solver = new DLX(true, new StdoutSolutionsHandler());
        ColumnObject columnObject = solver.chooseC(quadLinkedList);

        assertEquals("LeastOnes", columnObject.getName());
    }

    @Test
    public void testChooseCMinimizeBranchingFalse() {
        int[][] matrix = {
                {1, 0, 0},
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 1}
        };

        String[] names = {"First", "Second", "LeastOnes"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix, names);

        DLX solver = new DLX(false, new StdoutSolutionsHandler());
        ColumnObject columnObject = solver.chooseC(quadLinkedList);
        assertEquals("First", columnObject.getName());
    }

    /**
     * See Page 7, Figure 3 for a visual representation https://arxiv.org/pdf/cs/0011047.pdf.
     */
    @Test
    public void testCoverColumn() {
        QuadLinkedList quadLinkedList = new QuadLinkedList(KNUTH_EXAMPLE, KNUTH_EXAMPLE_NAMES);

        ColumnObject columnA = (ColumnObject) quadLinkedList.getRoot().getR();
        ColumnObject columnD = quadLinkedList.getRoot();
        while(!(columnD = (ColumnObject) columnD.getR()).getName().equals("D"));
        ColumnObject columnG = quadLinkedList.getRoot();
        while(!(columnG= (ColumnObject) columnG.getR()).getName().equals("G"));

        // Before Cover.
        assertEquals("A", ((ColumnObject) quadLinkedList.getRoot().getR()).getName());
        assertNotEquals(columnD, columnD.getD().getD());
        assertNotEquals(columnG, columnG.getD().getD().getD());

        DLX knuthDancer = new DLX(false, new StdoutSolutionsHandler());
        knuthDancer.coverColumn(columnA);

        // After Cover.
        assertEquals("B", ((ColumnObject) quadLinkedList.getRoot().getR()).getName());
        assertEquals(columnD, columnD.getD().getD());
        assertEquals(columnG, columnG.getD().getD().getD());

        // For good measure make sure other links haven't changed.
        assertEquals(columnD.getD(), columnD.getD().getR().getR().getR());
    }

    @Test
    public void testUncoverColumn() {
        QuadLinkedList quadLinkedList = new QuadLinkedList(KNUTH_EXAMPLE, KNUTH_EXAMPLE_NAMES);

        ColumnObject columnA = (ColumnObject) quadLinkedList.getRoot().getR();
        ColumnObject columnD = quadLinkedList.getRoot();
        while(!(columnD = (ColumnObject) columnD.getR()).getName().equals("D"));
        ColumnObject columnG = quadLinkedList.getRoot();
        while(!(columnG= (ColumnObject) columnG.getR()).getName().equals("G"));

        // Before Cover.
        assertEquals("A", ((ColumnObject) quadLinkedList.getRoot().getR()).getName());
        assertNotEquals(columnD, columnD.getD().getD());
        assertNotEquals(columnG, columnG.getD().getD().getD());

        DLX knuthDancer = new DLX(false, new StdoutSolutionsHandler());
        knuthDancer.coverColumn(columnA);
        knuthDancer.uncoverColumn(columnA);

        // After Cover and Uncover.
        assertEquals("A", ((ColumnObject) quadLinkedList.getRoot().getR()).getName());
        assertNotEquals(columnD, columnD.getD().getD());
        assertNotEquals(columnG, columnG.getD().getD().getD());
    }
}