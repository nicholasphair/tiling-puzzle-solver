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