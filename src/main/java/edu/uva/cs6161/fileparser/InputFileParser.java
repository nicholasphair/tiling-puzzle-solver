package edu.uva.cs6161.fileparser;// added by soneya

import java.io.*;
import java.util.ArrayList;
public class InputFileParser {
    public static int array_length=0;
    public static int array_width=0;
    ArrayList<int [][] > tileList = new ArrayList<int [][] >();

    char [][] getInputTilesArray() throws IOException {

        String file_name="input1.txt";

        File file = new File(file_name);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String lineFromInputFile;


        while ((lineFromInputFile = br.readLine()) != null)
        {
           // System.out.println(lineFromInputFile);
            array_length++;
            if(array_width < lineFromInputFile.length())
            {
                array_width=lineFromInputFile.length();
            }
        }
        br.close();

       // System.out.println("hereee"+array_length);
       // System.out.println("hereee"+array_width);

//--------------------------------------------------------------------------------dimension read done-------------

        file = new File(file_name);
        br = new BufferedReader(new FileReader(file));

        char [][] entireInputArray= new char [array_length][array_width];  // array declaration

        for (int row = 0; row < array_length; row ++)   // array initialization
            for (int col = 0; col <array_width; col++)
                entireInputArray[row][col] = '_';
        //PrintInputArray(entireInputArray);
//-----------------------------------------------------------------------------------------------------------------------

        int count=0;

        while ((lineFromInputFile = br.readLine()) != null)                      // reading from input text file and populating char array
        {


            for ( int i=0; i < lineFromInputFile.length();i++)
            {
                if (lineFromInputFile.charAt(i) == ' ')
                    entireInputArray[count][i]='_';
                else  entireInputArray[count][i]=lineFromInputFile.charAt(i);
            }
            count++;
        }
        return entireInputArray;
    }




    public static void main(String args[]) throws IOException {


        InputFileParser newparser = new InputFileParser();
        ArrayList< char[][]>  allTiles = newparser.getInputTiles();

        for (int counter = 0; counter < allTiles.size(); counter++) {
            char[][] tile = allTiles.get(counter);


        }



        }

    ArrayList< char[][]>  getInputTiles() throws IOException {


        ArrayList< char[][]> all_tiles = new  ArrayList< char[][]>();

        InputFileParser new_parser = new InputFileParser();

        char[][] tilesArray = new_parser.getInputTilesArray();
        char[][] tilesArray_copy= new char [array_length][array_width];  // making a copy so that actual array remains unchanged

        for (int row = 0; row < array_length; row ++) {
            for (int col = 0; col < array_width; col++) {
                tilesArray_copy[row][col]= tilesArray[row][col];
            }
        }

        // new_parser.PrintInputArray(tilesArray_copy);
        //System.out.println("hereee"+array_length);
        //System.out.println("hereee"+array_widt

        int totalNoOfTiles=0;



        for (int row = 0; row < new_parser.array_length; row ++)
        {

            for (int col = 0; col < new_parser.array_width; col++) {
               if(tilesArray_copy[row][col] != '_' && tilesArray_copy[row][col-1] == '_')
               {

                   System.out.println(".............................................");

                   ArrayList<String> arrayIndices = new   ArrayList<String> ();
                   new_parser.depthFirstTraversal(tilesArray_copy,row, col, arrayIndices);

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


                   char [][] tile = new char [row_h-row_l+1][col_h-col_l+1];

                   //System.out.println( row_h-row_l+1);
                   //System.out.println(col_h-col_l+1);

                   for (int row1 = 0; row1 < (row_h-row_l+1); row1 ++) {  // array initialization
                       for (int col1 = 0; col1 < (col_h-col_l+1) ; col1 ++) {

                           //System.out.print(row1);
                           //System.out.print(col1);

                           //System.out.print(row1+row_l);
                          // System.out.print(col1+col_l);
                           //System.out.println(tilesArray[row1+row_l][col1+col_l]);

                           tile[row1][col1]=tilesArray[row1+row_l][col1+col_l];
                       }
                   }

                   all_tiles.add(tile);

                   new_parser.PrintInputArray(tile,row_h-row_l+1,col_h-col_l+1);
                   //System.out.println(".............................................");
                    totalNoOfTiles++;
               }
            }
            //new_parser.PrintInputArray(tilesArray_copy);
        }
        //new_parser.PrintInputArray(tilesArray);
        System.out.println("Total Tiles ===="+totalNoOfTiles);
        return all_tiles;

    }
    void PrintInputArray(char [][] entireInputArray , int row1, int col1)
    {
        for (int row = 0; row < row1; row ++) {  // array initialization
            for (int col = 0; col < col1; col++) {
                System.out.print(entireInputArray[row][col]);
            }
            System.out.println();
        }
    }


    void PrintInputArray(char [][] entireInputArray)
    {
        for (int row = 0; row < array_length; row ++) {  // array initialization
            for (int col = 0; col < array_width; col++) {
                System.out.print(entireInputArray[row][col]);
            }
            System.out.println();
        }
    }


    boolean depthFirstTraversal( char[][] tilesArray, int row, int column, ArrayList<String> arrayIndices)
    {

        if(row==array_length )
        {
            return false;
        }

        if(column==array_width )
        {
            return false;
        }

        //System.out.println(row +","+ column);
        arrayIndices.add(row +","+ column);
        tilesArray[row][column]='_';

        if( row-1 >= 0 && tilesArray[row-1][column]!='_')
        {
            depthFirstTraversal(tilesArray, row-1, column,arrayIndices);
        }

        if( column-1 >= 0 && tilesArray[row][column-1]!='_')
        {
            depthFirstTraversal(tilesArray, row, column-1,arrayIndices);
        }

        if( column+1 < array_width && tilesArray[row][column+1]!='_')
        {
            depthFirstTraversal(tilesArray, row, column+1,arrayIndices);
        }

        if(row+1 < array_length &&   tilesArray[row+1][column]!='_')
        {
            depthFirstTraversal(tilesArray, row+1, column,arrayIndices);
        }
        return true;
    }
}
