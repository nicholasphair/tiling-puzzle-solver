package edu.uva.cs6161;

import edu.uva.cs6161.handlers.SolutionsHandler;
import edu.uva.cs6161.handlers.StdoutSolutionsHandler;
import edu.uva.cs6161.structures.ColumnObject;
import edu.uva.cs6161.structures.DataObject;
import edu.uva.cs6161.structures.QuadLinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm X implemented with Dancing Links. Known as DLX.
 */
public class DLX {

    private boolean minimizeBranchingFactor;
    private SolutionsHandler handler;

    public DLX() {
        this(false, new StdoutSolutionsHandler());
    }

    public DLX(boolean minimizeBranchingFactor, SolutionsHandler handler) {
        this.minimizeBranchingFactor = minimizeBranchingFactor;
        this.handler = handler;
    }

    /**
     * Finds all solutions to the exact cover problem.
     * @param matrix
     */
    public void search(int[][] matrix) {
        search(new QuadLinkedList(matrix), 0, new ArrayList<>());
    }

    /**
     * Finds all solutions to the exact cover problem.
     * @param matrix
     */
    public void search(QuadLinkedList matrix) {
        search(matrix, 0, new ArrayList<>());
    }

    /**
     * Finds all solutions to the exact cover problem.
     * @param matrix
     * @param k
     * @param solutions
     */
    public void search(QuadLinkedList matrix, int k, List<DataObject> solutions) {
        // If R[h] = h, print the current solution (see below) and return.
        if(matrix.isEmpty()) {
            this.handler.handle(solutions);
            return;
        }

        // Otherwise choose a column object c (see below).
        ColumnObject c = chooseC(matrix);

        // Cover column c (see below).
        coverColumn(c);

        // For each r ← D[c], D
        DataObject r = c;
        while((r = r.getD()) != c) {
            // set Ok ← r;
            solutions.add(k, r);

            // for each j ← R[r], R[R[r]]/, ... , while j =/= r,
            DataObject j = r;
            while((j = j.getR()) != r) {
                // cover column j (see below);
                coverColumn(j.getC());
            }

            // search(k + 1);
            search(matrix, k+1, solutions);

            // set r ← Ok and c ← C[r];
            r = solutions.remove(k);
            c = (ColumnObject) r.getC();

            // for each j ← L[r], L[L[r]], ... , while j =/= r
            j = r;
            while((j = j.getL()) != r) {
                // uncover column j (see below).
                uncoverColumn(j.getC());
            }
        }

        // Uncover column c (see below) and return.
        uncoverColumn(c);
    }

    /**
     * Determines the first column to cover. If minimizeBranchingFactor is set, then we choose the column with
     * the fewest ones.
     * @param matrix
     * @return the column to cover.
     */
    protected ColumnObject chooseC(QuadLinkedList matrix) {
        ColumnObject root = matrix.getRoot();
        ColumnObject c = (ColumnObject) root.getR();

        if(minimizeBranchingFactor) {
            int s = Integer.MAX_VALUE;
            ColumnObject j = root;
            while((j = (ColumnObject) j.getR()) != root) {
                if(j.getSize() < s) {
                    c = j;
                    s = j.getSize();
                }

            }
        }
        return c;
    }

    /**
     * Remove the Column from the header and header's rows from all other columns.
     * @param header
     */
    public void coverColumn(DataObject header) {
        header.getR().setL(header.getL());
        header.getL().setR(header.getR());

        DataObject obj = header;
        while((obj = obj.getD()) != header) {
            DataObject j = obj;
            while((j = j.getR()) != obj) {
                j.getD().setU(j.getU());
                j.getU().setD(j.getD());
                ((ColumnObject) j.getC()).decrementSize();
            }
        }
    }

    /**
     * The inverse operation of coverColumn.
     * @param header
     */
    public void uncoverColumn(DataObject header) {
        DataObject obj = header;
        while((obj = obj.getU()) != header) {
            DataObject j = obj;
            while((j = j.getL()) != obj) {
                ((ColumnObject) j.getC()).incrementSize();
                j.getD().setU(j);
                j.getU().setD(j);
            }
        }

        header.getR().setL(header);
        header.getL().setR(header);
    }

}
