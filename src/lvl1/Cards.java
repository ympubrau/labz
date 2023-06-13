package lvl1;

public class Cards {
    private int number;        //значение карты
    private int next = -1;    //позиция карты в колоде/руке
    public Cards(int number) {        //задает значение карте
        this.number = number;
    }

    public void out() {
        switch (number % 9) {
            case 0 -> {
                System.out.print(" 6");
            }
            case 1 -> {
                System.out.print(" 7");
            }
            case 2 -> {
                System.out.print(" 8");
            }
            case 3 -> {
                System.out.print(" 9");
            }
            case 4 -> {
                System.out.print(" 10");
            }
            case 5 -> {
                System.out.print(" J");
            }
            case 6 -> {
                System.out.print(" Q");
            }
            case 7 -> {
                System.out.print(" K");
            }
            case 8 -> {
                System.out.print(" A");
            }
        }

        switch (number / 9) {
            case 0 -> {
                System.out.print("d"); //diamonds - бубны
            }
            case 1 -> {
                System.out.print("h"); //hearts -- червы
            }
            case 2 -> {
                System.out.print("s"); //spades -- пики
            }
            case 3 -> {
                System.out.print("c"); //clubs -- трефы
            }
        }
    }

    public void setNext(int next) {        //изменение значения next извне
        this.next = next;
    }

    public int getNext() {
        return next;
    }

    public int getNumber() { return number;}

    public int getValue () {
        if(number<9) {
            return number+6;
        } else if (number <18) {
            return number -9+6;
        } else if (number <27) {
            return number -18+6;
        } else {
            return number -27+6;
        }
    }
}
