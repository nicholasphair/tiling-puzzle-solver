package edu.uva.cs6161;

import edu.uva.cs6161.fileparser.InputFileParser;
import edu.uva.cs6161.handlers.CollectSolutionsHandler;
import edu.uva.cs6161.structures.Enclosure;
import edu.uva.cs6161.structures.QuadLinkedList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {

    public List<String> run(String filename, boolean optional) {
        InputFileParser inputFileParser = new InputFileParser(filename);
        List<char[][]> piecesAndBoard= inputFileParser.getInputPieces();
        int largestIndex = InputFileParser.identifyLargest(piecesAndBoard);

        Enclosure board = new Enclosure(piecesAndBoard.remove(largestIndex));
        List<Enclosure> pieces = piecesAndBoard.stream().map(Enclosure::new).collect(Collectors.toList());

        for(int i = 0; i < pieces.size(); i++) {
            Enclosure e = pieces.get(i);
            e.setName(Integer.toString(i));
        }

        ExactCoverGenerator exactCoverGenerator = new ExactCoverGenerator(pieces, board);

        exactCoverGenerator.generateMatrix();
        int[][] matrix = exactCoverGenerator.getMatrix();

        CollectSolutionsHandler collector = new CollectSolutionsHandler();
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix);
        if(optional) {
            quadLinkedList.setOptionalColumns(matrix[0].length - pieces.size(), pieces.size());
        }
        DLX dlx = new DLX(true, collector);
        dlx.search(quadLinkedList);
        return collector.collect();
    }
}
