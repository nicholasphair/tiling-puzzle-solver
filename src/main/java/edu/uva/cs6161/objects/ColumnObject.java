package edu.uva.cs6161.objects;

public class ColumnObject extends DataObject {
    private int size;
    private String name;

    public ColumnObject() {
        this.size = 0;
        this.name = "";
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void incrementSize() {
        this.size++;
    }

    public void decrementSize() {
        this.size--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
