import java.util.List;
import java.util.function.Predicate;

import static java.lang.Math.toIntExact;

abstract class Cell {

    public static Cell dead() {
        return new DeadCell();
    }

    public static Cell live() {
        return new LiveCell();
    }

    public Cell nextGeneration(List<Cell> neighbors) {
        if (isAliveInTheNextGeneration(neighbors)) {
            return Cell.live();
        } else {
            return Cell.dead();
        }
    }

    protected abstract boolean isAliveInTheNextGeneration(List<Cell> neighbors);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return getClass() == o.getClass();
    }

    protected Integer liveCellsIn(List<Cell> neighbors) {
        return toIntExact(neighbors.stream().filter(isAlive()).count());
    }

    private Predicate<Cell> isAlive() {
        return cell -> cell instanceof LiveCell;
    }
}
