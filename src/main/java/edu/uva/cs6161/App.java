package edu.uva.cs6161;

import edu.uva.cs6161.fileparser.InputFileParser;
import edu.uva.cs6161.handlers.CollectSolutionsHandler;
import edu.uva.cs6161.structures.Enclosure;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args ) {
        String filename = "/opt/devel/tiling-puzzle-solver/target/test-classes/checkerboard";

        InputFileParser inputFileParser = new InputFileParser(filename);


        List<char[][]> pieces = inputFileParser.getInputPieces();

        int largestIndex = InputFileParser.identifyLargest(pieces);
        Enclosure board = new Enclosure(pieces.remove(largestIndex));

        List<Enclosure> ps = pieces.stream().map(Enclosure::new).collect(Collectors.toList());
        //System.out.println(ps.stream().mapToInt(Enclosure::hashCode).distinct().count());
        //System.out.println(ps.stream().distinct().count());

        for(int i = 0; i < pieces.size(); i++) {
            ps.get(i).setName(Integer.toString(i));
            Enclosure e = ps.get(i);
            //System.out.println(e.hashCode());
            e.printCells();
            System.out.println();
        }

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(ps, board);

        exactCoverGenerator.generateMatrix();
        int[][] matrix = exactCoverGenerator.getMatrix();
        //kSystem.out.println(Arrays.stream(matrix).map(x -> x[0]).filter(x -> x == 1).count());

        CollectSolutionsHandler collector = new CollectSolutionsHandler();
        DLX dlx = new DLX(false, collector);
        dlx.search(matrix);
        System.out.println("Number of solutions to the pentomino problem: ");
        System.out.println(collector.collect().size());
    }
}
