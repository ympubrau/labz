package island;

public class Fox extends Animal{
    private int points = 10;

    public Animal reproduce() {
        int t = (int)Math.floor(Math.random() * 6);
        return t > 2 ? new Fox() : null;
    }

    public boolean isFox() {
        return true;
    }

    public void decreasePoints() {
        points --;
    }

    public void increasePoints() {
        points += 3;
    }

    public void print() {
        System.out.print("F ");
    }
}
