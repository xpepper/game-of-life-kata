import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class World {
    private final Map<Location, Cell> cells = new HashMap<>();

    private final Integer rows;
    private final Integer columns;

    public World() {
        this.rows = 3;
        this.columns = 3;
        initialize();
    }

    public void add(Cell cell, Location location) {
        cells.put(location, cell);
    }

    public World evolve() {
        World evolved = new World();

        for (Location location : cells.keySet()) {
            Cell nextCell = cells.getOrDefault(location, Cell.dead()).nextGeneration(neighborhoodOf(location));
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
                        cells.getOrDefault(location.at(Location.NORTH_EAST), Cell.dead()),
                        cells.getOrDefault(location.at(Location.NORTH), Cell.dead()),
                        cells.getOrDefault(location.at(Location.NORTH_WEST), Cell.dead()),
                        cells.getOrDefault(location.at(Location.WEST), Cell.dead()),
                        cells.getOrDefault(location.at(Location.EAST), Cell.dead()),
                        cells.getOrDefault(location.at(Location.SOUTH_WEST), Cell.dead()),
                        cells.getOrDefault(location.at(Location.SOUTH), Cell.dead()),
                        cells.getOrDefault(location.at(Location.SOUTH_EAST), Cell.dead())
                )
        );
    }
}
