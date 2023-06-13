package lvl2;

public class infectedCell extends cell{
    public infectedCell(int a, int b) {
        super(a, b);
    }

    public boolean infected() {
        return true;
    }

    public cell transform(field f) {
        int inf = countInfected(f);
        if (inf == 2 || inf == 3) {
            return this;
        }
        else {
            return new cell(xPos, yPos);
        }
    }

    @Override
    public String toString() {
        return ("X ");
    }
}
