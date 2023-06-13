package lvl2;

public class cell {
    protected int xPos;
    protected int yPos;

    public cell (int a, int b) {
        xPos = a;
        yPos = b;
    }

    public boolean infected() {
        return false;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public cell transform(field f) {
        int inf = countInfected(f);

        if (inf == 3) {
            return new infectedCell(xPos, yPos);
        }
        else {
            return this;
        }

    }

    public int countInfected(field f) {
        cell[][] ar = f.getCellArray();
        int counter = 0;

        for (int i = xPos - 1; i < xPos + 2; i++) {
            for (int j = yPos - 1; j < yPos + 2; j++) {
                if (i == xPos && j == yPos) continue;
                if (ar[i][j].infected()) {
                    counter ++;
                }
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        return ("- ");
    }
}
