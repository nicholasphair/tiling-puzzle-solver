package edu.uva.cs6161;

import edu.uva.cs6161.structures.Enclosure;
import edu.uva.cs6161.structures.Pair;
import edu.uva.cs6161.utils.PentominoUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
public class ExactCoverGeneratorTest {
    private static final char[][] PENTOMINO_BOARD = {
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', '_', '_', 'X', 'X', 'X'},
            {'X', 'X', 'X', '_', '_', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    };

    private static final Enclosure PENTOMINO_BOARD_ENCLOSURE = new Enclosure(PENTOMINO_BOARD);

    @Test
    public void testGenerateMatrix1() {
        char[][] boardArray = {
                {'X', 'X'},
                {'X', 'X'},
        };

        char[][] piece = {
                {'X'},
                {'X'},
        };

        final int[][] expectedPossibilities = {
                {1, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1},
        };

        Enclosure enclosure = new Enclosure(piece);
        enclosure.setName("X");
        Enclosure board = new Enclosure(boardArray);

        List<Enclosure> pieces = new ArrayList<>();
        pieces.add(enclosure);
        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pieces, board);

        exactCoverGenerator.generateMatrix();
        assertTrue(Arrays.deepEquals(expectedPossibilities, exactCoverGenerator.getMatrix()));
    }

    // PENTOMINO WITH 1 in first
    @Test
    public void testGenerateMatrix2() {
        char[][] boardArray = {
                {'X', 'X'},
                {'X', 'X'},
        };

        char[][] piece = {
                {'X'},
        };

        final int[][] expectedPossibilities = {
                {1, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 0, 1},
        };

        Enclosure enclosure = new Enclosure(piece);
        enclosure.setName("X");
        Enclosure board = new Enclosure(boardArray);

        List<Enclosure> pieces = new ArrayList<>();
        pieces.add(enclosure);
        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pieces, board);

        exactCoverGenerator.generateMatrix();
        assertTrue(Arrays.deepEquals(expectedPossibilities, exactCoverGenerator.getMatrix()));
    }

    @Test
    public void testGenerateMatrixNoPossibilities() {
        char[][] boardArray = {
                {'X', 'X'},
                {'X', 'X'},
        };

        char[][] piece = {
                {'X', 'X', 'X'},
        };

        final int[][] expectedPossibilities = {
                {1, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 0, 1},
        };

        Enclosure enclosure = new Enclosure(piece);
        Enclosure board = new Enclosure(boardArray);

        List<Enclosure> pieces = new ArrayList<>();
        pieces.add(enclosure);
        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pieces, board);

        exactCoverGenerator.generateMatrix();
        assertEquals(0, exactCoverGenerator.getMatrix().length);
    }

    @Test
    public void testGeneratedPentominoMatrixCounts() {
        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(PentominoUtils.ALL_PENTOMINO_ENCLOSURES, PENTOMINO_BOARD_ENCLOSURE);
        exactCoverGenerator.generateMatrix();

        // See page 3. https://arxiv.org/pdf/cs/0011047.pdf
        int[][] matrix = exactCoverGenerator.getMatrix();
        assertEquals(1568, matrix.length);
        for(int[] row : matrix) {
            assertEquals(72, row.length);
        }

        // Test there is at least a one in every column.
        for(int i = 0; i < matrix[0].length; i++) {
            final int index = i;
            assertNotEquals(0, Arrays.stream(matrix).map(x -> x[index]).filter(x -> x == 1).count());
        }
    }

    @Test
    public void testIndexMapping() {
        List<Enclosure> enclosures = new ArrayList<>();
        enclosures.add(new Enclosure(PentominoUtils.F));

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(enclosures, PENTOMINO_BOARD_ENCLOSURE);

        assertEquals(60, exactCoverGenerator.getIndexMap().size());
        assertFalse(exactCoverGenerator.getIndexMap().containsKey(new Pair(3,3)));
        assertFalse(exactCoverGenerator.getIndexMap().containsKey(new Pair(3,4)));
        assertFalse(exactCoverGenerator.getIndexMap().containsKey(new Pair(4,3)));
        assertFalse(exactCoverGenerator.getIndexMap().containsKey(new Pair(4,4)));

        assertTrue(exactCoverGenerator.getIndexMap().containsKey(new Pair(0,0)));
        assertTrue(exactCoverGenerator.getIndexMap().containsKey(new Pair(0,7)));
        assertTrue(exactCoverGenerator.getIndexMap().containsKey(new Pair(7,0)));
        assertTrue(exactCoverGenerator.getIndexMap().containsKey(new Pair(7,7)));
    }

    @Test
    public void testIndexToCoordinate() {
        List<Enclosure> enclosures = new ArrayList<>();
        enclosures.add(new Enclosure(PentominoUtils.F));

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(enclosures, PENTOMINO_BOARD_ENCLOSURE);

        Pair beforeHolePair = exactCoverGenerator.indexToCoordinate(26);
        assertEquals(3, beforeHolePair.x);
        assertEquals(2, beforeHolePair.y);

        Pair afterHolePair = exactCoverGenerator.indexToCoordinate(27);
        assertEquals(3, afterHolePair.x);
        assertEquals(5, afterHolePair.y);
    }
}