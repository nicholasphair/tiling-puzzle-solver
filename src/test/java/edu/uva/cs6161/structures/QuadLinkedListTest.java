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
}