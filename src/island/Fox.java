package island;

public class Fox extends FreeCell {
    private int points = 5;

    public Fox(int X, int Y) {
        super(X,Y);
        super.i = X;
        super.j = Y;
    }

    public FreeCell reproduce(int i, int j) {
        int t = (int)Math.floor(Math.random() * 6);
        return t > 2 ? new Fox(i,j) : null;
    }

    public boolean canBeGone() {
        return true;
    }

    public void print() {
        String str = points < 10 ? "0" + points : String.valueOf(points);
        System.out.print("F (" + str + ") ");
    }

    public Coordinates move (FreeCell[][] land) {
        Coordinates t = findFreeFox(land);
        FreeCell target = land[i][j];
        if (target.canBeEaten()) {
            //System.out.println("EATEN");
            points += 3;
            return t;
        } else {
            points --;
            return points <= 0 ? new Coordinates(-1,-1) : t;
        }
    }

    //ищет клетку где либо сводно, либо есть кролик
    private Coordinates findFreeFox(FreeCell[][] land) {
        int count = 0;
        int a = i;
        int b = j;

        //обработка рамок
        int a1 = a == 0 ? 0 : a - 1;
        int a2 = a == land.length - 1 ? land.length - 1 : a + 2;
        int b1 = b == 0 ? 0 : b - 1;
        int b2 = b == land.length - 1 ? land.length - 1 : b + 2;

        //Есть ли вообще свободный
        for (int i = a1; i < a2; i++) {
            for (int j = b1; j < b2; j++) {
                if (i == a && j == b) continue;
                if (!land[i][j].canBeGone()) {
                    count += 1;
                }
            }
        }

        if (count <= 0) return new Coordinates(-1,-1);

        //поиск свободного
        while (true) {
            int r = (int)Math.floor(Math.random() * 9);

            int x,y;
            x = a;
            y = b;

            if (r == 0) {
                x -= 1;
                y -= 1;
            }
            if (r == 1) {
                y -= 1;
            }
            if (r == 2) {
                x += 1;
                y -= 1;
            }
            if (r == 3) {
                x -= 1;
            }
            if (r == 4) {
                continue;
            }
            if (r == 5) {
                x += 1;
            }
            if (r == 6) {
                x -= 1;
                y += 1;
            }
            if (r == 7) {
                y += 1;
            }
            if (r == 8) {
                x += 1;
                y += 1;
            }

            if (x < 0 || y < 0 || y >= land.length || x >= land.length) continue;
            //System.out.println(x + " " + y + " | old coords: " + a + " " + b + " | random: "  + r + " | count: " + count);
            return new Coordinates(x,y);
        }
    }
}
