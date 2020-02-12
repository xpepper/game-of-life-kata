public class DeadCell extends Cell {

    @Override
    protected boolean isAliveInTheNextGeneration(Neighborhood neighborhood) {
        return neighborhood.isFertile();
    }

}