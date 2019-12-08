import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

    public class TilingPuzzleSolver {


        public static String fileName = "";
        public static ArrayList<char [][]> outputList = new ArrayList<char [][]> ();
        public static int counter=0;



        public static void main(final String[] args) {

            JPanel btnPanel;
            JPanel topPanel;
            final JFrame frame = new JFrame("Test");
            frame.setTitle("Tiling Puzzle Solver");
            frame.setSize(800, 800);
            frame.setBackground(Color.gray);

            btnPanel = new JPanel();

            final TilingPuzzleBoard tilingPuzzleBoard = new TilingPuzzleBoard(1000);
            frame.getContentPane().add(tilingPuzzleBoard, BorderLayout.CENTER);
            frame.getContentPane().add(btnPanel, BorderLayout.PAGE_START);

            final JButton openBtn = new JButton("Choose Input File");
            final JButton slnButton = new JButton("Get Solution");
            final JButton nextSlnButton = new JButton("Get Next Solution");

            btnPanel.add(openBtn, BorderLayout.WEST);
            btnPanel.add(slnButton, BorderLayout.WEST);
            btnPanel.add(nextSlnButton, BorderLayout.WEST);


            tilingPuzzleBoard.setVisible(false);
            frame.setVisible(true);

            openBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JFileChooser openFile = new JFileChooser();
                    openFile.showOpenDialog(null);

                    try {
                        fileName = openFile.getSelectedFile().toString();
                    }catch (Exception e)
                    {
                    }
                    tilingPuzzleBoard.setVisible(false);


                    if(fileName.trim().length()!=0)
                    {

                        outputList =  getOutputList(fileName);

                        System.out.println("size=========="+outputList.size());


                        // get the final array list
                        // set the arrayList of this class

                    }


                }
            });

            slnButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {



                    tilingPuzzleBoard.setArray(0,outputList.get(0));

                    System.out.println(fileName);

                    tilingPuzzleBoard.setVisible(true);
                    tilingPuzzleBoard.repaint();
                }
            });

            nextSlnButton.addActionListener(new ActionListener() {





                @Override
                public void actionPerformed(ActionEvent arg0) {

                    System.out.println("I am here......"+counter);



                    tilingPuzzleBoard.setArray(0,outputList.get(counter));

                    System.out.println(fileName);

                     // tilingPuzzleBoard.setVisible(true);
                    tilingPuzzleBoard.repaint();
                    counter++;

                    if(counter==outputList.size()) counter=0;


                    System.out.println("I am here......"+counter);

                }
            });
        }





        public static ArrayList<char [][] > getOutputList(String file){

            ArrayList<char [][]> outputList_ = new ArrayList<char [][]> ();

            char [][] board0 ={

                    {'R','R','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','R','R','R','Y','Y','Y','Y'}
            };

            outputList_.add(board0);


            char [][] board1 ={

                    {'R','R','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'}
            };
            outputList_.add(board1);


            char [][] board2 ={

                    {'R','R','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','G','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','G','G','G','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','X'},
                    {'R','R','Y','Y','Y','Y','Y','Y','Y','Y','R','R','R','Y','Y','Y','X'}
            };

            outputList_.add(board2);

            return outputList_;


        }



    }




