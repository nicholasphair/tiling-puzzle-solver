package edu.uva.cs6161;

import edu.uva.cs6161.structures.Enclosure;
import edu.uva.cs6161.structures.Pair;

import java.util.*;

/**
 * Generate a matrix that can be solved by dlx.
 */
public class ExactCoverGenerator {
    private List<Enclosure> pieces;
    private Enclosure board;
    private List<int[]> possibilities;
    private Map<Pair, Integer> indexMap;

    /**
     *
     * @param pieces the pieces to the puzzle, do not include variants.
     * @param board
     */
    public ExactCoverGenerator(List<Enclosure> pieces, Enclosure board) {
        this.pieces = pieces;
        this.board = board;
        this.possibilities = new ArrayList<>();
        this.indexMap = buildCoordinateToIndexMap();
    }

    private Map<Pair, Integer> buildCoordinateToIndexMap() {
        Map<Pair, Integer> map = new HashMap<>();

        Integer i = 0;
        for(int row = 0; row < board.getLength(); row++) {
            for(int col = 0; col < board.getLength(); col++) {
                if(board.getEnclosureCell(row, col).value != Enclosure.OUTSIDE_CONSTANT) {
                    map.put(new Pair(row, col), i++);
                }
            }
        }
        return map;
    }

    public Map<Pair, Integer> getIndexMap() {
        return this.indexMap;
    }


    public void generateMatrix() {
        for(Enclosure piece : pieces) {
            addPossibilities(piece);
        }
    }

    public void generateMatrixWithVariants() {
        for(Enclosure piece : pieces) {
            for(Enclosure variant : piece.generateAllVariants()) {
                addPossibilities(variant);
            }
        }
    }

    public int[][] getMatrix() {
        int[][] matrix = new int[possibilities.size()][pieces.size() + indexMap.size()];
        for(int i = 0; i < possibilities.size(); i++) {
            matrix[i] = possibilities.get(i);
        }
        return matrix;
    }

    /**
     *
     * @param piece
     */
    public void addPossibilities(Enclosure piece) {
        Enclosure paddedBoard = board.clone();
        paddedBoard.pad(piece.getLength(), Enclosure.OUTSIDE_CONSTANT, false);

        int pieceLength = piece.getLength();
        int paddedBoardLength = paddedBoard.getLength();
        for(int startRow = 0, endRow = pieceLength; endRow < paddedBoardLength + 1; startRow++, endRow++) {
            for(int startCol = 0, endCol = pieceLength; endCol < paddedBoardLength + 1; startCol++, endCol++) {
                Enclosure boardSlice = paddedBoard.slice(startRow, endRow, startCol, endCol);
                if(boardSlice.validPosition(piece)) {
                    int r = startRow - pieceLength;
                    int c = startCol - pieceLength;
                    possibilities.add(mapToCells(piece, r, c));
                }
            }
        }
    }

    public int[] mapToCells(Enclosure piece, int startRow, int startCol) {
        int[] rowPossibility = new int[pieces.size() + indexMap.size()];

        System.out.println(piece.getName());
        for(int row = 0; row < piece.getLength(); row++) {
            for(int col = 0; col < piece.getLength(); col++) {
                if(piece.getEnclosureCell(row, col).value != Enclosure.OUTSIDE_CONSTANT) {
                    Pair key = new Pair(startRow + row, startCol + col);
                    rowPossibility[indexMap.get(key)] = 1;
                }
            }
        }

        //System.out.println();
        //for(int i : rowPossibility) {
        //    System.out.print(i + " ");
        //}
        //System.out.println();
        //System.out.println();


        return rowPossibility;
    }
}
