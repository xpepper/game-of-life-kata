import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class World {
    private Cell cell;

    public void add(Cell cell) {
        this.cell = cell;
    }

    public World evolve() {
        return new World();
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
