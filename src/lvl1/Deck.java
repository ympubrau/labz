package lvl1;

import java.util.Random;

public class Deck {
    private Cards[] deck = new Cards[36];

    public Deck() {                            //задает значения картам
        for (int i = 0; i < 36; i++) {
            deck[i] = new Cards(i);
        }
    }
    public void show() {
        for(int i = 0; i < deck.length; i++) {
            deck[i].out();
        }
        System.out.println();
    }

    public void shuffle() {
        Random random=new Random();
        int randomIndex;
        Cards temp;
        for(int i = 0; i < 18; i++) {
            randomIndex=random.nextInt(36);        //генерируем рандомный индекс до 36
            temp=deck[i];
            deck[i]=deck[randomIndex];
            deck[randomIndex]=temp;
            deck[i].setNext(i);
        }
    }
    
    public void destribute(Sequence a, Sequence b) {
        for (int i = 0; i < 36; i += 2) {
            a.addCard(deck[i]);
            b.addCard(deck[i + 1]);
        }
    }
    Cards[] getDeck() {
        return deck;
    }
}