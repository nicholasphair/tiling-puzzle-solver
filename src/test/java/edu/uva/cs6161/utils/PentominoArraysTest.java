package edu.uva.cs6161.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PentominoArraysTest {

    @Test
    public void testPentominosHaveOnlyFiveXs() {
        for(char[][] pentomino : PentominoArrays.ALL_PENTOMINOS) {
            assertEquals(5, countPentominoXs(pentomino));
        }
    }

    private int countPentominoXs(char[][] pentomino) {
        int sum = 0;
        for(char[] rows : pentomino) {
            for(char column : rows) {
                sum += column == 'X' ? 1 : 0;
            }
        }
        return sum;
    }

}