package edu.uva.cs6161;

import edu.uva.cs6161.structures.ColumnObject;
import edu.uva.cs6161.structures.DataObject;
import edu.uva.cs6161.structures.QuadLinkedList;
import org.junit.Test;

import javax.xml.crypto.Data;

import static org.junit.Assert.*;

public class dlxTest {

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
    public void search() {

        int[][] matrix = {
                {0, 0, 1},
                {1, 1, 0,}
        };

        String[] names = {"Moe", "Larry", "Curly"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix, names);


        dlx solver = new dlx(false);
        solver.search(quadLinkedList);

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

        dlx solver = new dlx(true);
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

        dlx solver = new dlx(false);
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

        dlx knuthDancer = new dlx(false);
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

        dlx knuthDancer = new dlx(false);
        knuthDancer.coverColumn(columnA);
        knuthDancer.uncoverColumn(columnA);

        // After Cover and Uncover.
        assertEquals("A", ((ColumnObject) quadLinkedList.getRoot().getR()).getName());
        assertNotEquals(columnD, columnD.getD().getD());
        assertNotEquals(columnG, columnG.getD().getD().getD());
    }
}