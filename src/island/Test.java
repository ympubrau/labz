package island;

public class Test {
    public static void main(String[] args) {
        Island is = new Island(10);
        is.printIsland();
        initIsland(is);
        is.printIsland();

        for (int i = 0; i < 5; i++){
            is.life();
            is.printIsland();
        }
    }

    public static void initIsland(Island is) {
        /*is.setAnimal(new Rabbit( 2,2));
        is.setAnimal(new Rabbit(4,5));
        is.setAnimal(new Rabbit(7,2));
        is.setAnimal(new Rabbit(1,6));*/


        is.setAnimal(new Fox(2,8));
        is.setAnimal(new Fox(5,5));
    }
}
