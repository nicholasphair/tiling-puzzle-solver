package edu.uva.cs6161.structures;

import java.util.List;

public class Enclosure {

    public static final char OUTSIDE_CONSTANT = '_';
    private EnclosureCell[][] cells;
    private int length;

    public Enclosure(char[][] tiles) {
        this.length = Math.max(tiles.length, tiles[0].length);
        this.cells = new EnclosureCell[length][length];
        for(int row = 0; row < length; row++) {
            for(int col = 0; col < length; col++) {
                char value;
                if(row >= tiles.length || col >= tiles[0].length) {
                    value = OUTSIDE_CONSTANT;
                } else {
                    value = tiles[row][col];
                }
                cells[row][col] = new EnclosureCell(value, value != OUTSIDE_CONSTANT);
            }
        }
    }


    public EnclosureCell getEnclosureCell(int row, int col) {
        if(row > length - 1 || col > length -1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.cells[row][col];
    }

    public void grow(int size, char value, boolean inside) {
        EnclosureCell[][] newCells = new EnclosureCell[size][size];
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                if(row >= length || col >= length) {
                    newCells[row][col] = new EnclosureCell(value, inside);
                } else {
                    newCells[row][col] = cells[row][col];
                }
            }
        }
        this.cells = newCells;
    }

    public int getLength() {
        return length;
    }


    /**
     * Given a list of enclosures normalize their areas.
     * @param enclosures
     * @return
     */
    public static void normalize(List<Enclosure> enclosures) {
        if(enclosures == null || enclosures.isEmpty()) {
            return;
        }

        int maxSize = Integer.MIN_VALUE;
        for(Enclosure enclosure : enclosures) {
            maxSize = Math.max(enclosure.length, maxSize);
        }

        for(Enclosure enclosure : enclosures) {
            enclosure.grow(maxSize, 'O', false);
        }
    }

    public boolean validPosition(Enclosure enclosure) {
        if(length != enclosure.getLength()) {
            throw new IllegalArgumentException("Enclosures must be of the same size");
        }

        for(int row = 0; row < enclosure.getLength(); row++) {
            for(int col = 0; col < enclosure.getLength(); col++) {
                EnclosureCell effectivePieceCell = enclosure.getEnclosureCell(row, col);
                EnclosureCell effectiveBoardCell = cells[row][col];
                if(effectivePieceCell.inside && !effectiveBoardCell.inside) {
                    return false;
                }
                if(effectivePieceCell.inside && effectiveBoardCell.inside) {
                    if(effectivePieceCell.value != effectiveBoardCell.value) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}