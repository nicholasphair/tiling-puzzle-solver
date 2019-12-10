package edu.uva.cs6161.structures;


import java.util.*;
import java.util.stream.Collectors;

public class QuadLinkedList {
    private ColumnObject root;
    private int[][] matrix;
    private String[] columnNames;

    public QuadLinkedList(int[][] matrix) {
        this(matrix, null);
    }

    /**
     * Construct the data structure defined in the Dancing Links paper from a 2D int array.
     * @param matrix
     * @param columnNames
     */
    public QuadLinkedList(int[][] matrix, String[] columnNames) {
        if(!matrixIsValid(matrix)) {
            throw new IllegalArgumentException("matrix cannot be null or empty.");
        }
        this.matrix = matrix;

        int numCols = numColumns();
        if(columnNames == null) {
            columnNames = generateColumnNames(numCols);
        }

        if(!columnNamesAreValid(columnNames, numCols)) {
            throw new IllegalArgumentException(String.format(
                    "You supplied %d names for the %d columns of `matrix`.",
                    columnNames.length,
                    numCols
            ));
        }

        this.columnNames = columnNames;
        initializeColumns();
        initializeRows();
    }

    /**
     * If no names are provided, traverse the ascii table sequentially to generate names.
     * @param numNames
     * @return
     */
    private String[] generateColumnNames(int numNames) {
        columnNames = new String[numNames];
        int ascii = 'A';
        for(int i = 0; i < numNames; i++) {
            columnNames[i] = Character.toString((char) ascii++);
        }
        return columnNames;
    }

    /**
     * Walk the columns of the matrix and link ColumnObjects together for each column.
     */
    private void initializeColumns() {
        root = new ColumnObject();
        for(int i = 0; i < numColumns(); i++) {
            ColumnObject header = new ColumnObject();
            header.setName(columnNames[i]);
            header.setR(root);
            header.setL(root.getL());
            root.getL().setR(header);
            root.setL(header);
        }
    }

    /**
     * Walk the matrix creating DataObject for each 1 in the matrix. At the end, link the rows together.
     */
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

    public List<Map<String, int[]>> solutionToIndices(String solution) {
        return Arrays.stream(solution.split("\n"))
                .map(this::solutionRowToIndices)
                .collect(Collectors.toList());
    }

    public Map<String, int[]> solutionRowToIndices(String solution) {
        String[] names = solution.split(" ");
        //return Arrays.stream(names).mapToInt(this::nameToColumnIndex).toArray();

        int[] solutionRow = new int[matrix[0].length];
        Arrays.stream(names).mapToInt(this::nameToColumnIndex).forEach(x -> solutionRow[x] = 1);
        //return solutionRow;



        Map<String, int[]> foo = new HashMap<>();

        foo.put(getNameOfFirstSetColumn(solutionRow), Arrays.copyOfRange(solutionRow, 1, solution.length()-1));
        return foo;
    }

    public String getNameOfFirstSetColumn(int[] row) {
        int nameIndex = 0;
        while (row[nameIndex++] != 1) ;

        ColumnObject object = root;
        for (int i = 0; i < nameIndex; i++) {
            object = (ColumnObject) object.getR();
        }
        return object.getName();
    }

    public int nameToColumnIndex(String name) {
        ColumnObject current = root;
        int i = 0;
        while((current = (ColumnObject) current.getR()) != root) {
            if(current.getName().equals(name)) {
                return i;
            }
            i++;
        }
        throw new IllegalArgumentException("Name not in QuadLinkedList");
    }


    /**
     * Updates pointer to add a DataObject to the column defined by head.
     * @param head
     * @return the newly added DataObject.
     */
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

    private static boolean columnNamesAreValid(String[] columnNames, int numColumns) {
        return columnNames != null && columnNames.length == numColumns;
    }

    private int numRows() {
        return matrix.length;
    }

    private int numColumns() {
        return matrix[0].length;
    }

    public ColumnObject getRoot() {
        return root;
    }

    /**
     * An empty matrix has a root that points to itself.
     * @return
     */
    public boolean isEmpty() {
        return root.getR() == root;
    }


    public void makeFirstNColumnsSecondary(int n) {
        if (n < 1 || root == null) {
            return;
        }

        DataObject man = getColumnAtIndex(n+1);
        ColumnObject current = root;
        while((current = (ColumnObject) current.getR()) != root) {
            makeSecondary(current);
            if(--n == 0) break;
        }

        if(n != 0) {
            throw new IllegalArgumentException("Could not make the first n columns optional.");
        }

        man.setL(root);
        root.setR(man);
    }


    private DataObject getColumnAtIndex(int i) {
        ColumnObject current = root;
        while((current = (ColumnObject) current.getR()) != root) {
            if(--i == 0) return current;
        }

        throw new IllegalArgumentException("There is no nth column to get.");
    }

    private static void makeSecondary(DataObject object) {
        object.setL(object);
        object.setR(object);
    }
}
