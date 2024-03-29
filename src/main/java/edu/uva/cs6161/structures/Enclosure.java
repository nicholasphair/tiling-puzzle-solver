package edu.uva.cs6161.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Enclosure {
    public static final char OUTSIDE_CONSTANT = '_';
    private static final char[][] EMPTY_TILES = {{}};
    private static final EnclosureCell[][] EMPTY_ENCLOSURECELL_TILES = {{}};

    private EnclosureCell[][] cells;
    private EnclosureCell[][] truncatedCells;
    private String name;
    private int length;

    public Enclosure(char[][] tiles, String name) {
        this(tiles);
        this.name = name;
    }

    public Enclosure(EnclosureCell[][] tiles, String name) {
        this(tiles);
        this.name = name;
    }

    /**
     * Build an Enclosure object from a 2D char array.
     * @param tiles
     */
    public Enclosure(char[][] tiles) {
        if(!areValidTiles(tiles)) {
            throw new IllegalArgumentException("Tiles cannot be null or empty.");
        }

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
        this.truncatedCells = truncate();
        this.name = "";
    }

    public Enclosure(EnclosureCell[][] tiles) {
        if(!areValidTiles(tiles)) {
            throw new IllegalArgumentException("EnclosureCell tile cannot be null or empty or non-square.");
        }

        this.cells = tiles;
        this.length = tiles.length;
        this.truncatedCells = truncate();
        this.name = "";
    }

    private boolean areValidTiles(char[][] tiles) {
        return !(tiles == null || Arrays.deepEquals(tiles, EMPTY_TILES));
    }

    private boolean areValidTiles(EnclosureCell[][] tiles) {
        return !(tiles == null || Arrays.deepEquals(tiles, EMPTY_ENCLOSURECELL_TILES) || tiles.length != tiles[0].length);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @param startRow inclusive
     * @param endRow exclusive
     * @param startCol inclusive
     * @param endCol exclusive
     * @return
     */
    public Enclosure slice(int startRow, int endRow, int startCol, int endCol) {
        EnclosureCell[][] slice = new EnclosureCell[endRow-startRow][endCol-startCol];
        for(int row = startRow, i = 0; row < endRow; row++, i++) {
            slice[i] = Arrays.copyOfRange(cells[row], startCol, endCol);
        }

        return new Enclosure(slice);
    }

    /**
     * The internal structure holding cell values.
     * @return
     */
    public EnclosureCell[][] getCells() {
        return cells;
    }

    /**
     * A truncated/trimmed view of the structure holding cell values.
     * What this means is any row or columns that are entirely 'OUTSIDE_CONSTANT' values are removed.
     * @return
     */
    public EnclosureCell[][] getTrucatedCells() {
        return truncatedCells;
    }

    /**
     * @param row
     * @param col
     * @return the cell are index (row,col).
     */
    public EnclosureCell getEnclosureCell(int row, int col) {
        if(row > length - 1 || col > length -1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.cells[row][col];
    }

    /**
     *  Grow the internal cells array so that it has an area of size*size.
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

    /**
     * Pad the Enclosure in all directions
     * Usage: well call this w/ piece.size
     * @param padding
     */
    public void pad(int padding, char value, boolean inside) {
        if(padding < 1) {
            return;
        }

        int newSize = 2 * padding + length;
        EnclosureCell[][] newCells = new EnclosureCell[newSize][newSize];
        for(int row = 0; row < newSize; row++) {
            for(int col = 0; col < newSize; col++) {
                newCells[row][col] = new EnclosureCell(value, inside);
            }
        }

        for(int row = 0; row < length; row++) {
            for(int col = 0; col < length; col++) {
                newCells[row+padding][col+padding] = cells[row][col];
            }
        }

        this.cells = newCells;
        this.length = newSize;
        this.truncatedCells = truncate();
    }

    /**
     * @return sqrt(area(cells)).
     */
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
        this.truncatedCells = truncate();
    }

    /**
     *
     * @return
     */
    public void reflect() {
        for(int column = 0; column < cells.length; ++column) {
            EnclosureCell[] tempRow = cells[column];
            for(int row = 0; row < tempRow.length/2; row++) {
                EnclosureCell temp = tempRow[row];
                tempRow[row] = cells[column][tempRow.length-row-1];
                tempRow[tempRow.length-row-1] = temp;
            }
        }
        this.truncatedCells = truncate();
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

        Enclosure e = new Enclosure(cellClone);
        e.setName(name);
        return e;
    }

    /**
     * All possible rotations and reflections of an Enclosure object.
     * @return
     */
    public List<Enclosure> generateAllVariants() {
        List<Enclosure> variants = generateAllRotations();
        Predicate<Enclosure> unique = ((Predicate) variants::contains).negate();

        Enclosure reflection = this.clone();
        reflection.reflect();
        reflection.generateAllRotations()
                .stream()
                .filter(unique)
                .forEach(variants::add);

        return variants;
    }

    private List<Enclosure> generateAllRotations() {
        List<Enclosure> variants = new ArrayList() {
            @Override
            public boolean contains(Object o) {
                if (o == null) {
                    return super.contains(null);
                }
                for(Object obj : this) {
                    Enclosure e = (Enclosure) obj;
                    Enclosure f = (Enclosure) o;
                    if(e.truncatedEquals(f)) {
                        return true;
                    }
                }
                return false;
            }
        };

        variants.add(this);
        Enclosure variant = this.clone();
        variant.rotate();
        while(!variant.truncatedEquals(this)) {
            variants.add(variant);
            variant = variant.clone();
            variant.rotate();
        }

        return variants;
    }

    /**
     * Truncate cells.
     * What this means is any row or columns that are entirely 'OUTSIDE_CONSTANT' values are removed.
     * @return
     */
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

    /**
     * Pretty Print the cells array.
     */
    public void printCells() {
        printCellMatrix(this.cells);
    }

    /**
     * Pretty Print the truncateCells array.
     */
    public void printTruncatedCells() {
        printCellMatrix(this.truncatedCells);
    }

    private static void printCellMatrix(EnclosureCell[][] matrix) {
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col].value + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(cells), name);
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
        EnclosureCell[][] enclosureCells = enclosure.getCells();
        return Arrays.deepEquals(cells, enclosureCells) && enclosure.getName().equals(name);
    }

    public boolean truncatedEquals(Enclosure enclosure) {
        if(this == enclosure) {
            return true;
        }

        if(enclosure == null) {
            return false;
        }

        EnclosureCell[][] enclosureCells = enclosure.getTrucatedCells();
        return Arrays.deepEquals(truncatedCells, enclosureCells) && enclosure.getName().equals(name);
    }
}
