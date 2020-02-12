abstract class Cell {

    public static Cell dead() {
        return new DeadCell();
    }

    public static Cell live() {
        return new LiveCell();
    }

    public abstract Cell nextGeneration(Neighborhood neighborhood);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return getClass() == o.getClass();
    }

    public boolean isAlive() {
        return this instanceof LiveCell;
    }
}
