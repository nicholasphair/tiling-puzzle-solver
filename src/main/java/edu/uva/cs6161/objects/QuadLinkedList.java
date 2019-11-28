package edu.uva.cs6161.objects;


import java.util.ArrayList;
import java.util.List;

public class QuadLinkedList {
    private ColumnObject root;
    private int[][] matrix;

    public QuadLinkedList(int[][] matrix) {
        if(!matrixIsValid(matrix)) {
            throw new IllegalArgumentException("matrix cannot be null or empty.");
        }

        this.matrix = matrix;
        initializeColumns();
        initializeRows();

    }

    private void initializeColumns() {
        root = new ColumnObject();
        for(int i = 0; i < numColumns(); i++) {
            ColumnObject header = new ColumnObject();
            header.setR(root);
            header.setL(root.getL());
            root.getL().setL(header);
            root.setL(header);
        }
    }

    private void initializeRows() {
        for(int r = 0; r < numRows(); r++) {
            DataObject columnHead = root.getR();
            List<DataObject> row = new ArrayList<>();
            for(int c = 0; c < numColumns(); c++) {
                if(matrix[r][c] == 0) {
                    columnHead = columnHead.getR();
                    continue;
                }
                row.add(appendDataObjectToColumn((ColumnObject) columnHead));
                columnHead = columnHead.getR();
            }

            if(row.isEmpty()) {
                continue;
            }

            DataObject rowHead = row.remove(0);
            row.forEach((x) -> {
                x.setL(rowHead.getL());
                x.setR(rowHead);
                rowHead.getL().setR(x);
                rowHead.setL(x);
            });
        }
    }

    private DataObject appendDataObjectToColumn(ColumnObject head) {
        DataObject dataObject = new DataObject();
        dataObject.setU(head.getU());
        dataObject.setD(head);
        dataObject.setC(head);

        head.getU().setD(dataObject);
        head.setU(dataObject);
        head.incrementSize();

        return dataObject;
    }



    private static boolean matrixIsValid(int[][] matrix) {
        return !(matrix == null || matrix.length == 0);
    }

    private int numRows() {
        return matrix.length;
    }

    private int numColumns() {
        return matrix[0].length;
    }
}
