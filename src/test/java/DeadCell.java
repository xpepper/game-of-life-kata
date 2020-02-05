import java.util.List;

import static java.lang.Math.toIntExact;

public class DeadCell extends Cell {

    @Override
    protected boolean isAliveInTheNextGeneration(List<Cell> neighbors) {
        return isFertileNeighborhood(neighbors);
    }

    @Override
    protected boolean isAlive() {
        return false;
    }

    private boolean isFertileNeighborhood(List<Cell> neighbors) {
        return liveCellsIn(neighbors) == 3;
    }

    private Integer liveCellsIn(List<Cell> neighbors) {
        return toIntExact(neighbors.stream().filter(Cell::isAlive).count());
    }

}