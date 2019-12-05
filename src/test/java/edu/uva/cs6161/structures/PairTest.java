package edu.uva.cs6161.structures;

import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

    @Test
    public void testEquals() {
        Pair pairA = new Pair(1, 1);
        Pair pairB = new Pair(1, 1);
        Pair pairC = new Pair(2, 2);

        assertNotSame(pairA, pairB);
        assertEquals(pairA, pairB);
        assertNotEquals(pairA, pairC);
    }

    @Test
    public void testHashCode() {
        Pair pairA = new Pair(1, 1);
        Pair pairB = new Pair(1, 1);
        Pair pairC = new Pair(2, 2);

        assertEquals(pairA.hashCode(), pairB.hashCode());
        assertNotEquals(pairA.hashCode(), pairC.hashCode());
    }
}