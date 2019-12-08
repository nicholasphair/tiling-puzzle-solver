package edu.uva.cs6161.structures;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuadLinkedListTest {

    @Test
    public void testConstructor() {
        int[][] matrix = {
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 0, 1}
        };
        final int[] expectedSizes = {0, 1, 2, 3};
        final int[] actualSizes = new int[4];

        int i = 0;
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix);
        DataObject obj = quadLinkedList.getRoot();
        while((obj = obj.getR()) != quadLinkedList.getRoot()) {
            actualSizes[i++] = ((ColumnObject) obj).getSize();
        }

        assertArrayEquals(expectedSizes, actualSizes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullArgument() {
        new QuadLinkedList(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameConstructorWithNullArgument() {
        new QuadLinkedList(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyArgument() {
        new QuadLinkedList(new int[0][0]);
    }

    @Test
    public void testConstructorWithColumnNames() {
        String[] names = {"Moe", "Larry", "Curly"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(new int[1][3], names);


        int i = 0;
        DataObject obj = quadLinkedList.getRoot();
        final String[] actualNames = new String[3];
        while((obj = obj.getR()) != quadLinkedList.getRoot()) {
            actualNames[i++] = ((ColumnObject) obj).getName();
        }

        assertArrayEquals(names, actualNames);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithWrongNumberOfNames() {
        String[] names = {"Moe"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(new int[1][3], names);
    }

    @Test
    public void testConstructorDefaultNames() {
        QuadLinkedList quadLinkedList = new QuadLinkedList(new int[1][5]);
        String[] expectedNames = {"A", "B", "C", "D", "E"};

        int i = 0;
        DataObject obj = quadLinkedList.getRoot();
        final String[] actualNames = new String[5];
        while((obj = obj.getR()) != quadLinkedList.getRoot()) {
            actualNames[i++] = ((ColumnObject) obj).getName();
        }

        assertArrayEquals(expectedNames, actualNames);
    }


    @Test
    public void testUpDownLinks() {
        int[][] matrix = {
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 0, 1}
        };

        DataObject obj;
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix);

        DataObject firstColumnHeader = quadLinkedList.getRoot().getR();
        assertEquals(firstColumnHeader, firstColumnHeader.getD());

        DataObject lastColumnHeader = quadLinkedList.getRoot().getL();
        obj = lastColumnHeader;
        for(int i = 0; i < 3; i++) {
            obj = obj.getD();
        }
        assertEquals(lastColumnHeader, obj.getD());
    }

    @Test
    public void testLeftRightLinks() {
        int[][] matrix = {
                {0, 0, 0, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 0}
        };

        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix);

        DataObject secondRow = quadLinkedList.getRoot().getR().getD();
        assertNotEquals(secondRow, secondRow.getR());
        assertEquals(secondRow, secondRow.getR().getR());
    }

    @Test
    public void testIsEmpty() {
        int[][] matrix = {{}};
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix);
        assertTrue(quadLinkedList.isEmpty());
    }

    @Test
    public void testNameToColumnIndex() {
        String[] names = {"A", "B", "C", "D", "E"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(new int[1][5], names);


        assertEquals(0, quadLinkedList.nameToColumnIndex("A"));
        assertEquals(1, quadLinkedList.nameToColumnIndex("B"));
        assertEquals(2, quadLinkedList.nameToColumnIndex("C"));
        assertEquals(3, quadLinkedList.nameToColumnIndex("D"));
        assertEquals(4, quadLinkedList.nameToColumnIndex("E"));
    }


    @Test
    public void part1() {
    }



    @Test(expected = IllegalArgumentException.class)
    public void testNameToColumnIndexThrowsExceptionOnBadName() {
        String[] names = {"A", "B", "C", "D", "E"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(new int[1][5], names);


        assertEquals(0, quadLinkedList.nameToColumnIndex("foobar"));
    }

    @Test
    public void testGetNameOfFirstSetColumn() {
        String[] names = {"A", "B", "C", "D", "E"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(new int[1][5], names);

        int[] nameIsB = {0, 1, 1, 1, 1};
        assertEquals("B", quadLinkedList.getNameOfFirstSetColumn(nameIsB));

        int[] nameIsD = {0, 0, 0, 1, 0};
        assertEquals("D", quadLinkedList.getNameOfFirstSetColumn(nameIsD));
    }

    @Test
    public void solutionRowToCoordinates() {
        int[][] matrix = {
                {1, 0, 0, 1},
                {0, 1, 1, 0}
        };
        String[] colNames = {"PieceA", "PieceB", "X", "Y"};

        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix, colNames);

        int[] expectedCoordinates = {0, 3};
        //int[] actualCoordinates = quadLinkedList.solutionRowToIndices("PieceA Y");
        //assertArrayEquals(expectedCoordinates, actualCoordinates);

        int[] expectedCoordinates2 = {1, 2};
        //int[] actualCoordinates2 = quadLinkedList.solutionRowToIndices("PieceB X");
        //assertArrayEquals(expectedCoordinates2, actualCoordinates2);
    }
}