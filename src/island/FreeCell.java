package island;

public class FreeCell {

    //координаты
    protected int x;
    protected int y;

    public FreeCell(int i, int j) {
        x = i;
        y = j;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void print() {
        System.out.print("   _   ");
    }

    public FreeCell whoWillBecome(Island is) {
        return this;
    }

    public boolean canBeGone() {
        return true;
    }
}
