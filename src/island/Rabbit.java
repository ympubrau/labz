package island;

public class Rabbit extends FreeCell {
    /*
        С вероятностью 1/2 данный метод возвращает нового кролика. В противном случае - возвращает нулл
     */

    public Rabbit(int X, int Y) {
        super(X, Y);
        super.i = X;
        super.j = Y;
    }

    public FreeCell reproduce(int i, int j) {
        int t = (int)Math.floor(Math.random() * 2);
        return t == 1 ? new Rabbit(i, j) : null;
    }

    public boolean canBeEaten() {
        return true;
    }

    public void print() {
        System.out.print("   R   ");
    }

    public Coordinates move(FreeCell[][] land) {
        return findFree(land);
    }
}
