package edu.uva.cs6161;

import edu.uva.cs6161.handlers.SolutionsHandler;
import edu.uva.cs6161.handlers.StdoutSolutionsHandler;
import edu.uva.cs6161.structures.ColumnObject;
import edu.uva.cs6161.structures.DataObject;
import edu.uva.cs6161.structures.QuadLinkedList;

import java.util.ArrayList;
import java.util.List;

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

    public void search(int[][] matrix) {
        search(new QuadLinkedList(matrix), 0, new ArrayList<>());
    }

    public void search(QuadLinkedList matrix) {
        search(matrix, 0, new ArrayList<>());
    }

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

    protected ColumnObject chooseC(QuadLinkedList matrix) {
        ColumnObject root = matrix.getRoot();
        ColumnObject c = (ColumnObject) root.getR();

        if(minimizeBranchingFactor) {
            ColumnObject j = root;
            while((j = (ColumnObject) j.getR()) != root) {
                if(j.getSize() < Integer.MAX_VALUE) {
                    c = j;
                    c.setSize(j.getSize()); // NOTE (nphair): not necessary?
                }

            }
        }

        return c;

    }

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
