package StringClass;

public class List {

    public class StringItem {
        private final char [] symbols;
        private byte size;
        private StringItem next;

        public StringItem(char[] a, byte b, StringItem c){
            symbols = a;
            size = b;
            next = c;
        }
    }

    //дополнительный класс чтобы возвращать позицию элемента и айтем в одной переменной
    public class Wrapper {
        public Wrapper(int a, StringItem b) {
            position = a;
            node = b;
        }
        private final int position;
        private final StringItem node;
    }

    final static int SIZE = 16;
    private StringItem head;

    //соединение двух массивов
    public void unionArrays(char[] ar1, char [] ar2, int sz1, int sz2) {
        if(sz1 + sz2 <= SIZE) {
            for (int i = 0; i < sz2; i++) {
                ar1[sz1+i] = ar2[i];
            }
        }
    }

    //поиск по индексу
    public Wrapper search(int index) {
        StringItem g = this.head;
        StringItem h = g.next;
        int q = g.size;

        while (q < index) {
            q += h.size;
            g = h;
            h = h.next;
        }

        if (g == head)
            return (new Wrapper(index - 1, head));
        else
            return (new Wrapper(index - q - 1 + h.size, g));
    }

    //поиск последнего элемента со сцепкой айтемов
    //То есть во время данного метода происходит оптимизация всех нодов
    public Wrapper searchEnd() {
        StringItem g = this.head;
        StringItem h = g.next;
        while (h != null) {
            if(h.next!=null) {
                if(g.size + h.size <= SIZE) {
                    unionArrays(g.symbols, h.symbols, g.size, h.size);
                    g.size += h.size;
                    g.next = h.next;
                }
            }
            g = h;
            h = h.next;
        }
        return (new Wrapper(g.size - 1, g));
    }


    public void print() {
        System.out.println(this);
    }

    public List() {
        head =  null;
    }

    //конструктор, преобразующий обычную строку в новый тип данных
    public List(String q) {
        int m = SIZE * 2;
        int n = SIZE;
        int len = q.length();
        char[] chararray = new char[SIZE];

        //если строка помещается в node
        if(len <= SIZE) {
            q.getChars(0, len, chararray, 0);
            head = new StringItem(chararray, (byte) len, null);
        }
        else {
            //получаем массив чаров и преобразовываем его в наш тип
            q.getChars(0, SIZE, chararray,0);
            head = new StringItem(chararray, (byte) SIZE, head);
            StringItem h = head;

            //создаем ноды длиной SIZE и записываем в них соответвующие элементы
            for (;m < len;n += SIZE) {
                chararray = new char[SIZE];
                q.getChars(n,m,chararray,0);
                h.next = new StringItem(chararray, (byte) SIZE, null);
                h = h.next;
                m += SIZE;
            }

            //если длина исходной строки не кратна SIZE - отдельно записывается последний node
            if(m - SIZE != len){
                chararray = new char[SIZE];
                m = len- (len - m + SIZE);
                q.getChars(m, len ,chararray,0);
                h.next = new StringItem(chararray, (byte) (len - m), null);
            }
        }
    }

    //копирующий консутруктор
    public List(List q) {
        StringItem f = q.head;
        head = new StringItem(f.symbols, f.size, head);
        StringItem h = head;

        while (f != null) {
            h.next = new StringItem(f.symbols, f.size, null);
            f = f.next;
            h = h.next;
        }
    }

    //вернуть символ в строке в позиции index
    public char CharAt(int index) {
        if(index <= 0 || index > this.length())
            throw new myException("Не подходящий индекс");

        Wrapper x = this.search(index);
        return(x.node.symbols[x.position]);
    }

    //заменить в строке символ в позиции index на символ ch
    public void SetCharAt(int index, char symbol) {
        if(index <= 0 || index > this.length())
            throw new myException("Не подходящий индекс");;

        Wrapper x = this.search(index);
        x.node.symbols[x.position] = symbol;
    }

    //взятие подстроки, от start до end, не включая end, возвращается новый объект ListString, исходный не изменяется
    public List substring(int start, int end) {
        if(start <= 0 || end <= 0 || start > end || start > this.length() || end > this.length() || end < start+1 )
            throw new myException("Не подходящий индекс");

        Wrapper x = this.search(start);
        Wrapper y = this.search(end);
        //System.out.println(y.position+" "+x.position);

        //Если кусок нужной строки находится в одном ноде
        if (x.node==y.node)
            return(new List(String.copyValueOf(x.node.symbols, x.position, y.position - x.position)));
        else {
            StringItem h = x.node;

            //копируем все элементы(с нужного по последний) в первом айтеме
            List str = new List(String.copyValueOf(h.symbols, x.position, h.size-x.position));
            h = x.node.next;
            //str.printSize();

            //полностью копируем остальные айтемы в строку
            while (h!=y.node) {
                str.append(String.copyValueOf(h.symbols, 0, h.size));
                h=h.next;
            }

            //копирование с первого элемента по нужный в последнем айтеме
            str.append(String.copyValueOf(y.node.symbols, 0, y.position));
            return(str);
        }
    }

    //добавить в конец строки символ (в конец символьного массива последнего блока, если там есть свободное место, иначе в начало символьного массива нового блока)
    public void append(char symbol) {

        //если нужно создавать новый node
        if (this.length()% SIZE == 0) {
            char[] arr = new char[SIZE];
            arr[0]=symbol;
            head = new StringItem(arr, (byte) 1, head);
        }
        else {
            Wrapper x = this.searchEnd();
            x.node.symbols[x.position] = symbol;
            this.search(this.length()).node.size += 1;
        }
    }

    //добавить в конец строку String (перекинуть указатель на следующий последнего блока исходной строки на голову добавляемой строки)
    public void append(String string1) {
        this.append(new List(string1));
    }

    //добавить в конец строку ListString (перекинуть указатель на следующий последнего блока исходной строки на голову добавляемой строки)
    public void append(List string1) {
        List l = new List(string1);
        this.searchEnd().node.next = l.head;
    }

    //вспомогательный метод для отладки
    public void printSize() {
        StringItem h = this.head;
        while (h != null) {
            System.out.print(h.size + " ");
            h = h.next;
        }
        System.out.println();
    }

    public void insert(int index, List string) {
        this.insert(index, string.toString());
    }

    //вставить в строку в позицию index строку ListString (разбить блок на два по позиции index и строку вставить между этими блоками)
    public void insert(int index, String str) {
        if(index <= 0 || index > this.length())
            throw new myException("Не подходящий индекс");

        List l = new List(str);
        Wrapper x = this.search(index);
        Wrapper x1 = this.search(index - x.position - 1);
        //System.out.println(x.position);

        //вставка в первый айтем
        if (x.position == 0 && x.node == head) {
            l.append(this);
            this.head = l.head;
        }

        //вставка, между двумя айтемами в конец
        if (x.position == x.node.size - 1) {
            l.searchEnd().node.next = x.node.next;
            x.node.next = l.head;
        }

        //остальные варианты
        else {
            str = new String(x.node.symbols);

            //деление айтема на два
            StringItem q1 = new StringItem(str.substring(0, x.position).toCharArray(), (byte) (x.position), null);
            StringItem q2 = new StringItem(str.substring(x.position, x.node.size).toCharArray(), (byte) (x.node.size - x.position), x.node.next);

            if (head == x.node)
                head = q1;
            else
                x1.node.next = q1;

            q1.next = l.head;
            x = l.search(l.length());
            x.node.next = q2;
        }
    }

    //реальная длина строки
    public int length() {
        StringItem h = this.head;
        int q = 0;
        while (h != null) {
            q += h.size;
            h = h.next;
        }
        return q;
    }

    //Строковое представление объекта ListString (переопределить метод)
    public String toString() {
        StringItem h = this.head;
        StringBuilder str = new StringBuilder();
        while (h!=null) {
            str.append(String.copyValueOf(h.symbols, 0, h.size));
            h = h.next;
        }
        return str.toString();
    }

    public static void main(String[] args){
    }

}