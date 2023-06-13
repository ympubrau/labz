package island;

public class Animal {
    public void print() {
        System.out.print("_ ");
    }

    /*
        Статический метод, который возвращает True, если сработала вероятность 1/9
    */
    public static boolean isMoving() {
        int t = (int)Math.floor(Math.random() * 9);
        return t == 8;
    }

    public boolean isFox() {
        return false;
    }

    public boolean isRabbit() {
        return false;
    }

    public void decreasePoints() {}

    public void increasePoints() {}

    /*
        Данный метод нужен для размножения. Он будет возвращать объект суперкласса, поэтому
        даже после переопределения, получившийся объект (Лису, кролика или просто животное)
        можно будет использовать в массиве в классе Island.
     */
    public Animal reproduce() {
        return this;
    }
}
