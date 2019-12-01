package edu.uva.cs6161.structures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EnclosureTest {

    @Test
    public void testLengthIsLongestSide() {
        char[][] piece = {
                {'O', 'O'},
                {'X', 'O'},
                {'O', 'O'},
        };

        Enclosure boardEnclosure = new Enclosure(piece);
        assertEquals(3, boardEnclosure.getLength());
    }

    @Test
    public void testEnclosuresAreSquares() {
        char[][] piece = {
                {'O', 'O'},
                {'O', 'O'},
                {'_', 'O'},
        };

        char[][] expectedValues = {
                {'O', 'O', '_'},
                {'O', 'O', '_'},
                {'_', 'O', '_'},
        };

        Enclosure enclosure = new Enclosure(piece);
        for(int row = 0; row < enclosure.getLength(); row++) {
            for(int col = 0; col < enclosure.getLength(); col++) {
                assertEquals(expectedValues[row][col], enclosure.getEnclosureCell(row, col).value);
            }
        }
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
        char[][] boardPiece = {
                {'O'},
                {'O'},
                {'O'},
        };

        char[][] invalidPuzzlePiece = {
                {'O'},
                {'X'},
                {'O'},
        };

        Enclosure boardEnclosure = new Enclosure(boardPiece);
        assertFalse(boardEnclosure.validPosition(new Enclosure(invalidPuzzlePiece)));
    }

    @Test
    public void testGrow() {
        char[][] boardPiece = {
                {'O', 'O'},
                {'O', 'O'},
                {'_', 'O'},
        };

        char[][] expectedValues = {
                {'O', 'O', '_', '_'},
                {'O', 'O', '_', '_'},
                {'_', 'O', '_', '_'},
                {'_', '_', '_', '_'},
        };


        Enclosure enclosure = new Enclosure(boardPiece);
        enclosure.grow(4, EnclosureCell.DEFAULT_VALUE, false);

        for(int row = 0; row < enclosure.getLength(); row++) {
            for(int col = 0; col < enclosure.getLength(); col++) {
                assertEquals(expectedValues[row][col], enclosure.getEnclosureCell(row, col).value);
            }
        }
    }

    @Test
    public void testNormalize() {
        char[][] smallerPiece = {
                {'O'},
                {'O'},
        };

        char[][] largerPiece = {
                {'O', 'O'},
                {'O', 'O'},
                {'_', 'O'},
        };

        Enclosure smallerEnclosure = new Enclosure(smallerPiece);
        Enclosure largerEnclosure = new Enclosure(largerPiece);

        List<Enclosure> enclosures = new ArrayList<>();
        enclosures.add(smallerEnclosure);
        enclosures.add(largerEnclosure);

        // Before Normalizing.
        assertEquals(2, smallerEnclosure.getLength());
        assertEquals(3, largerEnclosure.getLength());

        Enclosure.normalize(enclosures);

        // After Normalizing.
        assertEquals(3, smallerEnclosure.getLength());
        assertEquals(3, largerEnclosure.getLength());
    }
}