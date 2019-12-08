package edu.uva.cs6161;

import java.awt.*;
import javax.swing.JPanel;
public class TilingPuzzleBoard extends JPanel {
    private int size;
    int color=0;
    char [][] board;

    public TilingPuzzleBoard(int size) {
        this.setPreferredSize(new Dimension(size, size));
        this.size = size;

    }

    public void setArray(int color, char [][] brd)
    {
        this.board=brd;
        this.color=color;
        System.out.println("setting board ");

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the board here.................
        int tileSize = size / 30;
        //Fill black.
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Color red tiles.
        if(color==9) g.setColor(Color.cyan);
        else g.setColor(Color.red);
        for (int i = 3; i < 20; i++) {
            for (int j = 3; j < 20; j++) {


                if(board[i-3][j-3]=='R')
                {

                    g.setColor(Color.RED);
                    g.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);

                }
                if(board[i-3][j-3]=='Y')
                {
                    g.setColor(Color.YELLOW);
                    g.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);

                }

                if(board[i-3][j-3]=='G')
                {

                    g.setColor(Color.GREEN);
                    g.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);

                }
                if(board[i-3][j-3]=='X')
                {


                }

            }
        }



    }
}