import java.util.List;

import static java.lang.Math.toIntExact;

public class LiveCell extends Cell {
    public LiveCell() {
        super(true);
    }

    public Cell nextGeneration(List<Cell> neighbors) {
        if (isStableNeighborhood(neighbors)) {
            return Cell.live();
        } else {
            return Cell.dead();
        }
    }

    private boolean isStableNeighborhood(List<Cell> neighbors) {
        return liveCellsIn(neighbors) == 2 || liveCellsIn(neighbors) == 3;
    }

    private Integer liveCellsIn(List<Cell> neighbors) {
        return toIntExact(neighbors.stream().filter(Cell::isAlive).count());
    }

}