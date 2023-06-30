package island;

public abstract class Cell {

    //координаты
    protected int i;
    protected int j;

    public Cell(int X, int Y) {
        i = X;
        j = Y;
    }

    public abstract Coordinates getCords();

    public abstract void print();

    /*
        Статический метод, который возвращает True, если сработала вероятность 1/9
    */
    public static boolean isMoving() {
        int t = (int)Math.floor(Math.random() * 9);
        //return true;
        return t == 8;
    }

    /*
        Методы для проверки на лису и на кролика.
        В программе нам определенно точно нужно различать все три вида клеток,
        причем в разных моментах работы программы.
        Когда лиса ходит, ей точно нужно знать на какую из трех клеток она попала
        (уменьшение очков, если пустая клетка
         увеличение очков, если клетка с кроликом
         нельзя походить, если в клетке лиса)
         Кролику надо отличать пустую клетку от клетку с лисой / кроликом.
    */
    public abstract boolean canBeGone();

    public abstract boolean canBeEaten();

    /*
        Данный метод нужен для размножения. Он будет возвращать объект суперкласса, поэтому
        даже после переопределения, получившийся объект (Лису, кролика или просто животное)
        можно будет использовать в массиве в классе Island.
     */
    public abstract FreeCell reproduce(int i, int j);

    public abstract Coordinates move(FreeCell[][] land);

    //поиск свободного элемент
    //ищет клетку, где не стоят ни кролики ни лисы и возвращает ее кординаты
    public abstract Coordinates findFree(FreeCell[][] land);
}
