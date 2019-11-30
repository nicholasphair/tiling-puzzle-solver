package edu.uva.cs6161.fileparser;// added by soneya

import java.io.*;
import java.util.ArrayList;
public class InputFileParser {
    public static int array_length=0;
    public static int array_width=0;
    ArrayList<int [][] > tileList = new ArrayList<int [][] >();

    char [][] getInputTilesArray() throws IOException {

        String file_name="input.txt";


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
        newparser.getInputTiles();


    }

    void getInputTiles() throws IOException {

        InputFileParser new_parser = new InputFileParser();

        char[][] tilesArray = new_parser.getInputTilesArray();
        char[][] tilesArray_copy= new char [array_length][array_width];  // making a copy so that actual array remains unchanged

        for (int row = 0; row < array_length; row ++) {
            for (int col = 0; col < array_width; col++) {
                tilesArray_copy[row][col]= tilesArray[row][col];
            }
        }

        new_parser.PrintInputArray(tilesArray_copy);

        //System.out.println("hereee"+array_length);
        //System.out.println("hereee"+array_widt

        int totalNoOfTiles=0;
        for (int row = 0; row < new_parser.array_length; row ++)
        {

            for (int col = 0; col < new_parser.array_width; col++) {
               if(tilesArray_copy[row][col] != '_' && tilesArray_copy[row][col-1] == '_')
               {

                   System.out.println(".............................................");
                   new_parser.depthFirstTraversal(tilesArray_copy,row, col);
                   System.out.println(".............................................");
                   totalNoOfTiles++;
               }

            }
            //new_parser.PrintInputArray(tilesArray_copy);
        }
        new_parser.PrintInputArray(tilesArray);
        System.out.println("Total Tiles ===="+totalNoOfTiles);

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


    boolean depthFirstTraversal( char[][] tilesArray, int row, int column)
    {

        if(row==array_length )
        {
            return false;
        }

        if(column==array_width )
        {
            return false;
        }

        System.out.println(row +","+ column);
        tilesArray[row][column]='_';

        if( row-1 >= 0 && tilesArray[row-1][column]!='_')
        {
            depthFirstTraversal(tilesArray, row-1, column);
        }

        if( column-1 >= 0 && tilesArray[row][column-1]!='_')
        {
            depthFirstTraversal(tilesArray, row, column-1);
        }

        if( column+1 < array_width && tilesArray[row][column+1]!='_')
        {
            depthFirstTraversal(tilesArray, row, column+1);
        }

        if(row+1 < array_length &&   tilesArray[row+1][column]!='_')
        {
            depthFirstTraversal(tilesArray, row+1, column);
        }
        return true;
    }
}
