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

    public Animal decreasePoints() {
        points --;
        if (points <= 0) return new Animal();
        return this;
    }

    public void increasePoints() {
        points += 3;
    }

    public void print() {
        String str = points < 10 ? "0" + points : String.valueOf(points);
        System.out.print("F (" + str + ") ");
    }
}
