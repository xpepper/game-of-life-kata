import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class World {
    private final Map<Location, Cell> cells = new HashMap<>();

    private final Integer rows;
    private final Integer columns;

    public World(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        initialize();
    }

    public void add(Cell cell, Location location) {
        cells.put(location, cell);
    }

    public World evolve() {
        World evolved = new World(rows, columns);

        for (Location location : locations()) {
            Cell nextCell = cellAt(location).nextGeneration(neighborhoodOf(location));
            evolved.add(nextCell, location);
        }
        return evolved;
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }

    private Cell cellAt(Location location) {
        return cells.getOrDefault(location, Cell.dead());
    }

    private Set<Location> locations() {
        return cells.keySet();
    }

    private void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                add(Cell.dead(), new Location(i, j));
            }
        }
    }

    private Neighborhood neighborhoodOf(Location location) {
        return new Neighborhood(
                asList(
                        cellAt(location.at(Location.NORTH_EAST)),
                        cellAt(location.at(Location.NORTH)),
                        cellAt(location.at(Location.NORTH_WEST)),
                        cellAt(location.at(Location.WEST)),
                        cellAt(location.at(Location.EAST)),
                        cellAt(location.at(Location.SOUTH_WEST)),
                        cellAt(location.at(Location.SOUTH)),
                        cellAt(location.at(Location.SOUTH_EAST))
                )
        );
    }
}
