public class DeadCell extends Cell {

    @Override
    public Cell nextGeneration(Neighborhood neighborhood) {
        if (neighborhood.isFertile()) {
            return Cell.live();
        } else {
            return Cell.dead();
        }
    }
}