package edu.uva.cs6161;

import edu.uva.cs6161.structures.ColumnObject;
import edu.uva.cs6161.structures.QuadLinkedList;
import org.junit.Test;

import static org.junit.Assert.*;

public class dlxTest {

    @Test
    public void search() {
        //int[][] matrix = {
        //        {0, 0, 1, 0, 1, 1, 0},
        //        {1, 0, 0, 1, 0, 0, 1},
        //        {0, 1, 1, 0, 0, 1, 0},
        //        {1, 0, 0, 1, 0, 0, 0},
        //        {0, 1, 0, 0, 0, 0, 1},
        //        {0, 0, 0, 1, 1, 0, 1}
        //};

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
}