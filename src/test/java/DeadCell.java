import java.util.List;

import static java.lang.Math.toIntExact;

public class DeadCell extends Cell {
    public DeadCell() {
        super(false);
    }

    public Cell nextGeneration(List<Cell> neighbors) {
        if (isFertileNeighborhood(neighbors)) {
            return Cell.live();
        } else {
            return Cell.dead();
        }
    }

    private boolean isFertileNeighborhood(List<Cell> neighbors) {
        return liveCellsIn(neighbors) == 3;
    }

    private Integer liveCellsIn(List<Cell> neighbors) {
        return toIntExact(neighbors.stream().filter(Cell::isAlive).count());
    }

}