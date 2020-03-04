import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class Location {
    public static final Location NORTH_EAST = new Location(-1, -1);
    public static final Location NORTH = new Location(-1, 0);
    public static final Location NORTH_WEST = new Location(-1, +1);
    public static final Location WEST = new Location(0, -1);
    public static final Location EAST = new Location(0, +1);
    public static final Location SOUTH_WEST = new Location(+1, -1);
    public static final Location SOUTH = new Location(+1, 0);
    public static final Location SOUTH_EAST = new Location(+1, +1);

    public final int x;
    public final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }

    Location at(Location otherLocation) {
        return new Location(x + otherLocation.x, y + otherLocation.y);
    }
}
