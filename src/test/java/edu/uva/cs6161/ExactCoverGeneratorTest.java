package edu.uva.cs6161;

import edu.uva.cs6161.structures.Enclosure;
import edu.uva.cs6161.structures.Pair;
import edu.uva.cs6161.utils.PentominoArrays;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
public class ExactCoverGeneratorTest {
    private static final char[][] KNUTH_BOARD = {
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', '_', '_', 'X', 'X', 'X'},
            {'X', 'X', 'X', '_', '_', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
    };

    private static final Enclosure KNUTH_BOARD_ENCLOSURE = new Enclosure(KNUTH_BOARD);

    @Test
    public void buildEnclosurePossibilitiesFromBoardTest() {
        char[][] piece = {
                {'X', 'X'},
                {'X', 'X'},
        };

        char[][] piece2 = {
                {'X'},
                {'X'},
        };

        Enclosure enclosure = new Enclosure(piece2);
        Enclosure board = new Enclosure(piece);

        List<Enclosure> pieces = new ArrayList<>();
        pieces.add(enclosure);
        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pieces, board);

        exactCoverGenerator.generateMatrix();

    }


    @Test
    public void testGenerateMatrix() {
        List<Enclosure> pentominos = PentominoArrays.ALL_PENTOMINOS
                .stream()
                .map(Enclosure::new)
                .flatMap(x -> x.generateAllVariants().stream())
                .collect(Collectors.toList());

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pentominos, KNUTH_BOARD_ENCLOSURE);
        exactCoverGenerator.generateMatrix();

        // See page 3. https://arxiv.org/pdf/cs/0011047.pdf
        assertEquals(1568, exactCoverGenerator.getMatrix().length);
    }

    @Test
    public void testGenerateMatrixWithVariants() {
        List<Enclosure> pentominos = PentominoArrays.ALL_PENTOMINOS
                .stream()
                .map(Enclosure::new)
                .collect(Collectors.toList());

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pentominos, KNUTH_BOARD_ENCLOSURE);
        exactCoverGenerator.generateMatrixWithVariants();

        // See page 3. https://arxiv.org/pdf/cs/0011047.pdf
        assertEquals(1568, exactCoverGenerator.getMatrix().length);
    }


    @Test
    public void testIndexMapping() {
        List<Enclosure> enclosures = new ArrayList<>();
        enclosures.add(new Enclosure(PentominoArrays.F));

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(enclosures, KNUTH_BOARD_ENCLOSURE);

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
}