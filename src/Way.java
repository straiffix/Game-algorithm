import java.util.ArrayList;
import java.util.List;

public class Way {

    List<List<PairOfPoints>> road;
    private double probability;
    List<List<PairOfPoints>> winningCombinations = new ArrayList<>();
    private PairOfPoints winningCombination;

    public Way( List<List<PairOfPoints>> road){
        this.road = new ArrayList<>(road);
        this.winningCombination = null;
        makeProbabilityOfWay();
    }

    public void makeProbabilityOfWay(){
        int win = 0;
        int lose = 0;
        for (List<PairOfPoints> list: road){

            if (list.size()==1){
                winningCombination = list.get(0);
            }
            if (list.size() % 2 == 0){
                lose++;
            }
            else {win ++;
            winningCombinations.add(list);}
        }
        if (win == 0){probability = 0;}
        else if (lose == 0) {probability = 1000;}
        else probability = win / lose;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public PairOfPoints getWinningCombination() {
        return winningCombination;
    }

    public void setWinningCombination(PairOfPoints winningCombination) {
        this.winningCombination = winningCombination;
    }
}
