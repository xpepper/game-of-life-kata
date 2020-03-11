import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/*
    RULES:
    * Any live cell with fewer than two live neighbours dies, as if by underpopulation. [DONE]
    * Any live cell with two or three live neighbours lives on to the next generation. [DONE]
    * Any live cell with more than three live neighbours dies, as if by overpopulation. [DONE]
    * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction [DONE]

    TODO:

    * estrarre factory per creare mondo
    * neighborhoodOf va bene su World o è da spostare altrove?
    * valutare se il metodo add debba sempre aggiungere una cella viva, e quindi non servirebbe passarla come primo parametro
    * verificare come questo modello di cella si aggancia ad un mondo fatto di tante celle a griglia
        * proviamo a testare la transizione di stato per effetto collaterale su un collaboratore
    * provare a spostare DeadCell e LiveCell in un package "interno" (rendendo non public le due classi)
        e verificare se tutto compila ancora
    * introdurre un builder per creare i neighbors in modo "comodo"
        * tipo `n.withAlive(3).andDead(5).build()`
        * rafforzare la regola di dominio che ogni cella ha esattamente 8 celle vicine.
    * come testiamo l'evoluzione del mondo per diversi seed iniziali?
    * come disaccoppiamo il world dalla sua rappresentazione grafica?
    * cosa cambierebbe se volessimo avere un mondo toroidale infinito oltre a quello con i confini?

 */

public class GameOfLifeTest {

    @Test
    public void two_worlds_with_a_cell_in_a_different_location_are_not_equals() {
        World world = aWorld();
        world.add(Cell.live(), new Location(1, 1));

        World anotherWorld = aWorld();
        anotherWorld.add(Cell.live(), new Location(2, 2));

        assertNotEquals(anotherWorld, world);
    }

    @Test
    public void two_worlds_with_a_cell_in_the_same_location_are_equals() {
        World world = aWorld();
        world.add(Cell.live(), new Location(1, 1));

        World anotherWorld = aWorld();
        anotherWorld.add(Cell.live(), new Location(1, 1));

        assertEquals(anotherWorld, world);
    }

    @Test
    public void simple_evolution() {
        World world = aWorld();
        world.add(Cell.live(), new Location(0, 0));
        world.add(Cell.live(), new Location(0, 1));
        world.add(Cell.live(), new Location(0, 2));

        World newWorld = world.evolve();

        World evolved = aWorld();
        evolved.add(Cell.live(), new Location(0, 1));
        evolved.add(Cell.live(), new Location(1, 1));

        assertEquals(evolved, newWorld);
    }

    @Test
    public void a_dead_cell_with_all_dead_neighbors_remains_dead() {
        List<Cell> neighbors = asList(
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.dead().nextGeneration(new Neighborhood(neighbors));

        assertEquals(Cell.dead(), nextGenerationCell);
    }

    @Test
    public void a_dead_cell_with_three_live_neighbours_becomes_a_live_cell() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(), Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.dead().nextGeneration(new Neighborhood(neighbors));

        assertEquals(Cell.live(), nextGenerationCell);
    }

    @Test
    public void a_dead_cell_with_two_live_neighbours_remains_dead() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.dead().nextGeneration(new Neighborhood(neighbors));

        assertEquals(Cell.dead(), nextGenerationCell);
    }

    @Test
    public void a_live_cell_with_all_dead_neighbors_dies() {
        List<Cell> neighbors = asList(
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(new Neighborhood(neighbors));

        assertEquals(Cell.dead(), nextGenerationCell);
    }

    @Test
    public void a_live_cell_with_fewer_than_two_live_neighbors_dies() {
        List<Cell> neighbors = asList(
                Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(new Neighborhood(neighbors));

        assertEquals(Cell.dead(), nextGenerationCell);
    }

    @Test
    public void a_live_cell_with_more_than_three_live_neighbors_dies() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(), Cell.live(), Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(new Neighborhood(neighbors));

        assertEquals(Cell.dead(), nextGenerationCell);
    }

    @Test
    public void a_live_cell_with_two_live_neighbours_remains_alive() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(new Neighborhood(neighbors));

        assertEquals(Cell.live(), nextGenerationCell);
    }

    @Test
    public void a_live_cell_with_three_live_neighbours_remains_alive() {
        List<Cell> neighbors = asList(
                Cell.live(), Cell.live(), Cell.live(),
                Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead(), Cell.dead()
        );
        Cell nextGenerationCell = Cell.live().nextGeneration(new Neighborhood(neighbors));

        assertEquals(Cell.live(), nextGenerationCell);
    }

    private static World aWorld() {
        return new World(3, 3);
    }
}
