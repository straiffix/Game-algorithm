import java.util.LinkedList;
import java.util.List;

public class Algorithm {

    public static List<PairOfPoints> makeAllPossiblePairs(Board board) {
        List<PairOfPoints> pairOfPoints = new LinkedList<>();
        for(Point point : board.listOfFreePoints){
            List<Point> list = board.checkClosest(point);
            for(Point anPoint: list){
                if (pairOfPoints.contains(new PairOfPoints(point, anPoint))!=true) {
                    pairOfPoints.add(new PairOfPoints(point, anPoint));
                }
            }
        }
        return pairOfPoints;

    }

    public static void makeDecisions(PairOfPoints pair, List<PairOfPoints> list, List<PairOfPoints> way, List<List<PairOfPoints>> road){
        List<PairOfPoints> freePoints = reduceElements(pair, list);
        if (freePoints.size()==0){
            List<PairOfPoints> recway = new LinkedList<>(way);
            road.add(recway);
            return;
        }
       for (int i=0; i<freePoints.size(); i++) {
            PairOfPoints pr = freePoints.get(i);
            way.add(pr);
            makeDecisions(pr, freePoints, way, road);
            way.remove(pr);
        }

    }


    public static List<PairOfPoints> reduceElements(PairOfPoints pair, List<PairOfPoints> list){
        List<PairOfPoints> freePoints = new LinkedList<>(list);
        freePoints.remove(pair);
        int i = 0;

        while(i<freePoints.size()){
            if (freePoints.get(i).contains(pair)){
                freePoints.remove(i);
            }
            i++;
        }
        return freePoints;
    }



}

