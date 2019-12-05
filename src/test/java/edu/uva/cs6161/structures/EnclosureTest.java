package edu.uva.cs6161.structures;

import edu.uva.cs6161.utils.PentominoArrays;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EnclosureTest {

    @Test
    public void testCellConstructor() {
        EnclosureCell[][] piece = {
                {new EnclosureCell('0', true), new EnclosureCell('0', true)},
                {new EnclosureCell('X', true), new EnclosureCell('0', true)},
        };

        Enclosure enclosure = new Enclosure(piece);
        assertEquals(2, enclosure.getLength());
        assertArrayEquals(piece, enclosure.getCells());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCellConstructorWithNonSquareMatrix() {
        EnclosureCell[][] piece = {
                {new EnclosureCell('0', true), new EnclosureCell('0', true)},
                {new EnclosureCell('X', true), new EnclosureCell('0', true)},
                {new EnclosureCell('X', true), new EnclosureCell('0', true)},
        };

        Enclosure enclosure = new Enclosure(piece);
    }


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
        assertEquals(4, enclosure.getLength());
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

    @Test
    public void testRotate() {
        char[][] originalPiece = {
                {'O', 'O'},
                {'O', 'X'},
        };

        char[][] expectedRotatedValue = {
                {'O', 'X'},
                {'O', 'O'},
        };

        Enclosure enclosure = new Enclosure(originalPiece);
        enclosure.rotate();

        for(int row = 0; row < enclosure.getLength(); row++) {
            for(int column = 0; column < enclosure.getLength(); column++) {
                assertEquals(expectedRotatedValue[row][column], enclosure.getEnclosureCell(row, column).value);
            }
        }

        enclosure.rotate();
        enclosure.rotate();
        enclosure.rotate();

        for(int row = 0; row < enclosure.getLength(); row++) {
            for(int column = 0; column < enclosure.getLength(); column++) {
                assertEquals(originalPiece[row][column], enclosure.getEnclosureCell(row, column).value);
            }
        }
    }

    @Test
    public void testClone() {
        char[][] piece = {
                {'O', 'X'},
                {'O', 'X'},
        };

        Enclosure original = new Enclosure(piece);
        Enclosure clone = original.clone();

        assertNotSame(original, clone);
        for(int row = 0; row < original.getLength(); row++) {
            for(int column = 0; column < original.getLength(); column++) {
                EnclosureCell originalCell = original.getEnclosureCell(row, column);
                EnclosureCell cloneCell = clone.getEnclosureCell(row, column);
                assertNotSame(originalCell, cloneCell);
                assertEquals(originalCell.value, cloneCell.value);
                assertEquals(originalCell.inside, cloneCell.inside);
            }
        }
    }

    @Test
    public void testReflect() {
        char[][] originalPiece = {
                {'X', 'O'},
                {'O', 'O'},
        };

        char[][] expectedReflectedValue = {
                {'O', 'X'},
                {'O', 'O'},
        };

        Enclosure enclosure = new Enclosure(originalPiece);
        enclosure.reflect();

        for(int row = 0; row < enclosure.getLength(); row++) {
            for(int column = 0; column < enclosure.getLength(); column++) {
                assertEquals(expectedReflectedValue[row][column], enclosure.getEnclosureCell(row, column).value);
            }
        }

        enclosure.reflect();
        for(int row = 0; row < enclosure.getLength(); row++) {
            for(int column = 0; column < enclosure.getLength(); column++) {
                assertEquals(originalPiece[row][column], enclosure.getEnclosureCell(row, column).value);
            }
        }
    }

    @Test
    public void testEquals() {
        char[][] piece = {
                {'O', 'O'},
                {'O', 'X'},
        };

        Enclosure enclosureA = new Enclosure(piece);
        Enclosure enclosureB = new Enclosure(piece);

        assertNotSame(enclosureA, enclosureB);
        assertEquals(enclosureA, enclosureB);
    }

    @Test
    public void testTruncatedEquals() {
        char[][] piece = {
                {'_', 'X'},
                {'_', 'X'},
        };

        char[][] piece2 = {
                {'X', '_'},
                {'X', '_'},
        };

        Enclosure enclosureA = new Enclosure(piece);
        Enclosure enclosureB = new Enclosure(piece2);

        assertNotEquals(enclosureA, enclosureB);
        assertTrue(enclosureA.truncatedEquals(enclosureB));
    }


    @Test
    public void testGenerateAllVariants() {
        Enclosure f = new Enclosure(PentominoArrays.F);
        Enclosure l = new Enclosure(PentominoArrays.L);
        Enclosure n = new Enclosure(PentominoArrays.N);
        Enclosure p = new Enclosure(PentominoArrays.P);
        Enclosure y = new Enclosure(PentominoArrays.Y);
        Enclosure t = new Enclosure(PentominoArrays.T);
        Enclosure u = new Enclosure(PentominoArrays.U);
        Enclosure v = new Enclosure(PentominoArrays.V);
        Enclosure w = new Enclosure(PentominoArrays.W);
        Enclosure z = new Enclosure(PentominoArrays.Z);
        Enclosure i = new Enclosure(PentominoArrays.I);
        Enclosure x = new Enclosure(PentominoArrays.X);

        assertEquals(8, f.generateAllVariants().size());
        assertEquals(8, l.generateAllVariants().size());
        assertEquals(8, n.generateAllVariants().size());
        assertEquals(8, p.generateAllVariants().size());
        assertEquals(8, y.generateAllVariants().size());
        assertEquals(4, t.generateAllVariants().size());
        assertEquals(4, u.generateAllVariants().size());
        assertEquals(4, v.generateAllVariants().size());
        assertEquals(4, w.generateAllVariants().size());
        assertEquals(4, z.generateAllVariants().size());
        assertEquals(2, i.generateAllVariants().size());
        assertEquals(1, x.generateAllVariants().size());
    }

    @Test
    public void testTruncate() {
        char[][] test = {
                {'_', '_'},
                {'X', '_'},
                {'X', '_'},
                {'X', '_'},
                {'X', '_'},
        };

        char[][] expectedTruncatedValue = {
                {'X'},
                {'X'},
                {'X'},
                {'X'},
        };

        Enclosure enclosureTest = new Enclosure(test);
        EnclosureCell[][] truncated = enclosureTest.truncate();

        for (int row = 0; row < truncated.length; row++) {
            for (int col = 0; col < truncated[0].length; col++) {
                assertEquals(expectedTruncatedValue[row][col], truncated[row][col].value);
            }
        }
    }

    @Test
    public void testTruncate2() {
        char[][] test = {
                {'_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_'},
                {'X', 'X', 'X', 'X', 'X'},
        };

        char[][] expectedTruncatedValue = {
                {'X', 'X', 'X', 'X', 'X'},
        };

        Enclosure enclosureTest = new Enclosure(test);
        EnclosureCell[][] truncated = enclosureTest.truncate();

        for (int row = 0; row < truncated.length; row++) {
            for (int col = 0; col < truncated[0].length; col++) {
                assertEquals(expectedTruncatedValue[row][col], truncated[row][col].value);
            }
        }
    }

    @Test
    public void testTruncate3() {
        char[][] piece = {
                {'X', '_'},
                {'X', '_'},
        };

        char[][] expectedTruncatedValue = {
                {'X'},
                {'X'},
        };

        Enclosure enclosureTest = new Enclosure(piece);
        EnclosureCell[][] truncated = enclosureTest.truncate();

        for (int row = 0; row < truncated.length; row++) {
            for (int col = 0; col < truncated[0].length; col++) {
                assertEquals(expectedTruncatedValue[row][col], truncated[row][col].value);
            }
        }
    }

    @Test
    public void testPad() {
        char[][] piece = {
                {'X', 'X'},
                {'X', 'X'},
        };

        char[][] expectedPaddedPiece = {
                {'_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_'},
                {'_', '_', 'X', 'X', '_', '_'},
                {'_', '_', 'X', 'X', '_', '_'},
                {'_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_'},
        };

        Enclosure enclosure = new Enclosure(piece);
        enclosure.pad(2, '_', false);

        assertEquals(new Enclosure(expectedPaddedPiece), enclosure);
        assertEquals(6, enclosure.getLength());
    }

    @Test
    public void testSlice() {
        char[][] piece = {
                {'X', '_', 'X'},
                {'_', 'X', 'X'},
                {'X', 'X', 'X'},
        };

        char[][] expectedTopLeftSlice = {
                {'X', '_'},
                {'_', 'X'},
        };

        char[][] expectedBottomRightSlice = {
                {'X', 'X'},
                {'X', 'X'},
        };


        Enclosure topLeftSliceEnclosure = new Enclosure(expectedTopLeftSlice);
        Enclosure bottomRightSliceEnclosure = new Enclosure(expectedBottomRightSlice);

        Enclosure enclosure = new Enclosure(piece);
        Enclosure actualTopLeftSliceEnclosure = enclosure.slice(0, 2, 0, 2);
        Enclosure actualBottomRightSliceEnclosure = enclosure.slice(1, 3, 1, 3);

        assertEquals(topLeftSliceEnclosure, actualTopLeftSliceEnclosure);
        assertEquals(bottomRightSliceEnclosure, actualBottomRightSliceEnclosure);
    }

}