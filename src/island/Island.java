package island;

public class Island {
    public class Cords {
        int x;
        int y;
        public Cords(int a, int b){
            x = a;
            y = b;
        }
    }
    private Animal[][] land;
    private Animal[][] landCopy;

    /*
        Создание и инициализация массива
    */
    public Island(int a){
        land = new Animal[a][a];
        landCopy = new Animal[a][a];

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                land[i][j] = new Animal();
                landCopy[i][j] = new Animal();
            }
        }
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

    public void setAnimal(Animal a, int x, int y) {
        land[x][y] = a;
        landCopy[x][y] = a;
    }

    /*
        Проход по острову.
        Нужно создать копию текущего состояния острова, а дальше работать с такой копией. То есть, все изменения вносить
        в копию острова, а исходные данные о положении лис и кроликов брать с оригинада, в который не вносятся изменения.
        Во время прохода, будет осуществляться движение животного, а также процесс размножения.
        (с движением пока много вопросов)
        Разможнение будет происходить вызовом метода reproduce. Если этот метод вернет не null, то размножение прошло
        удачно, поэтому нужно выбрать свободную клетку рядом (если она есть) и поместить туда новое животное.
     */
    public void life() {
        //первый проход, в котором изменяется только копия
        for (int i = 0; i < land.length; i++){
            for (int j = 0; j < land[i].length; j++) {
                //если мы в обычный клетке, а не на клетке кролика или лисы
                if (!land[i][j].canBeGone() && !land[i][j].canBeEaten()) continue;

                int x, y;
                Animal temp;

                //размножение
                temp = land[i][j].reproduce();
                //если не сработала вероятность
                if (temp != null){
                    //если не null, то ищем свободное место и записываем туда новое животное
                    Cords t = findFree(i,j, true);
                    x = t.x;
                    y = t.y;
                    if (x != -1) {
                        landCopy[x][y] = temp;
                    }
                }


                //считаем вероятность
                boolean shouldMove = Animal.isMoving();

                //ищем свободное место
                Cords t;
                if (land[i][j].canBeEaten()) {
                    t = findFree(i,j, true);
                } else {
                    t = findFree(i,j, false);
                }

                x = t.x;
                y = t.y;
                //if (x != -1) System.out.println(x + " " + y +  " " + land[x][y].isRabbit() + " | " + i + " " + j +  " " + land[i][j].isFox());

                //либо мы ходим туда, куда можно пойти
                if (shouldMove && x!= -1 ) {
                    temp = land[i][j].move(land[x][y]);
                    landCopy[i][j] = new Animal();
                    landCopy[x][y] = temp;
                }
                //либо остаемся на месте, но у лисы отнимутся очки
                else {
                    landCopy[i][j] = land[i][j].move(land[i][j]);
                }
            }
        }

        //перенос значений с копии в оригинал
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                land[i][j] = landCopy[i][j];
            }
        }
    }

    public Cords findFree(int a, int b, boolean forRabbit) {
        int count = 0;
        //обработка рамок
        int a1 = a == 0 ? 0 : a - 1;
        int a2 = a == land.length - 1 ? land.length - 1 : a + 2;
        int b1 = b == 0 ? 0 : b - 1;
        int b2 = b == land.length - 1 ? land.length - 1 : b + 2;

        //Есть ли вообще свободный
        for (int i = a1; i < a2; i++) {
            for (int j = b1; j < b2; j++) {
                if (i == a && j == b) continue;
                boolean t = true;
                if (forRabbit) {
                    t = !land[i][j].canBeEaten();
                }
                if (!land[i][j].canBeGone() && t) {
                    count += 1;
                }
            }
        }

        if (count <= 0) return new Cords(-1,-1);

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

            if (!forRabbit) {
                if (!land[x][y].canBeGone()) {
                    return new Cords(x,y);
                }
            } else {
                if (!land[x][y].canBeGone() && !land[x][y].canBeEaten()) {
                    return new Cords(x,y);
                }
            }
        }
    }
}
