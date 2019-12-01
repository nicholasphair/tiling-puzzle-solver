package edu.uva.cs6161.structures;

public class EnclosureCell {
    private static final char DEFAULT_VALUE = '1';

    public char value ;
    public boolean inside;

    public EnclosureCell(char value, boolean inside) {
        this.value = value;
        this.inside = inside;
    }
}
