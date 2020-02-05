import java.util.List;

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

    protected abstract boolean isAlive();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return getClass() == o.getClass();
    }
}
