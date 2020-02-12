import java.util.List;

public class DeadCell extends Cell {

    @Override
    protected boolean isAliveInTheNextGeneration(List<Cell> neighbors) {
        return isFertileNeighborhood(neighbors);
    }

    private boolean isFertileNeighborhood(List<Cell> neighbors) {
        return liveCellsIn(neighbors) == 3;
    }

}