import java.util.List;

import static java.lang.Math.toIntExact;

public class LiveCell extends Cell {

    @Override
    protected boolean isAliveInTheNextGeneration(List<Cell> neighbors) {
        return isStableNeighborhood(neighbors);
    }

    @Override
    protected boolean isAlive() {
        return true;
    }

    private boolean isStableNeighborhood(List<Cell> neighbors) {
        return liveCellsIn(neighbors) == 2 || liveCellsIn(neighbors) == 3;
    }

    private Integer liveCellsIn(List<Cell> neighbors) {
        return toIntExact(neighbors.stream().filter(Cell::isAlive).count());
    }

}