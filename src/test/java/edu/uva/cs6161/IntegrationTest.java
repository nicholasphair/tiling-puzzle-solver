package edu.uva.cs6161;
import edu.uva.cs6161.handlers.CollectSolutionsHandler;
import edu.uva.cs6161.structures.Enclosure;
import edu.uva.cs6161.structures.EnclosureCell;
import edu.uva.cs6161.structures.Pair;
import edu.uva.cs6161.structures.QuadLinkedList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class IntegrationTest {

    @Test
    public void testColumnNameToMatrixIndex() {
        char[][] board = {
                {'X', 'X'},
                {'X', 'X'}
        };

        char[][] piece1 = {
                {'_', 'X'},
                {'X', 'X'}
        };

        char[][] piece2 = {
                {'X'}
        };

        Enclosure boardEnclosure = new Enclosure(board);
        Enclosure piece1Enclosure = new Enclosure(piece1, "A");
        Enclosure piece2Enclosure = new Enclosure(piece2, "B");
        List<Enclosure> pieces = new ArrayList<>();
        pieces.add(piece1Enclosure);
        pieces.add(piece2Enclosure);

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pieces, boardEnclosure);
        exactCoverGenerator.generateMatrix();
        int[][] matrix = exactCoverGenerator.getMatrix();


        String[] colNames = {"A", "B", "0", "1", "2", "3"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix, colNames);


        assertEquals(0, quadLinkedList.nameToColumnIndex("A"));
        assertEquals(1, quadLinkedList.nameToColumnIndex("B"));
        assertEquals(2, quadLinkedList.nameToColumnIndex("0"));
        assertEquals(3, quadLinkedList.nameToColumnIndex("1"));
        assertEquals(4, quadLinkedList.nameToColumnIndex("2"));
        assertEquals(5, quadLinkedList.nameToColumnIndex("3"));
    }

    @Test
    public void testColumnNameToBoardIndex() {
        char[][] board = {
                {'X', 'X'},
                {'X', 'X'}
        };

        char[][] piece1 = {
                {'_', 'X'},
                {'X', 'X'}
        };

        char[][] piece2 = {
                {'X'}
        };

        Enclosure boardEnclosure = new Enclosure(board);
        Enclosure piece1Enclosure = new Enclosure(piece1, "A");
        Enclosure piece2Enclosure = new Enclosure(piece2, "B");
        List<Enclosure> pieces = new ArrayList<>();
        pieces.add(piece1Enclosure);
        pieces.add(piece2Enclosure);

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pieces, boardEnclosure);
        exactCoverGenerator.generateMatrix();
        int[][] matrix = exactCoverGenerator.getMatrix();


        String[] colNames = {"A", "B", "0", "1", "2", "3"};
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix, colNames);

        CollectSolutionsHandler collectSolutionsHandler = new CollectSolutionsHandler();
        DLX dlx = new DLX(true, collectSolutionsHandler);
        dlx.search(quadLinkedList);
        List<String> solutions = collectSolutionsHandler.collect();

        EnclosureCell[][] btc = boardEnclosure.getTrucatedCells();

        List<String[][]> allSolutions = new ArrayList<>();

        for(String solution : solutions) {
            String[][] output = new String[btc.length][btc[0].length];

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

        //for(String[][] output : allSolutions) {
        //    System.out.println("======");
        //     for(int i = 0; i < output.length; i++)  {
        //         for(int j = 0; j < output[0].length; j++) {
        //             System.out.print(output[i][j] + " ");
        //         }
        //         System.out.println();
        //     }
        //}
    }
}
