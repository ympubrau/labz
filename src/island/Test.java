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
        FreeCell[][] mainLand = is.getLand();
        FreeCell[][] copyLand = is.getLandCopy();

        /*is.setAnimal(new Rabbit( 2,2));
        is.setAnimal(new Rabbit(4,5));
        is.setAnimal(new Rabbit(7,2));
        is.setAnimal(new Rabbit(1,6));*/


        mainLand[0][1] = new Fox(0,1);
        mainLand[7][7] = new Rabbit(7,7);
        //mainLand[1][0] = new Fox(1,0);

        copyLand[0][1] = mainLand[0][1];
        copyLand[7][7] = mainLand[7][7];
        //copyLand[1][0] = mainLand[1][0];
    }
}
