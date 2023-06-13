package lvl1;

public class Sequence {
    public Sequence(Cards[] deckArray) {
        deck = deckArray;
    }
    private Cards[] deck;
    private int start = -1;

    public void show() {            //показать руку
        int st = start;
        Cards tmp;

        while (st != -1){
            tmp = deck[st];            //"сохраняем" карту в пустышку(temp)
            st = tmp.getNext();        //делаем "шаг"
            tmp.out();                    //показываем сохраненную карту
        }
        System.out.println();
    }

    public void addCard(Cards card) {        //добавляем карту в руку
        int i = getCardNumber(card);
        if (start == -1) { //проверка работаем ли мы с первой картой в руке
            start = i;
            deck[start].setNext(-1);
        }
        else {
            int q = getLast();
            deck[q].setNext(i);
            deck[i].setNext(-1);
        }
    }

    public Cards removeCard(){
        Cards temp = new Cards(deck[start].getNumber());
        start = deck[start].getNext();
        return temp;
    }

    public void connectSeq(Sequence a) {
        if (a.start == -1) {
            return;
        }

        if (this.start == -1) {
            this.setStart(a.start);
        }

        //если последовательности не пустые, ищем конец первой последовательности,
        //и некст от этого конца получает старт второй последовательности
        int i = getLast();
        deck[i].setNext(a.start);
        a.setStart(-1);
    }

    public int getLast() {
        int i = start;
        int i2 = deck[i].getNext();

        while (i2 != -1) {        //ищем последнюю карту игрока 1
            i = deck[i].getNext();                    //делаем "шаг" по руке
            i2 = deck[i].getNext();
        }
        return i;
    }

    //используется в методе give, чтобы узнать индекс карты, которую мы хотим выдать игроку
    private int getCardNumber(Cards c) {
        for (int i = 0; i < deck.length; i++) {
            if (deck[i].getNumber() == c.getNumber()) {
                return i;
            }
        }
        return -1;
    }

    public void setStart(int start) {
        this.start = start;
    }
}