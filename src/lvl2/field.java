package lvl2;

public class field {
    private int size = 15;
    private cell[][] ar;
    private cell[][] helpAr;

    public field() {
        ar = new cell[size + 2][size + 2];
        helpAr = new cell[size + 2][size + 2];

        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar.length; j++) {
                ar[i][j] = new cell(i, j);
                helpAr[i][j] = new cell(i,j);
            }
        }
    }

    public void printCells() {
        for (int i = 1; i < ar.length - 1; i++) {
            for (int j = 1; j < ar[0].length - 1; j++) {
                System.out.print(ar[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public cell[][] getCellArray() {
        return ar;
    }

    public void setCell(cell c) {
        ar[c.getYPos()][c.getXPos()] = c;
    }

    public void update() {
        for (int i = 1; i < ar.length - 1; i++) {
            for (int j = 1; j < ar[0].length - 1; j++) {
                helpAr[i][j] = ar[i][j].transform(this);
            }
        }

        for (int i = 1; i < ar.length - 1; i++) {
            for (int j = 1; j < ar[0].length - 1; j++) {
                ar[i][j] = helpAr[i][j];
            }
        }
    }
}
