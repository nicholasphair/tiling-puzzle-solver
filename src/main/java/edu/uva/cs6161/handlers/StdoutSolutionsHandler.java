package edu.uva.cs6161.handlers;

import edu.uva.cs6161.structures.ColumnObject;
import edu.uva.cs6161.structures.DataObject;

import java.util.List;

/**
 * Prints solutions to the exact cover problem to STDOUT.
 */
public class StdoutSolutionsHandler implements SolutionsHandler {

    @Override
    public void handle(List<DataObject> solutions) {
        for(DataObject o : solutions) {
            DataObject x = o;
            do {
                String name = ((ColumnObject) x.getC()).getName();
                System.out.print(name + " ");
            } while((x = x.getR()) != o);
            System.out.println();
        }
        System.out.println();
    }
}
