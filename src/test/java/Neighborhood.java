import java.util.List;

import static java.lang.Math.toIntExact;

public class Neighborhood {
    private List<Cell> neighbors;

    public Neighborhood(List<Cell> neighbors) {
        this.neighbors = neighbors;
    }

    public boolean isFertile() {
        return liveCells() == 3;
    }

    public boolean isStable() {
        return liveCells() == 2 || liveCells() == 3;
    }

    protected Integer liveCells() {
        return toIntExact(neighbors.stream().filter(Cell::isAlive).count());
    }
}
