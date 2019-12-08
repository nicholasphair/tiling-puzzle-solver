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


        //int a = quadLinkedList.nameToColumnIndex("A");
        int zero = quadLinkedList.nameToColumnIndex("0");
        int zeroOnBoardIndex = zero - pieces.size();
        Pair pair = exactCoverGenerator.indexToCoordinate(zeroOnBoardIndex);
        //System.out.println(pair.x);
        //System.out.println(pair.y);

        int three = quadLinkedList.nameToColumnIndex("3");
        int threeBoardIndex = three - pieces.size();
        Pair anotherPair = exactCoverGenerator.indexToCoordinate(threeBoardIndex);
        //System.out.println(anotherPair.x);
        //System.out.println(anotherPair.y);

        EnclosureCell[][] btc = boardEnclosure.getTrucatedCells();

       for(String solution : solutions) {
           String[][] output = new String[btc.length][btc[0].length];

           String[] solutionRows = solution.split("\n");
           for(String solutionRow: solutionRows) {
               String name = "";
               //System.out.println("======");
               List<Pair> solutionRowPairs = new ArrayList<>();
               for(String columnName : solutionRow.split(" ")) {
                   // whatabout pieces
                   int flatIndex = quadLinkedList.nameToColumnIndex(columnName);
                   if(flatIndex < pieces.size()) {
                       //Sys
                       name = columnName;
                       //this is a piece
                   } else {
                       int boardIndex = flatIndex - pieces.size();
                       Pair p = exactCoverGenerator.indexToCoordinate(boardIndex);
                       solutionRowPairs.add(p);
                       //System.out.println("x: " + p.x + " y: " + p.y + " ");
                   }

               }

               for(Pair p : solutionRowPairs) {
                   output[p.x][p.y] = name;
               }
               //System.out.println("PIECE IS " + name);
               //System.out.println("======");
           }

           System.out.println("======");
            for(int i = 0; i < output.length; i++)  {
                for(int j = 0; j < output[0].length; j++) {
                    System.out.print(output[i][j] + " ");
                }
                System.out.println();
            }
           System.out.println("======");
           System.out.println();

       }
       //     exactCoverGenerator.
       //     System.out.println(solution);
       //     System.out.println();
       // }

    }
}
