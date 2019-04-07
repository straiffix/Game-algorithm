
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;


public class Player {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(System.in));

        int size = Integer.parseInt(bufferedReader.readLine());
        long initTime;
        ok();
        String field = bufferedReader.readLine();
        ok();
        List<Point> listOfPoints = Point.makeListOfOccupiedPoints(field);
        Board board = new Board(size, listOfPoints);
        board.makeListOfFreePoints();
        String info = bufferedReader.readLine();

        if (info.toLowerCase().compareTo("start") == 0) { //I am first
            board.move();
        } else {
            // I am second
            answer(board, info);
            board.move();

        }

            while (true) {

                info = bufferedReader.readLine();
                answer(board, info);

                 if (board.getFreePoints()<50){
                    List<PairOfPoints> listOfPossiblePairs = Algorithm.makeAllPossiblePairs(board);
                    //initTime = System.currentTimeMillis();
                    if (listOfPossiblePairs.size()<10){
                        List<Way> winning = new ArrayList<>();
                        for(PairOfPoints pair : listOfPossiblePairs) {
                            List<List<PairOfPoints>> road = new ArrayList<List<PairOfPoints>>();
                            List<PairOfPoints> way = new LinkedList<PairOfPoints>();
                            way.add(pair);
                           // if(System.currentTimeMillis() - initTime > 400L){
                             //   road.add(way);
                               // winning.add(new Way(road));
                                //break;
                        //    }
                            Algorithm.makeDecisions(pair, listOfPossiblePairs, way, road);
                            Way winningWay = new Way(road);
                            if (winningWay.getWinningCombination()!=null){
                                System.out.println(winningWay.getWinningCombination());
                                System.exit(0);
                            }
                            winning.add(winningWay);
                        }
                        PairOfPoints pair = findMax(winning);
                        board.moveFinal(pair);
                        continue;
                        }
               }
               board.move();
            }
        }


    private static void ok() {
        System.out.println("ok");
    }

    public static void print(int x1, int y1, int x2, int y2) {
        System.out.printf("{%d;%d},{%d;%d}\r\n", x1, y1, x2, y2);
    }


    public  static  void printList(List<Point> points){
        for(Point point: points){
            System.out.println(point);
            System.out.println(point.getCf());
        }
    }

    public static void answer(Board board, String info){
        List<Point> list = Point.makeListOfOccupiedPoints(info);
        for (Point point : list) {
            board.board[point.getX()][point.getY()] = false;
            board.listOfFreePoints.remove(point);
            board.setFreePoints(board.getFreePoints()-1);
        }
    }

    public static PairOfPoints findMax(List<Way> list){
        Way max = list.get(0);
        for (Way ls:list){
            if(ls.getProbability()>max.getProbability()) max = ls;
        }
        if (max.winningCombinations.size()!=0){
        return max.winningCombinations.get(0).get(0);}
        else return max.road.get(0).get(0);

    }

}