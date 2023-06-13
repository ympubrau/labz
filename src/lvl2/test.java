package lvl2;

public class test {
    public static void main(String[] args) {
        field f = new field();
        init(f);
        f.printCells();

        for (int i = 0; i < 2; i++) {
            f.update();
            f.printCells();
        }
    }

    public static void init(field f) {
        f.setCell(new infectedCell(3,8));
        f.setCell(new infectedCell(4,7));
        f.setCell(new infectedCell(5,7));
        f.setCell(new infectedCell(5,8));
        f.setCell(new infectedCell(5,9));
        f.setCell(new infectedCell(10,7));
        f.setCell(new infectedCell(10,8));
        f.setCell(new infectedCell(10,9));
        f.setCell(new infectedCell(11,7));
        f.setCell(new infectedCell(12,8));
        f.setCell(new infectedCell(3,2));
        f.setCell(new infectedCell(4,3));
        f.setCell(new infectedCell(5,1));
        f.setCell(new infectedCell(5,2));
        f.setCell(new infectedCell(5,3));
        f.setCell(new infectedCell(9,1));
        f.setCell(new infectedCell(9,2));
        f.setCell(new infectedCell(9,3));
        f.setCell(new infectedCell(10,3));
        f.setCell(new infectedCell(11,2));
    }
}
