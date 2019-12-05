package edu.uva.cs6161.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * You might think having these tests is ridiculous. I thought it would be too.
 * It is only once I had wasted hours trying to figure out why my DLX implementation
 * wasn't working only to then realize that I suck at drawing Pentomino arrays did
 * I accept that I NEED these tests.
 */
public class PentominoUtilsTest {

    @Test
    public void testPentominosHaveOnlyFiveXs() {
        for(char[][] pentomino : PentominoUtils.ALL_PENTOMINOS) {
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

    @Test
    public void testFriendlyNames() {
        assertEquals("F", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get(PentominoUtils.F));
        assertEquals("L", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.L));
        assertEquals("N", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.N));
        assertEquals("P", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.P));
        assertEquals("Y", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.Y));
        assertEquals("T", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.T));
        assertEquals("U", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.U));
        assertEquals("V", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.V));
        assertEquals("W", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.W));
        assertEquals("Z", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.Z));
        assertEquals("I", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.I));
        assertEquals("X", PentominoUtils.PENTOMINO_FRIENDLY_NAMES.get( PentominoUtils.X));
    }
}