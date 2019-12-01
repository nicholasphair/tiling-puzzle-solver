package edu.uva.cs6161.structures;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnclosureTest {

    @Test
    public void testLengthIsLongestSide() {
        char[][] piece = new char[][] {
                {'O', 'O'},
                {'X', 'O'},
                {'O', 'O'},
        };

        Enclosure boardEnclosure = new Enclosure(piece);
        assertEquals(3, boardEnclosure.getLength());
    }

    @Test
    public void testInsideBoundsIsAValidPosition() {
        char[][] boardPiece = new char[][] {
                {'O', '_', '_'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
        };

        char[][] puzzlePiece = new char[][] {
                {'O', '_', '_'},
                {'O', 'O', '_'},
                {'_', 'O', 'O'},
        };

        Enclosure boardEnclosure = new Enclosure(boardPiece);
        assertTrue(boardEnclosure.validPosition(new Enclosure(puzzlePiece)));
    }

    @Test
    public void testOutsideBoundsIsNotAValidPosition() {
        char[][] puzzlePiece = new char[][] {
                {'O', '_', '_'},
                {'O', 'O', '_'},
                {'_', 'O', 'O'},
        };

        char[][] boardPiece = new char[][] {
                {'_', '_', 'O'},
                {'_', '_', 'O'},
                {'_', '_', 'O'},
        };

        Enclosure boardEnclosure = new Enclosure(boardPiece);
        assertFalse(boardEnclosure.validPosition(new Enclosure(puzzlePiece)));
    }

    @Test
    public void testValuesMustMatchToBeValidPosition() {
        char[][] boardPiece = new char[][] {
                {'O'},
                {'O'},
                {'O'},
        };

        char[][] invalidPuzzlePiece = new char[][] {
                {'O'},
                {'X'},
                {'O'},
        };

        Enclosure boardEnclosure = new Enclosure(boardPiece);
        assertFalse(boardEnclosure.validPosition(new Enclosure(invalidPuzzlePiece)));
    }
}