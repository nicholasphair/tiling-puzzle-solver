package edu.uva.cs6161.structures;

import java.util.Objects;

public class EnclosureCell {
    public static final char DEFAULT_VALUE = '_';

    public char value;
    public boolean inside;

    public EnclosureCell(char value, boolean inside) {
        this.value = value;
        this.inside = inside;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, inside);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        EnclosureCell cell = (EnclosureCell) obj;
        return value == cell.value && inside == cell.inside;
    }
}
