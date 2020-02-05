import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/*
    RULES:
    * Any live cell with fewer than two live neighbours dies, as if by underpopulation. [DONE]
    * Any live cell with two or three live neighbours lives on to the next generation. [DONE]
    * Any live cell with more than three live neighbours dies, as if by overpopulation. [DONE]
    * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction [DONE]

    TODO:

    * verificare come questo modello di cella si aggancia ad un mondo fatto di tante celle a griglia
    * potremmo togliere isAlive dentro il metodo liveCellsIn?
    * provare a spostare DeadCell e LiveCell in un package "interno" (rendendo non public le due classi)
        e verificare se tutto compila ancora
    * neighbors potrebbe diventare un oggetto?
        * `liveCellsIn()` potrebbe essere un suo metodo...
    * introdurre un builder per creare i neighbors in modo "comodo"
        * tipo `n.withAlive(3).andDead(5).build()`

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
    public void a_dead_cell_with_all_dead_neighbors_remains_dead() {
        List<Cell> neighbors = asList(
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.dead().nextGeneration(neighbors);

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
    public void a_dead_cell_with_two_live_neighbours_remains_dead() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
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
    public void a_live_cell_with_fewer_than_two_live_neighbors_dies() {
        List<Cell> neighbors = asList(
                Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(neighbors);

        assertEquals(Cell.dead(), nextGenerationCell);
    }

    @Test
    public void a_live_cell_with_more_than_three_live_neighbors_dies() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(), Cell.live(), Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(neighbors);

        assertEquals(Cell.dead(), nextGenerationCell);
    }

    @Test
    public void a_live_cell_with_two_live_neighbours_remains_alive() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(neighbors);

        assertEquals(Cell.live(), nextGenerationCell);
    }

    @Test
    public void a_live_cell_with_three_live_neighbours_remains_alive() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(), Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(neighbors);

        assertEquals(Cell.live(), nextGenerationCell);
    }
}
