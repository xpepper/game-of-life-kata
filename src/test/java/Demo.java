public class Demo {
    public static void main(String[] args) {
        World world = new World(25, 25);
        world.add(Cell.live(), new Location(0, 1));
        world.add(Cell.live(), new Location(1, 2));
        world.add(Cell.live(), new Location(2, 0));
        world.add(Cell.live(), new Location(2, 1));
        world.add(Cell.live(), new Location(2, 2));

        while (true) {
            System.out.println(world.toString());
            world = world.evolve();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
