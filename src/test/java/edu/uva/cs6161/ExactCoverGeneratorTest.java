package edu.uva.cs6161;

import edu.uva.cs6161.structures.Enclosure;
import edu.uva.cs6161.utils.PentominoArrays;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
public class ExactCoverGeneratorTest {

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
    public void petominoPossibilities() {
        char[][] board = {
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', '_', '_', 'X', 'X', 'X'},
                {'X', 'X', 'X', '_', '_', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
        };

        Enclosure enclosureBoard = new Enclosure(board);

        List<Enclosure> pentominos = PentominoArrays.ALL_PENTOMINOS
                .stream()
                .map(Enclosure::new)
                .flatMap(x -> x.generateAllVariants().stream())
                .collect(Collectors.toList());

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pentominos, enclosureBoard);

        assertEquals(1568, exactCoverGenerator.generateMatrix());


    }


}