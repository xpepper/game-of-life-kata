public class LiveCell extends Cell {

    @Override
    public Cell nextGeneration(Neighborhood neighborhood) {
        if (neighborhood.isStable()) {
            return Cell.live();
        } else {
            return Cell.dead();
        }
    }
}