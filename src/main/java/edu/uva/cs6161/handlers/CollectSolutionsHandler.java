package edu.uva.cs6161.handlers;

import edu.uva.cs6161.structures.ColumnObject;
import edu.uva.cs6161.structures.DataObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collects solutions (represented as String) to the exact cover problem into a List.
 */
public class CollectSolutionsHandler implements SolutionsHandler {
    private List<String> solutions;

    public CollectSolutionsHandler() {
        this.solutions = new ArrayList<>();
    }

    public List<String> collect() {
        if(solutions.isEmpty()) {
            return Collections.emptyList();
        }
        return this.solutions;
    }

    @Override
    public void handle(List<DataObject> solutions) {
        StringBuilder builder = new StringBuilder();
        for(DataObject o : solutions) {
            DataObject x = o;
            do {
                String name = ((ColumnObject) x.getC()).getName();
                builder.append(name + " ");
            } while((x = x.getR()) != o);
            builder.replace(builder.length() - 1, builder.length(), "\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        this.solutions.add(builder.toString());
    }
}
