package edu.uva.cs6161.utils;

import edu.uva.cs6161.ExactCoverGenerator;
import edu.uva.cs6161.structures.ColumnObject;
import edu.uva.cs6161.structures.EnclosureCell;
import edu.uva.cs6161.structures.Pair;
import edu.uva.cs6161.structures.QuadLinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleUtils {
    private Map<String, Integer> nameIndicies;
    private QuadLinkedList quadLinkedList;
    private ExactCoverGenerator exactCoverGenerator;


    public PuzzleUtils(QuadLinkedList quadLinkedList, ExactCoverGenerator exactCoverGenerator) {
        this.quadLinkedList = quadLinkedList;
        this.exactCoverGenerator = exactCoverGenerator;
        this.nameIndicies = new HashMap<>();
        initNameIndexMap();
    }

    private void initNameIndexMap() {
        ColumnObject root = this.quadLinkedList.getRoot();

        int i = 0;
        ColumnObject current = root;
        while((current = (ColumnObject) current.getR()) != root) {
            nameIndicies.put(current.getName(), i++);
        }
    }

    public int nameToColumnIndex(String name) {
        return nameIndicies.get(name);
    }
}
