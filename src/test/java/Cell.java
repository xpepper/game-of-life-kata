import java.util.List;
import java.util.Objects;

abstract class Cell {

    public static Cell dead() {
        return new DeadCell();
    }
    public static Cell live() {
        return new LiveCell();
    }

    public abstract Cell nextGeneration(List<Cell> neighbors);

    protected abstract boolean isAlive();

    @Override
    public String toString() {
        return "Cell{state=" + isAlive() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return isAlive() == cell.isAlive();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAlive());
    }
}
