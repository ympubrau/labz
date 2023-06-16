package island;

public class Rabbit extends Animal{
    /*
        С вероятностью 1/2 данный метод возвращает нового кролика. В противном случае - возвращает нулл
     */
    public Animal reproduce() {
        int t = (int)Math.floor(Math.random() * 2);
        return t == 1 ? new Rabbit() : null;
    }

    public boolean isRabbit() {
        return true;
    }

    public void print() {
        System.out.print("   R   ");
    }
}
