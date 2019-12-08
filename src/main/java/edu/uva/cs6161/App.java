package edu.uva.cs6161;

import edu.uva.cs6161.fileparser.InputFileParser;
import edu.uva.cs6161.handlers.CollectSolutionsHandler;
import edu.uva.cs6161.structures.Enclosure;
import edu.uva.cs6161.structures.EnclosureCell;
import edu.uva.cs6161.structures.Pair;
import edu.uva.cs6161.structures.QuadLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static List<String[][]> uniqueSolutions(List<String[][]> solutions) {
        List<String[][]> seenSolutions = new ArrayList() {
            @Override
            public boolean contains(Object o) {
                if (o == null) {
                    return super.contains(null);
                }
                for(Object obj : this) {
                    String[][] a = (String[][]) obj;
                    String[][] b = (String[][]) o;
                    if(Arrays.deepEquals(a, b)) {
                        return true;
                    }
                }
                return false;
            }
        };

        List<String[][]> uniqueSolutions = new ArrayList<>();
        for(String[][] solution : solutions) {
            if(!seenSolutions.contains(solution)) {
                generateAllVariants(solution).forEach(seenSolutions::add);
                uniqueSolutions.add(solution);
            }
        }
        return uniqueSolutions;
    }

    public static List<String[][]> generateAllVariants(String[][] solution) {
        char[][] s = new char[solution.length][solution[0].length];
        for(int i = 0; i < s.length; i++) {
            for(int j = 0; j < solution[0].length; j++) {
                s[i][j] = solution[i][j].toCharArray()[0];
            }
        }

        Enclosure enclosure = new Enclosure(s);

        List<String[][]> variants = new ArrayList<>();
        for(Enclosure e : enclosure.generateAllVariants()) {
            EnclosureCell[][] enclosureCells = e.getTrucatedCells();
            String[][] variant = new String[enclosureCells.length][enclosureCells[0].length];
            for(int i = 0; i < enclosureCells.length; i++) {
                for(int j = 0; j < enclosureCells[0].length; j++) {
                    variant[i][j] = Character.toString(enclosureCells[i][j].value);
                }
            }
            variants.add(variant);
        }
        return variants;
    }


    public List<String[][]> run(String filename, boolean optional) {
        InputFileParser inputFileParser = new InputFileParser(filename);
        List<char[][]> piecesAndBoard= inputFileParser.getInputPieces();
        int largestIndex = InputFileParser.identifyLargest(piecesAndBoard);

        Enclosure board = new Enclosure(piecesAndBoard.remove(largestIndex));
        List<Enclosure> pieces = piecesAndBoard.stream().map(Enclosure::new).collect(Collectors.toList());

        for(int i = 0; i < pieces.size(); i++) {
            Enclosure e = pieces.get(i);
            e.setName(Integer.toString(i));
        }

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pieces, board);

        exactCoverGenerator.generateMatrix();
        int[][] matrix = exactCoverGenerator.getMatrix();

        CollectSolutionsHandler collector = new CollectSolutionsHandler();
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix);
        if(optional) {
            quadLinkedList.setOptionalColumns(matrix[0].length - pieces.size(), pieces.size());
        }

        DLX dlx = new DLX(true, collector);
        dlx.search(quadLinkedList);
        List<String> solutions = collector.collect();

        EnclosureCell[][] btc = board.getTrucatedCells();
        List<String[][]> allSolutions = new ArrayList<>();

        for(String solution : solutions) {
            String[][] output = new String[btc.length][btc[0].length];
            deepFill(output, "_");

            String[] solutionRows = solution.split("\n");
            for(String solutionRow: solutionRows) {
                String name = "";
                List<Pair> solutionRowPairs = new ArrayList<>();
                for(String columnName : solutionRow.split(" ")) {
                    int flatIndex = quadLinkedList.nameToColumnIndex(columnName);
                    if(flatIndex < pieces.size()) {
                        name = columnName;
                    } else {
                        int boardIndex = flatIndex - pieces.size();
                        Pair p = exactCoverGenerator.indexToCoordinate(boardIndex);
                        solutionRowPairs.add(p);
                    }
                }

                for(Pair p : solutionRowPairs) {
                    output[p.x][p.y] = name;
                }
            }

            allSolutions.add(output);
        }
        return allSolutions;
    }

    private static void deepFill(String[][] output, String filler) {
        for(int i = 0; i < output.length; i++)  {
            for(int j = 0; j < output[0].length; j++) {
                output[i][j] = filler;
            }
        }
    }
}
