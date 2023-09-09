package island;

public class Fox extends Rabbit {
    private int points = 5;

    public Fox(int X, int Y) {
        super(X,Y);
    }


    public boolean canBeGone() {
        return false;
    }
    public boolean canBeEaten() {return false;}

    public void reproduce(FreeCell[][] copyLand) {
        for (int i = x - 1; i < x + 2; i++){
            for (int j = y - 1; j < y + 2; j++) {
                if (i >= 0 && i < copyLand.length && j >= 0 && j < copyLand[0].length) {
                    if (copyLand[i][j].canBeGone()) {
                        copyLand[i][j] = new Fox(i,j);
                        return;
                    }
                }
            }
        }
    }

    public boolean move(FreeCell[][] mainLand, FreeCell[][] copyLand) {
        for (int i = x - 1; i < x + 2; i++){
            for (int j = y - 1; j < y + 2; j++) {
                if (i >= 0 && j >= 0 && i < copyLand.length &&  j < copyLand[0].length) {
                    if (copyLand[i][j].canBeGone()) {
                        copyLand[x][y] = mainLand[i][j];
                        copyLand[i][j] = mainLand[x][y];
                        this.x = j;
                        this.y = i;
                        return false;
                    } else if (((Rabbit) copyLand[i][j]).canBeEaten()) {
                        copyLand[i][j] = mainLand[x][y];
                        copyLand[x][y] = new FreeCell(i,j);
                        this.x = i;
                        this.y = j;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public FreeCell whoWillBecome(Island is) {
        FreeCell[][] copyLand = is.getLandCopy();
        FreeCell[][] mainLand = is.getLand();

        boolean haveMoved = false;
        if ((int)Math.floor(Math.random() * 9) > -1) haveMoved = this.move(mainLand, copyLand);

        points += haveMoved ? 5 : -1;

        if (points < 1) {
            copyLand[x][y] = new FreeCell(x,y);
            mainLand[x][y] = copyLand[x][y];
            return this;
        }

        if ((int)Math.floor(Math.random() * 6) == 3) reproduce(copyLand);

        return this;
    }

    public void print() {
        String str = points < 10 ? "0" + points : String.valueOf(points);
        System.out.print("F (" + str + ") ");
    }

}
