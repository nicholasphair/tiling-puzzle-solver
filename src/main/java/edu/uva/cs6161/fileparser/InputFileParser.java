package edu.uva.cs6161.fileparser;// added by soneya

import java.io.*;
import java.util.ArrayList;
public class InputFileParser {


    char [][] getInputTilesArray() throws IOException {
        int array_length=0;
        int array_width=0;

        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String lineFromInputFile;


        while ((lineFromInputFile = br.readLine()) != null)
        {
            if(lineFromInputFile.length()==0 ) continue;
            array_length++;

            if(array_width < lineFromInputFile.length())
            {
                array_width=lineFromInputFile.length();
            }
        }
        br.close();
//--------------------------------------------------------------------------------dimension read done-------------


        file = new File("input.txt");
        br = new BufferedReader(new FileReader(file));

        char [][] entireInputArray= new char [array_length][array_width];  // array declaration

        for (int row = 0; row < array_length; row ++)   // array initialization
            for (int col = 0; col <array_width; col++)
                entireInputArray[row][col] = '_';

//-----------------------------------------------------------------------------------------------------------------------

        int count=0;

        while ((lineFromInputFile = br.readLine()) != null)                      // reading from input text file and populating char array
        {

            if(lineFromInputFile.length()==0 ) continue;
            for ( int i=0; i < lineFromInputFile.length();i++)
            {
                if(lineFromInputFile.charAt(i) =='X' )
                    entireInputArray[count][i]='X';
                else if(lineFromInputFile.charAt(i)=='O' )
                    entireInputArray[count][i]='O';
                else    entireInputArray[count][i]='_';
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
        new_parser.PrintInputArray(tilesArray);


    }


    void PrintInputArray(char [][] entireInputArray)
    {
        for (char[] x : entireInputArray)
        {
            for (char y : x)
            {
                System.out.print(y);
            }
            System.out.println();
        }
    }

}
