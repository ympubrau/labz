package island;

public class Rabbit extends FreeCell {
    public Rabbit(int X, int Y) {
        super(X, Y);
    }

    public void print() {
        System.out.print("   R   ");
    }

    public boolean move(FreeCell[][] mainLand, FreeCell[][] copyLand) {
        for (int i = x - 1; i < x + 2; i++){
            for (int j = y - 1; j < y + 2; j++) {
                if (i >= 0 && i < copyLand.length && j >= 0 && j < copyLand[0].length) {
                    if (copyLand[i][j].canBeGone()) {
                        copyLand[x][y] = mainLand[i][j];
                        copyLand[i][j] = mainLand[x][y];
                        this.x = i;
                        this.y = j;
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public void reproduce(FreeCell[][] copyLand) {
        for (int i = x - 1; i < x + 1; i++){
            for (int j = y - 1; j < y + 1; j++) {
                if (i >= 0 && i < copyLand.length && j >= 0 && j < copyLand[0].length) {
                    if (copyLand[i][j].canBeGone()) {
                        copyLand[j][i] = new Rabbit(i,j);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public FreeCell whoWillBecome(Island is) {
        FreeCell[][] copyLand = is.getLandCopy();
        FreeCell[][] mainLand = is.getLand();

        //если передвижение вернуло ложь - вокруг нет пустых клеток, а значит нет смысла выполнять метод размножения
        boolean canThereBeFreeCells = true;
        if ((int)Math.floor(Math.random() * 9) == 1) canThereBeFreeCells = move(mainLand, copyLand);
        if ((int)Math.floor(Math.random() * 2) == 1 && canThereBeFreeCells) reproduce(copyLand);

        return this;
    }

    public boolean canBeEaten() {
        return true;
    }

    @Override
    public boolean canBeGone() {
        return false;
    }
}
