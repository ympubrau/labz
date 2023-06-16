package island;

public class Animal {
    public void print() {
        System.out.print("   _   ");
    }

    /*
        Статический метод, который возвращает True, если сработала вероятность 1/9
    */
    public static boolean isMoving() {
        int t = (int)Math.floor(Math.random() * 9);
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
    public boolean isFox() {
        return false;
    }

    public boolean isRabbit() {
        return false;
    }

    public Animal decreasePoints() { return this; }

    public void increasePoints() {}

    /*
        Данный метод нужен для размножения. Он будет возвращать объект суперкласса, поэтому
        даже после переопределения, получившийся объект (Лису, кролика или просто животное)
        можно будет использовать в массиве в классе Island.
     */
    public Animal reproduce() {
        return null;
    }
}
