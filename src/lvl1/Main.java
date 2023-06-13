package lvl1;/*Колода карт состоит из 36 листов. Колода перемешивается, а затем раздается полностью двум
игрокам: 1-ая карта – 1-ому игроку, 2-ая карта – 2-ому игроку, 3-ая карта – 1-ому игроку, 4-ая карта
– 2-ому игроку и т. д. Напишите программу, используя объектно-ориентированную методологию,
которая создает объект колода, выводит колоду на экран, перемешивает колоду, вновь выводит ее
на экран. Далее создает объекты игроков, раздает карты колоды игрокам. При раздаче карты
добавляются в конец последовательности, для этого выполняется проход от начала до конца
последовательности и только затем добавляется очередная карта. Раздав все карты, программа
выводит на экран последовательности карт каждого игрока. Затем выполняются три раунда игры,
во время каждого раунда игроки кладут по одной карте (первой в их последовательности) на стол
(объект последовательность карт). Если достоинство карты 1-го игрока оказывается больше
достоинства карты 2-го игрока, то вся последовательность карт стола помещается в конец
последовательности карт 1-го игрока. Если же достоинство карты 2-го игрока оказывается больше
или равно достоинству карты 1-го игрока, то вся последовательность карт стола помещается в
конец последовательности карт 2-го игрока. После добавления последовательности карт стола в
конец последовательности карт игрока, она становится пустой. Во время каждого раунда
необходимо выводить на экран исходное состояние последовательностей карт игроков,
последовательность карт стола, и состояние последовательностей карт игроков после
присоединения последовательности карт стола. Используйте для хранения колоды и
последовательностей карт игроков и стола только один массив из 36 элементов.*/

public class Main {
    public static void main(String[] args){
        Deck deck = new Deck();
        Sequence p1 = new Sequence(deck.getDeck());
        Sequence p2 = new Sequence(deck.getDeck());
        Sequence table = new Sequence(deck.getDeck());
        System.out.print("START: ");
        deck.show();

        deck.shuffle();                                        //Перемешиваем колоду
        System.out.print("SHUFFLED: ");
        deck.show();


        deck.destribute(p1,p2);                                        //раздать 2 игрокам
        System.out.print("FIRST : ");
        p1.show();
        System.out.print("SECOND: ");
        p2.show();
        System.out.println();
        System.out.println();

        fight(p1, p2, table);
    }
    public static void fight(Sequence a, Sequence b, Sequence table) {
        for(int k=0; k<3; k++) {
            System.out.println("ROUND " + (k + 1));

            System.out.print("1 player cards: ");
            a.show();
            System.out.print("2 player cards: ");
            b.show();
            System.out.println();

            //забираем карты у игроков
            Cards firstPlayerCard = a.removeCard();
            Cards secondPlayerCard = b.removeCard();

            //кладем их на стол
            table.addCard(firstPlayerCard);
            table.addCard(secondPlayerCard);

            System.out.println("TABLE");
            table.show();
            System.out.println();

            if (firstPlayerCard.getValue() >= secondPlayerCard.getValue()) {
                b.connectSeq(table);
                System.out.println("1 player win!");
                System.out.println();
            } else {
                a.connectSeq(table);
                System.out.println("2 player win!");
                System.out.println();
            }

            System.out.print("1 player cards: ");
            a.show();
            System.out.print("2 player cards: ");
            b.show();
            System.out.println();
            System.out.println();
        }
    }
}