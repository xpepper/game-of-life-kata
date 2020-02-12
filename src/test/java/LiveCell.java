public class LiveCell extends Cell {

    @Override
    protected boolean isAliveInTheNextGeneration(Neighborhood neighborhood) {
        return neighborhood.isStable();
    }

}