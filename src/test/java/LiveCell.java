import java.util.List;

public class LiveCell extends Cell {

    @Override
    protected boolean isAliveInTheNextGeneration(List<Cell> neighbors) {
        return isStableNeighborhood(neighbors);
    }

    private boolean isStableNeighborhood(List<Cell> neighbors) {
        return liveCellsIn(neighbors) == 2 || liveCellsIn(neighbors) == 3;
    }

}