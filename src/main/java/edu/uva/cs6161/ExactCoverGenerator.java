package edu.uva.cs6161;

import edu.uva.cs6161.structures.Enclosure;
import edu.uva.cs6161.structures.Pair;

import java.util.*;

/**
 * Generate a matrix that can be solved by dlx.
 */
public class ExactCoverGenerator {
    private Map<String, Integer> pieceMap;
    private int uniquePieceCount;

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
        this.uniquePieceCount = pieces.size();
        this.pieceMap = generatePieceMap(pieces);
        this.pieces = generatePieceVariants(pieces);
        this.board = board;
        this.possibilities = new ArrayList<>();
        this.indexMap = buildCoordinateToIndexMap();
    }

    private List<Enclosure> generatePieceVariants(List<Enclosure> pieces) {
        List<Enclosure> variants = new ArrayList<>();
        for(Enclosure piece : pieces) {
            for (Enclosure variant : piece.generateAllVariants()) {
                variants.add(variant);
            }
        }

        return variants;
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

    private Map<String, Integer> generatePieceMap(List<Enclosure> pieces) {
        Map<String, Integer> pieceMap = new HashMap<>();

        Integer i = 0;
        for(Enclosure piece : pieces) {
            pieceMap.put(piece.getName(), i++);
        }

        return pieceMap;
    }

    public List<Enclosure> getPieces() {
        return pieces;
    }

    public Enclosure getBoard() {
        return board;
    }

    public Map<Pair, Integer> getIndexMap() {
        return this.indexMap;
    }

    public void generateMatrix() {
        for(Enclosure piece : pieces) {
            addPossibilities(piece);
        }
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
        int[] rowPossibility = new int[uniquePieceCount + indexMap.size()];

        for(int row = 0; row < piece.getLength(); row++) {
            for(int col = 0; col < piece.getLength(); col++) {
                if(piece.getEnclosureCell(row, col).value != Enclosure.OUTSIDE_CONSTANT) {
                    Pair key = new Pair(startRow + row, startCol + col);
                    rowPossibility[uniquePieceCount + indexMap.get(key)] = 1;
                    rowPossibility[pieceMap.get(piece.getName())] = 1;
                }
            }
        }

        return rowPossibility;
    }

    public int[][] getMatrix() {
        int[][] matrix = new int[possibilities.size()][uniquePieceCount + indexMap.size()];
        for(int i = 0; i < possibilities.size(); i++) {
            matrix[i] = possibilities.get(i);
        }

        return matrix;
    }


    /**
     * Given an index into the board, return the corresponding x,y Pair.
     * @param index
     * @return
     */
    public Pair indexToCoordinate(Integer index) {
        for (Map.Entry<Pair, Integer> entry : indexMap.entrySet()) {
            if(entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }

}
