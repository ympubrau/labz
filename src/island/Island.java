package island;

public class Island {

    private FreeCell[][] land;
    private FreeCell[][] landCopy;

    /*
        Создание и инициализация массива
    */
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

    public void printIsland() {
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                land[i][j].print();
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setAnimal(FreeCell a) {
        land[a.i][a.j] = a;
        landCopy[a.i][a.j] = a;
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
                FreeCell temp;

                //размножение
                temp = land[i][j].reproduce(i,j);
                //если не сработала вероятность
                if (temp != null){
                    //если не null, то ищем свободное место и записываем туда новое животное
                    Coordinates t = land[i][j].findFree(land);
                    x = t.x;
                    y = t.y;
                    //если свободных клеток рядом нет, то ничего не делаем
                    if (x != -1) {
                        temp.setCords(t);
                        landCopy[x][y] = temp;
                    }
                }

                //гуляние по острову
                //считаем вероятность
                boolean shouldMove = Cell.isMoving();

                //ищем свободное место
                Coordinates t;
                t = land[i][j].move(land);
                x = t.x;
                y = t.y;

                //значение -1 можно получить ТОЛЬКО, если лиса умерла, поэтому ниже мы создаем новое животное на месте лисы.
                if (x == -1) {
                    landCopy[i][j] = new FreeCell(i,j);
                } else {
                    //если лиса выжила, то просто меняем местами две клетки
                    if (shouldMove) {
                        temp = land[i][j];
                        landCopy[i][j] = new FreeCell(i,j);
                        landCopy[x][y] = temp;
                        landCopy[x][y].setCords(t);
                    }
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
}
