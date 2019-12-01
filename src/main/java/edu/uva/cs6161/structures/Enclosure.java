package edu.uva.cs6161.structures;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     *
     * @param row
     * @param col
     * @return
     */
    public EnclosureCell getEnclosureCell(int row, int col) {
        if(row > length - 1 || col > length -1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.cells[row][col];
    }

    /**
     *
     * @param size
     * @param value
     * @param inside
     */
    public void grow(int size, char value, boolean inside) {
        if(size < length) {
            return;
        }

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
        this.length = size;
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

    /**
     *
     * @param enclosure
     * @return
     */
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

    /**
     * Rotates the enclosure 90 degrees counterclockwise.
     */
    public void rotate() {
        for(int i = 0; i < length/2; i++) {
            for(int j = i; j < length-i-1; j++) {
                EnclosureCell temp = getEnclosureCell(i, j);
                cells[i][j] = cells[j][length-1-i];
                cells[j][length-1-i] = cells[length-1-i][length-1-j];
                cells[length-1-i][length-1-j] = cells[length-1-j][i];
                cells[length-1-j][i] = temp;
            }
        }
    }

    /**
     *
     * @return
     */
    public void reflect() {
        for(int column = 0; column < cells.length; ++column) {  // Extra for loop to go through each row in turn, performing the reversal within that row.
            EnclosureCell[] tempRow = cells[column];
            for(int row = 0; row < tempRow.length/2; row++) {
                EnclosureCell temp = tempRow[row];
                tempRow[row] = cells[column][tempRow.length-row-1];
                tempRow[tempRow.length-row-1] = temp;
            }
        }
    }

    /**
     *
     * @return
     */
    public Enclosure clone() {
        char[][] cellClone = new char[length][length];
        for(int row = 0; row < length; row++) {
            for(int col = 0; col < length; col++) {
                cellClone[row][col] = getEnclosureCell(row, col).value;
            }
        }
        return new Enclosure(cellClone);
    }

    public List<Enclosure> generateAllVariants() {
        List<Enclosure> variants = generateAllRotations();

        Enclosure reflection = this.clone();
        reflection.reflect();
        List<Enclosure> reflectedVariants = reflection.generateAllRotations();
        reflectedVariants.stream().filter(x -> !variants.contains(x)).forEach(variants::add);

        return variants;
    }

    private List<Enclosure> generateAllRotations() {
        List<Enclosure> variants = new ArrayList<>();
        Enclosure variant = this.clone();

        variant.rotate();

        while(!variant.equals(this)) {
            if(!variants.contains(variant)) {
                variants.add(variant);
            }
            variant = variant.clone();
            variant.rotate();
        }

        variants.add(this);
        return variants;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(cells);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Enclosure enclosure = (Enclosure) obj;
        if(this.length != enclosure.getLength()) {
            return false;
        }

        for(int row = 0; row < length; row++) {
            for(int col = 0; col < length; col++) {
                EnclosureCell enclosureCell = enclosure.getEnclosureCell(row, col);
                EnclosureCell thisCell = getEnclosureCell(row, col);
                if(enclosureCell.value != thisCell.value || enclosureCell.inside != thisCell.inside) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prints the enclosure's cell values.
     */
    public void printEnclosure() {
        for(int row = 0; row < length; row++) {
            for(int col = 0; col < length; col++) {
                System.out.print(getEnclosureCell(row, col).value + " ");
            }
            System.out.println();
        }
    }

    public EnclosureCell[][] truncate() {
        List<Integer> validRows = new ArrayList<>();
        for(int row = 0; row < length; row++) {
            if(!truncatable(cells[row])) {
                validRows.add(row);
            }
        }

        List<Integer> validColumns = new ArrayList<>();
        for(int column = 0; column < length; column++) {
            EnclosureCell[] c = new EnclosureCell[length];
            for(int row = 0; row < length; row++) {
                c[row] = cells[row][column];
            }

            if(!truncatable(c)) {
                validColumns.add(column);
            }
        }

        EnclosureCell[][] truncated = new EnclosureCell[validRows.size()][validColumns.size()];
        for(int row = 0; row < validRows.size(); row++) {
            for(int column = 0; column < validColumns.size(); column++) {
                truncated[row][column] = cells[validRows.get(row)][validColumns.get(column)];
            }
        }

        return truncated;
    }

    private boolean truncatable(EnclosureCell[] enclosureCells) {
        for (EnclosureCell e: enclosureCells) {
            if(e.value != Enclosure.OUTSIDE_CONSTANT) {
                return false;
            }
        }
        return true;
    }
}
