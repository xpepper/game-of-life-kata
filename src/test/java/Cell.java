import java.util.List;
import java.util.Objects;

abstract class Cell {
    private boolean state;

    public Cell(boolean state) {
        this.state = state;
    }

    public static Cell dead() {
        return new DeadCell();
    }

    public static Cell live() {
        return new LiveCell();
    }

    public abstract Cell nextGeneration(List<Cell> neighbors);

    public boolean isAlive() {
        return state;
    }

    @Override
    public String toString() {
        return "Cell{state=" + state + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return state == cell.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
