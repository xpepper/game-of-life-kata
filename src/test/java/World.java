import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class World {
    private final Map<Location, Cell> cells = new HashMap<>();

    public void add(Cell cell, Location location) {
        cells.put(location, cell);
    }

    public World evolve() {
        World evolved = new World();
        Location location = new Location(0, 1);
        Cell nextCell = Cell.live().nextGeneration(new Neighborhood(
                asList(
                        cells.getOrDefault(new Location(location.x, location.y-1), Cell.dead()),
                        cells.getOrDefault(new Location(location.x, location.y+1), Cell.dead()),
                        cells.getOrDefault(new Location(location.x+1, location.y-1), Cell.dead()),
                        cells.getOrDefault(new Location(location.x+1, location.y), Cell.dead()),
                        cells.getOrDefault(new Location(location.x+1, location.y+1), Cell.dead())
                )
        ));

        evolved.add(nextCell, new Location(0, 1));
        evolved.add(Cell.live(), new Location(1, 1));
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
}
