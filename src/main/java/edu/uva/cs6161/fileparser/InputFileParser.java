package edu.uva.cs6161.fileparser;// added by soneya

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputFileParser {
    private int array_length;
    private int array_width;
    private String filename;


    public InputFileParser(String filename) {
        this.filename = filename;
        initializeLengthAndWidth();
    }

    private void initializeLengthAndWidth() {
        this.array_length = 0;
        this.array_width = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.array_length++;
                this.array_width = Math.max(array_width, line.length());
            }
        } catch(FileNotFoundException e) {
            System.err.println("File not found.");
        } catch(IOException e) {
            System.err.println("Error while reading from file.");
        }
    }

    private char[][] getInputPiecesArray() {
        char[][] entireInputArray = new char[array_length][array_width];  // array declaration
        InputFileParser.fillArray(entireInputArray);

        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            int count = 0;
            String line;
            while((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        entireInputArray[count][i] = '_';
                    } else {
                        entireInputArray[count][i] = line.charAt(i);
                    }
                }
                count++;
            }
        } catch(FileNotFoundException e) {
            System.err.println("File not found.");
        } catch(IOException e) {
            System.err.println("Error while reading from file.");
        }
        return entireInputArray;
    }

    public ArrayList< char[][]> getInputPieces() {


        ArrayList< char[][]> all_pieces = new  ArrayList< char[][]>();

        InputFileParser new_parser = new InputFileParser(this.filename);

        char[][] piecesArray = new_parser.getInputPiecesArray();
        char[][] piecesArray_copy= new char [array_length][array_width];  // making a copy so that actual array remains unchanged

        for (int row = 0; row < array_length; row ++) {
            for (int col = 0; col < array_width; col++) {
                char piece = piecesArray[row][col];
                piecesArray_copy[row][col] = piece;
            }
        }

        //new_parser.PrintInputArray(piecesArray_copy);
        //System.out.println("hereee"+array_length);
        //System.out.println("hereee"+array_widt

        int totalNoOfPieces=0;

        for (int row = 0; row < new_parser.array_length; row ++)
        {

            for (int col = 0; col < new_parser.array_width; col++) {
                if(piecesArray_copy[row][col] != '_' && (col==0 || ( col > 0 && piecesArray_copy[row][col-1] == '_')))
                {               

                   //System.out.println(".............................................");

                   ArrayList<String> arrayIndices = new   ArrayList<String> ();
                   new_parser.depthFirstTraversal(piecesArray_copy,row, col, arrayIndices);

                   // now process the indices and add it into an array list of 2d array


                   // the the array dimension

                   int row_l=Integer.MAX_VALUE;
                   int row_h=Integer.MIN_VALUE;
                   int col_l=Integer.MAX_VALUE;
                   int col_h=Integer.MIN_VALUE;

                   //System.out.println("For Loop");
                   for (int counter = 0; counter < arrayIndices.size(); counter++) {


                       String[] index = arrayIndices.get(counter).split(",");
                       int row_ = Integer.parseInt(index[0]);
                       int col_ = Integer.parseInt(index[1]);
                       if(row_ < row_l ) row_l=row_;

                       if(row_h < row_ ) row_h=row_;

                       if(col_ < col_l ) col_l=col_;

                       if(col_h < col_ ) col_h=col_;
                   }

                 //  System.out.println( arrayIndices.toString());
                 //  System.out.println("For Loop: got low and high value :"+ row_l+","+ row_h+","+ col_l+","+col_h);


                   char [][] piece = new char [row_h-row_l+1][col_h-col_l+1];

                   //System.out.println( row_h-row_l+1);
                   //System.out.println(col_h-col_l+1);

                   for (int row1 = 0; row1 < (row_h-row_l+1); row1 ++) {  // array initialization
                       for (int col1 = 0; col1 < (col_h-col_l+1) ; col1 ++) {

                           //System.out.print(row1);
                           //System.out.print(col1);

                           //System.out.print(row1+row_l);
                           //System.out.print(col1+col_l);
                           //System.out.println(piecesArray[row1+row_l][col1+col_l]);

                           piece[row1][col1]=piecesArray[row1+row_l][col1+col_l];
                       }
                   }

                   all_pieces.add(piece);

                   //new_parser.PrintInputArray(piece,row_h-row_l+1,col_h-col_l+1);
                   //System.out.println(".............................................");
                   totalNoOfPieces++;
               }
            }
            //new_parser.PrintInputArray(piecesArray_copy);
        }
        //new_parser.PrintInputArray(piecesArray);
        //System.out.println("Total Pieces ==== " +totalNoOfPieces);
        return all_pieces;

    }

    private boolean depthFirstTraversal(char[][] piecesArray, int row, int column, ArrayList<String> arrayIndices)
    {

        if(row==array_length ) return false;


        if(column==array_width ) return false;

        //System.out.println(row +","+ column);
        arrayIndices.add(row +","+ column);
        piecesArray[row][column]='_';

        if( row-1 >= 0 && piecesArray[row-1][column]!='_')
        {
            depthFirstTraversal(piecesArray, row-1, column,arrayIndices);
        }

        if( column-1 >= 0 && piecesArray[row][column-1]!='_')
        {
            depthFirstTraversal(piecesArray, row, column-1,arrayIndices);
        }

        if( column+1 < array_width && piecesArray[row][column+1]!='_')
        {
            depthFirstTraversal(piecesArray, row, column+1,arrayIndices);
        }

        if(row+1 < array_length &&   piecesArray[row+1][column]!='_')
        {
            depthFirstTraversal(piecesArray, row+1, column,arrayIndices);
        }
        return true;
    }

    public static void printArray(char[][] array) {
        for(char[] r : array) {
            for(char c : r) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void fillArray(char[][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                array[i][j] = '_';
            }
        }
    }

    /**
     * The index of the largest piece where largest means having the most non underscore cells.
     * @param pieces
     * @return
     */
    public static int identifyLargest(List<char[][]> pieces) {
        Integer maxCount = Integer.MIN_VALUE;
        int maxIndex = 0;

        for(int i = 0; i < pieces.size(); i++) {
            int count = countValidCells(pieces.get(i));
            if(count > maxCount) {
                maxCount = count;
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private static int countValidCells(char[][] piece) {
        int total = 0;
        for(char[] r : piece) {
            for(char c : r) {
                total += c == '_' ? 0 : 1;
            }
        }
        return total;
    }
}
