package edu.uva.cs6161;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.*;

public class TilingPuzzleBoard extends JPanel {
    private Random rand;
    private Map<String, Color> colorMap;
    String[][] board;

    public TilingPuzzleBoard(int size) {
        this.setPreferredSize(new Dimension(size, size));
        this.rand = new Random();
        this.colorMap = new HashMap<>();
        colorMap.put("_", Color.BLACK);
        this.board = null;
    }

    public void paintBoard(String[][] board) {
        this.removeAll();
        this.setLayout(new GridLayout(board.length, board[0].length));
        for(String[] row: board) {
            for(String column: row) {
                addColoredPanel(column);
            }
        }
    }

    private void addColoredPanel(String column) {
        Color color = colorMap.computeIfAbsent(column, __ -> randomColor());
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(panel);
    }

    private Color randomColor() {
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return new Color(r, g, b);
    }
}