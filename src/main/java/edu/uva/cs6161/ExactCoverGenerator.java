package edu.uva.cs6161;

import edu.uva.cs6161.structures.Enclosure;
import edu.uva.cs6161.structures.EnclosureCell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generate a matrix that can be solved by dlx.
 */
public class ExactCoverGenerator {
    private List<Enclosure> pieces;
    private Enclosure board;
    private List<Enclosure> possibilities;

    public ExactCoverGenerator(List<Enclosure> pieces, Enclosure board) {
        this.pieces = pieces;
        this.board = board;
        this.possibilities = new ArrayList<>();
    }

    public int generateMatrix() {
        int[][] exactCoverMatrix;
        for(Enclosure piece : pieces) {
            buildEnclosurePossibilitiesFromBoard(piece);
            //break;
        }

        //int validCellCount = countValidCells();

        //exactCoverMatrix = new int[possibilities.size()][pieces.size()+validCellCount];
        //System.out.println(possibilities.size());
        return possibilities.size();
    }

    public List<Enclosure> getPossibilitiesAsEnclosures() {
        return this.possibilities;
    }


    private int countValidCells() {
        int count = 0;
        for(EnclosureCell[] row : board.getCells()) {
            for(EnclosureCell col : row) {
                count += col.value == '_' ? 0 : 1;
            }
        }
        return count;
    }

    public void buildEnclosurePossibilitiesFromBoard(Enclosure piece) {
        Enclosure paddedBoard = board.clone();
        paddedBoard.pad(piece.getLength(), '_', false);

        for(int startRow = 0, endRow = piece.getLength(); endRow < paddedBoard.getLength() + 1; startRow++, endRow++) {
            for(int startCol = 0, endCol = piece.getLength(); endCol < paddedBoard.getLength() + 1; startCol++, endCol++) {
                Enclosure boardSlice = paddedBoard.slice(startRow, endRow, startCol, endCol);
                if(boardSlice.validPosition(piece)) {
                    possibilities.add(boardSlice);
                    //System.out.println("Is valid on board slice");

                } else {
                    //System.out.println("====================");
                    //System.out.println();
                    //Enclosure e = paddedBoard.clone();
                    //e.getCells()[startRow][startCol].value = '*';
                    //e.getCells()[startRow][endCol-1].value = '*';
                    //e.getCells()[endRow-1][startCol].value = '*';
                    //e.getCells()[endRow-1][endCol-1].value = '*';
                    //e.printCells();
                    //System.out.println();
                    //System.out.println();

                    //System.out.println("Piece: ");
                    //piece.printCells();
                    //System.out.println();
                    //System.out.println("Is NOT valid on board slice");
                    //boardSlice.printCells();

                    //System.out.println();
                    //System.out.println("++++++++++++++++++++");
                    //System.out.println();
                }

            }
        }
    }
}
