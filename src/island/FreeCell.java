package island;

public class FreeCell extends Cell {

    //координаты
    protected int i;
    protected int j;

    public FreeCell(int X, int Y) {
        super(X, Y);
    }

    public Coordinates getCords() {
        return new Coordinates(i,j);
    }

    public void setCords(Coordinates c) {
        i = c.x;
        j = c.y;
    }

    public void print() {
        System.out.print("   _   ");
    }


    public boolean canBeGone() {
        return false;
    }

    public boolean canBeEaten() {
        return false;
    }


    public FreeCell reproduce(int i, int j) {
        return null;
    }

    public Coordinates move(FreeCell[][] land) { return new Coordinates(i,j); }

    public Coordinates findFree(FreeCell[][] land) {
        int a = i;
        int b = j;
        int count = 0;
        //System.out.println("FIND FREE:" + a + " " + b);
        //обработка рамок
        int a1 = a == 0 ? 0 : a - 1;
        int a2 = a == land.length - 1 ? land.length - 1 : a + 2;
        int b1 = b == 0 ? 0 : b - 1;
        int b2 = b == land.length - 1 ? land.length - 1 : b + 2;

        //Есть ли вообще свободный
        for (int i = a1; i < a2; i++) {
            for (int j = b1; j < b2; j++) {
                if (i == a && j == b) continue;
                if (!land[i][j].canBeGone() && !land[i][j].canBeEaten()) {
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
            //System.out.println("FIND FREE:" + x + " " + y);

            return new Coordinates(x,y);
        }
    }
}
