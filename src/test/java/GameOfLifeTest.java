import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/*
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
    public void when_all_neighbors_are_dead_the_dead_cell_will_die() {
        List<Cell> neighbors = asList(
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.dead().nextGeneration(neighbors);

        assertEquals(Cell.dead(), nextGenerationCell);
    }

    @Test
    public void when_all_neighbors_are_dead_the_alive_cell_will_die() {
        List<Cell> neighbors = asList(
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.alive().nextGeneration(neighbors);

        assertEquals(Cell.dead(), nextGenerationCell);
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

    public static Cell alive() {
        return new Cell(true);
    }

    public Cell nextGeneration(List<Cell> neighbors) {
        return Cell.dead();
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
