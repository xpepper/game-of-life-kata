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
    }

    public void add(Cell cell, Location location) {
        cells.put(location, cell);
    }

    public World evolve() {
        World evolved = new World();

        Location firstLocation = new Location(0, 1);
        Cell nextCellAtFirstLocation = cells.getOrDefault(firstLocation, Cell.dead()).nextGeneration(neighborhoodOf(firstLocation));

        Location secondLocation = new Location(1, 1);
        Cell nextCellAtSecondLocation = cells.getOrDefault(secondLocation, Cell.dead()).nextGeneration(neighborhoodOf(secondLocation));

        evolved.add(nextCellAtFirstLocation, firstLocation);
        evolved.add(nextCellAtSecondLocation, secondLocation);
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
