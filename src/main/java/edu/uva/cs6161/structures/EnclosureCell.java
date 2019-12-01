package edu.uva.cs6161.structures;

public class EnclosureCell {
    public static final char DEFAULT_VALUE = '_';

    public char value ;
    public boolean inside;

    public EnclosureCell(char value, boolean inside) {
        this.value = value;
        this.inside = inside;
    }
}
