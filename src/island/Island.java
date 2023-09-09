package island;

public class Island {

    private FreeCell[][] land;
    private FreeCell[][] landCopy;

    public Island(int a){
        land = new FreeCell[a][a];
        landCopy = new FreeCell[a][a];

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                land[i][j] = new FreeCell(i,j);
                landCopy[i][j] = new FreeCell(i,j);
            }
        }
    }

    public FreeCell[][] getLand() {
        return land;
    }

    public FreeCell[][] getLandCopy() {
        return landCopy;
    }

    public void printIsland() {
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                land[i][j].print();
            }
            System.out.println();
        }
        System.out.println();
    }

    public void life() {
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                land[i][j].whoWillBecome(this);
            }
        }

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                land[i][j] = landCopy[i][j];
            }
        }
    }

}
