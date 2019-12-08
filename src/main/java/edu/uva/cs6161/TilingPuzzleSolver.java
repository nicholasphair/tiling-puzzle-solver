package edu.uva.cs6161;

import javax.swing.*;
import java.awt.*;
import java.util.List;

    public class TilingPuzzleSolver {


        private String fileName;
        private List<String[][]> outputList;
        private int counter = 0;
        private App app;


        public TilingPuzzleSolver() {
            this.app = new App();

            JPanel btnPanel;
            final JFrame frame = new JFrame("Test");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
            frame.setTitle("Tiling Puzzle Solver");
            frame.setBackground(Color.gray);

            btnPanel = new JPanel();

            final TilingPuzzleBoard tilingPuzzleBoard = new TilingPuzzleBoard(1000);
            frame.getContentPane().add(tilingPuzzleBoard, BorderLayout.CENTER);
            frame.getContentPane().add(btnPanel, BorderLayout.PAGE_START);

            final JButton openBtn = new JButton("Choose Input File");
            final JButton slnButton = new JButton("Get Solutions");
            final JButton nextSlnButton = new JButton("Display Next Solution");

            btnPanel.add(openBtn, BorderLayout.WEST);
            btnPanel.add(slnButton, BorderLayout.WEST);
            btnPanel.add(nextSlnButton, BorderLayout.WEST);


            tilingPuzzleBoard.setVisible(false);
            frame.setVisible(true);

            openBtn.addActionListener(arg0 -> {
                JFileChooser openFile = new JFileChooser();
                openFile.showOpenDialog(null);
                try {
                    this.fileName = openFile.getSelectedFile().toString();
                }catch (Exception e) {

                }
                this.counter = 0;
                tilingPuzzleBoard.setVisible(false);
            });

            slnButton.addActionListener(arg0 -> {
                if(fileName.trim().length() != 0) {
                    outputList = app.run(fileName, false);
                }
                tilingPuzzleBoard.removeAll();
                tilingPuzzleBoard.revalidate();
                tilingPuzzleBoard.repaint();
                tilingPuzzleBoard.setVisible(true);
            });

            nextSlnButton.addActionListener(arg0 -> {
                tilingPuzzleBoard.paintBoard(outputList.get(counter));
                tilingPuzzleBoard.revalidate();
                tilingPuzzleBoard.repaint();
                counter = ++counter % outputList.size();
            });
        }

        public static void main(final String[] args) {
            TilingPuzzleSolver tilingPuzzleSolver = new TilingPuzzleSolver();
        }
    }




