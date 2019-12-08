package edu.uva.cs6161;

import edu.uva.cs6161.fileparser.InputFileParser;
import edu.uva.cs6161.handlers.CollectSolutionsHandler;
import edu.uva.cs6161.handlers.StdoutSolutionsHandler;
import edu.uva.cs6161.structures.Enclosure;
import edu.uva.cs6161.structures.EnclosureCell;
import edu.uva.cs6161.structures.Pair;
import edu.uva.cs6161.structures.QuadLinkedList;

import java.util.*;
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

        //StdoutSolutionsHandler collector = new StdoutSolutionsHandler();
        CollectSolutionsHandler collector = new CollectSolutionsHandler();
        QuadLinkedList quadLinkedList = new QuadLinkedList(matrix);
        if(optional) {
            quadLinkedList.setOptionalColumns(matrix[0].length - pieces.size(), pieces.size());
        }


        DLX dlx = new DLX(true, collector);
        dlx.search(quadLinkedList);
        List<String> solutions = collector.collect();

        for(String solution : solutions) {
            // Each element in the list as a line in the solution. The element is a map mapping the piece name to its index.
            List<Map<String, int[]>> s = quadLinkedList.solutionToIndices(solution);

            List<Map<String, List<Pair>>> solutionCoordinates = new ArrayList<>();
            for(Map<String, int[]> i : s) {
                for (Map.Entry<String, int[]> entry : i.entrySet()) {
                    Map<String, List<Pair>> pieceCoordMap = new HashMap<>();
                    List<Pair> coordinates = Arrays.stream(entry.getValue())
                            .mapToObj(x -> exactCoverGenerator.indexToCoordinate(x - pieces.size()))
                            //.mapToObj(exactCoverGenerator::indexToCoordinate)
                            .collect(Collectors.toList());
                    pieceCoordMap.put(entry.getKey(), coordinates);
                    solutionCoordinates.add(pieceCoordMap);
                }
            }



            EnclosureCell[][] ec = board.getTrucatedCells();
            Character[][] m = new Character[ec.length][ec[0].length];
            for(int r = 0; r < m.length; r++) {
                for(int c = 0; c < m[0].length; c++) {
                    m[r][c] = '_';
                }
            }

            char[] foo = {'a', 'b', 'c', 'd'};
            int x = 0;
            for(Map<String, List<Pair>> so : solutionCoordinates) {
                for (Map.Entry<String, List<Pair>> entry : so.entrySet()) {
                    for(Pair p : entry.getValue()) {
                        m[p.x][p.y] = entry.getKey().toCharArray()[0];
                    }
                }

            }

            //for(List<Pair> row : solutionCoordinates) {
            //    for(int i = 0; i < row.size(); i++) {
            //        Pair pair = row.get(i);
            //        try {
            //            m[pair.x][pair.y] = foo[x];
            //        } catch(NullPointerException e) {
            //            System.out.println("piece likely");
            //        }
            //    }
            //    x++;

            //}

            for(Character[] r : m) {
                for(Character c : r) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

            break;
        }

        return null;
    }
}
