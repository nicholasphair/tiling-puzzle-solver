package edu.uva.cs6161;

import javax.swing.*;
import java.awt.*;
import java.util.List;

    public class TilingPuzzleSolver {


        private String fileName;
        private List<String[][]> outputList;
        private int counter = 0;
        private App app;
        private static ImageIcon SPINNER = new ImageIcon("src/main/resources/ajax-loader.gif");


        public TilingPuzzleSolver() {
            this.app = new App();

            JPanel headerPanel = new JPanel(new GridLayout(2, 1));
            JPanel btnPanel = new JPanel();
            final JFrame frame = new JFrame("Test");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
            frame.setTitle("Tiling Puzzle Solver");
            frame.setBackground(Color.gray);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



            final TilingPuzzleBoard tilingPuzzleBoard = new TilingPuzzleBoard(1000);
            frame.getContentPane().add(tilingPuzzleBoard, BorderLayout.CENTER);
            frame.getContentPane().add(headerPanel, BorderLayout.PAGE_START);

            final JButton openBtn = new JButton("Choose Input File");
            final JButton slnButton = new JButton("Get Solutions");
            final JButton nextSlnButton = new JButton("Display Next Solution");
            final JCheckBox uniqueSolutionsBox = new JCheckBox("Find Unique Solutions");
            final JLabel solutionCount = new JLabel("Displaying Solution 0 of 0");

            btnPanel.add(openBtn, BorderLayout.CENTER);
            btnPanel.add(slnButton ,BorderLayout.CENTER);
            btnPanel.add(nextSlnButton, BorderLayout.CENTER);

            JPanel solutionsPanel = new JPanel(new FlowLayout());
            solutionsPanel.add(uniqueSolutionsBox);
            solutionsPanel.add(solutionCount);

            headerPanel.add(btnPanel);
            headerPanel.add(solutionsPanel);


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
                solutionCount.setText("Displaying Solution 0 of 0");
                solutionCount.revalidate();
                solutionCount.repaint();
                tilingPuzzleBoard.setVisible(false);
            });

            slnButton.addActionListener(arg0 -> {
                if(fileName != null && fileName.trim().length() != 0) {
                    this.counter = 0;
                    SwingWorker worker = new SwingWorker<List<String[][]>, Void>() {
                        Component component;
                        @Override
                        public List<String[][]> doInBackground() {
                            tilingPuzzleBoard.invalidate();
                            tilingPuzzleBoard.removeAll();
                            JLabel label = new JLabel("finding solutions... ", SPINNER, JLabel.CENTER);
                            component = tilingPuzzleBoard.add(label);
                            tilingPuzzleBoard.repaint();
                            tilingPuzzleBoard.setVisible(true);
                            tilingPuzzleBoard.validate();

                            nextSlnButton.setEnabled(false);
                            openBtn.setEnabled(false);
                            uniqueSolutionsBox.setEnabled(false);
                            List<String[][]> sols = app.run(fileName, false);
                            if(uniqueSolutionsBox.isSelected()) {
                                return app.uniqueSolutions(sols);
                            } else {
                                return sols;
                            }
                        }

                        @Override
                        protected void done() {
                            nextSlnButton.setEnabled(true);
                            openBtn.setEnabled(true);
                            uniqueSolutionsBox.setEnabled(true);
                            tilingPuzzleBoard.invalidate();
                            tilingPuzzleBoard.removeAll();

                            try{
                                outputList = get();
                                solutionCount.setText("Displaying Solution 0 of " + outputList.size());
                            } catch(Exception e) {
                                outputList = null;
                                JLabel label = new JLabel("Something went wrong. Try a different file.");
                                solutionCount.setText("Displaying Solution 0 of 0");
                                component = tilingPuzzleBoard.add(label);
                            }
                            tilingPuzzleBoard.repaint();
                            tilingPuzzleBoard.setVisible(true);
                            tilingPuzzleBoard.validate();
                        }
                    };
                    worker.execute();
                }
            });

            nextSlnButton.addActionListener(arg0 -> {
                if(fileName == null || outputList == null) {
                    return;
                }

                if(!outputList.isEmpty()) {
                    tilingPuzzleBoard.paintBoard(outputList.get(counter));
                    solutionCount.setText("Displaying Solution " + (counter+1) + " of " + outputList.size());
                    counter = ++counter % outputList.size();
                } else {
                    tilingPuzzleBoard.removeAll();
                    tilingPuzzleBoard.add(new JLabel("no solutions found", JLabel.CENTER));
                }

                frame.revalidate();
                frame.repaint();
                //tilingPuzzleBoard.revalidate();
                //tilingPuzzleBoard.repaint();
            });
        }

        public static void main(final String[] args) {
            TilingPuzzleSolver tilingPuzzleSolver = new TilingPuzzleSolver();
        }
    }




