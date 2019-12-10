package edu.uva.cs6161.fileparser;

import com.google.common.io.Resources;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InputFileParserTest {

    @Test
    public void getInputTiles() {
        String filename = Resources.getResource("input1.txt").getPath();
        InputFileParser inputFileParser = new InputFileParser(filename);

        assertEquals(13, inputFileParser.getInputPieces().size());

        String pentominos = Resources.getResource("pentominoes3x20").getPath();
        inputFileParser = new InputFileParser(pentominos);

        assertEquals(13, inputFileParser.getInputPieces().size());
    }

    @Test
    public void getInputTilesLucky13() {
        String filename = Resources.getResource("lucky13").getPath();
        InputFileParser inputFileParser = new InputFileParser(filename);

        for(char[][] a : inputFileParser.getInputPieces()) {
            for(char[] r : a) {
                for(char c : r) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
        assertEquals(14, inputFileParser.getInputPieces().size());
    }

    @Test
    public void getInputTilesLuckyIQ_creator() {
        String filename = Resources.getResource("IQ_creator").getPath();
        InputFileParser inputFileParser = new InputFileParser(filename);

        assertEquals(9, inputFileParser.getInputPieces().size());
    }

    @Test
    public void identifyLargest() {
        char[][] small = {{'X', 'O'}};

        char[][] large = {
                {'X', 'X', 'O'},
                {'X', 'X', 'O'},
                {'X', 'X', 'O'},
                {'X', 'X', 'O'},
        };

        char[][] largeWithUnderscores = {
                {'_', 'X', '_'},
                {'_', 'X', '_'},
                {'_', 'X', '_'},
                {'_', 'X', '_'},
        };

        List<char[][]> pieces = new ArrayList<>();
        pieces.add(small);
        pieces.add(large);
        pieces.add(largeWithUnderscores);

        assertEquals(1, InputFileParser.identifyLargest(pieces));
    }
}