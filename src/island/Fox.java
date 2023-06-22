package island;

public class Fox extends Animal{
    private int points = 5;

    public Animal reproduce() {
        int t = (int)Math.floor(Math.random() * 6);
        return t > 2 ? new Fox() : null;
    }

    public boolean canBeGone() {
        return true;
    }

    public void print() {
        String str = points < 10 ? "0" + points : String.valueOf(points);
        System.out.print("F (" + str + ") ");
    }

    public Animal move (Animal target) {
        if (target.canBeEaten()) {
            System.out.println("EATEN");
            points += 3;
            return this;
        } else {
            points --;
            return points <= 0 ? new Animal() : this;
        }
    }
}
