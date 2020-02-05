import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static java.lang.Math.toIntExact;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/*
    RULES:
    * Any live cell with two or three neighbors survives.
    * Any dead cell with three live neighbors becomes a live cell.
    * All other live cells die in the next generation. Similarly, all other dead cells stay dead.

    - griglia infinita
    - evoluzione nello stesso momento
    - qual'è il primo test?
    - in che modo una cella "conosce" le altre sue vicine?

    griglia 3x3
    vuota => vuota

    griglia 3x3
    sola cella viva al centro => vuota
 */

public class GameOfLifeTest {

    @Test
    public void a_dead_cell_with_all_dead_neighbors_stays_dead() {
        List<Cell> neighbors = asList(
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.dead().nextGeneration(neighbors);

        assertEquals(Cell.dead(), nextGenerationCell);
    }

    @Test
    public void a_live_cell_with_all_dead_neighbors_dies() {
        List<Cell> neighbors = asList(
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(neighbors);

        assertEquals(Cell.dead(), nextGenerationCell);
    }

    @Test
    public void a_dead_cell_with_three_live_neighbours_becomes_a_live_cell() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(), Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.dead().nextGeneration(neighbors);

        assertEquals(Cell.live(), nextGenerationCell);
    }

    @Test
    public void a_live_cell_with_two_live_neighbours_remains_alive() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(), Cell.dead(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(neighbors);

        assertEquals(Cell.live(), nextGenerationCell);
    }
}

class Cell {
    private boolean state;

    private Cell(boolean state) {
        this.state = state;
    }

    public static Cell dead() {
        return new Cell(false);
    }

    public static Cell live() {
        return new Cell(true);
    }

    public Cell nextGeneration(List<Cell> neighbors) {
        if (liveCellsIn(neighbors) == 2) {
            return Cell.live();
        }
        if (liveCellsIn(neighbors) == 3) {
            return Cell.live();
        }

        return Cell.dead();
    }

    @Override
    public String toString() {
        return "Cell{state=" + state + '}';
    }

    private Integer liveCellsIn(List<Cell> neighbors) {
        return toIntExact(neighbors.stream().filter(Cell::isAlive).count());
    }

    private boolean isAlive() {
        return state;
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
