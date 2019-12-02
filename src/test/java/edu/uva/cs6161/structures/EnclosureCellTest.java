package edu.uva.cs6161.structures;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnclosureCellTest {

    @Test
    public void testEquals() {
        EnclosureCell a = new EnclosureCell('x', false);
        EnclosureCell b = new EnclosureCell('x', false);
        assertNotSame(a, b);
        assertEquals(a, b);

        b.value = 'y';
        assertNotEquals(a, b);
    }
}