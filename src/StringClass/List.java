package StringClass;

public class List {
    //элемент списка
    private static class StringItem {
        //массив для храннеия чаров
        private final char [] symbols;

        //переменная для хранения занятых мест в массиве
        private byte size;

        //ссылка на следующий элемент списка
        private StringItem next;

        public StringItem(char[] a, byte b, StringItem c){
            symbols = a;
            size = b;
            next = c;
        }
    }

    //дополнительный класс чтобы возвращать позицию элемента и айтем в одной переменной (нужен для методов поиска)
    private static class Wrapper {
        public Wrapper(int a, StringItem b) {
            position = a;
            node = b;
        }
        //позиция внутри айтема (индекс в массиве)
        private final int position;

        //ссылка на айтем
        private final StringItem node;
    }

    //размер айтема
    final static int SIZE = 16;
    private StringItem head;

    //соединение двух массивов
    //параметры: из какого массива, в какой массив, сколько занятых элементов в первом, сколько занятых во втором
    private void unionArrays(char[] ar1, char [] ar2, int sz1, int sz2) {
        if(sz1 + sz2 <= SIZE) {
            for (int i = 0; i < sz2; i++) {
                ar1[sz1+i] = ar2[i];
                //System.out.println(ar2[i]);
            }
        }
    }

    private char[] getCharArrayFromString(String str, int start, int end) {
        char[] ar = new char[16];

        int q = 0;
        for (int i = start; i < end; i++) {
            ar[q] = str.charAt(i);
            //System.out.println(ar[q]);
            q++;
        }
        //System.out.println("---------------------");

        return ar;
    }

    //поиск по индексу
    private Wrapper search(int index) {
        //System.out.println(index);
        if (index <= this.head.size) {
            return (new Wrapper(index - 1, head));
        }

        StringItem g = this.head;
        int q = 0;

        while (g != null) {
            q += g.size;
            if (q >= index) {
                return (new Wrapper(index - q + g.size, g));
            }

            g = g.next;
        }
        return (new Wrapper(-1, null));
    }

    //поиск последнего элемента со сцепкой айтемов
    //То есть во время данного метода происходит оптимизация всех нодов
    private Wrapper searchEnd() {
        StringItem g = this.head;
        StringItem h = g.next;

        while (h != null) {
            g = h;
            h = h.next;
        }
        return (new Wrapper(g.size, g));
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

        //если строка помещается в айтем
        if(len <= SIZE) {
            //Преобразуем исходную строку в массив чаров, и записываем исходную строку в массив chararray
            q.getChars(0, len, chararray, 0);
            //помещаем строку в голову
            head = new StringItem(chararray, (byte) len, null);
        }
        else {
            //Преобразуем исходную строку в массив чаров, и записываем исходную строку в массив chararray
            q.getChars(0, SIZE, chararray,0);

            //создаем голову нового списка
            head = new StringItem(chararray, (byte) SIZE, head);
            StringItem h = head;

            //создаем айтемы длиной SIZE и записываем в них соответвующие элементы.
            //Если в исходной строке нет полных нодов (кроме головы), в цикл мы не зайдем.
            for (;m < len;n += SIZE) {
                chararray = new char[SIZE];
                q.getChars(n,m,chararray,0);
                h.next = new StringItem(chararray, (byte) SIZE, null);
                h = h.next;
                m += SIZE;
            }

            //если длина исходной строки не кратна SIZE - отдельно записывается последний node
            //копируем остатки строки в айтем. Если число символов в строке кратно SIZE - в цикл не зайдем.
            if (m - SIZE != len){
                chararray = new char[SIZE];
                m = len - (len - m + SIZE);
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
    public char charAt(int index) {
        if(index <= 0)
            throw new myException("Не подходящий индекс");

        Wrapper x = this.search(index);
        if (x.position == -1 ) throw new myException("Не подходящий индекс");

        //возвращаем символ через вспомогательный класс
        return(x.node.symbols[x.position]);
    }

    //заменить в строке символ в позиции index на символ ch
    public void setCharAt(int index, char symbol) {
        if(index <= 0 || index > this.length())
            throw new myException("Не подходящий индекс");;

        Wrapper x = this.search(index);

        //меняем символ через вспомогательный класс
        x.node.symbols[x.position] = symbol;
    }

    //взятие подстроки, от start до end, не включая end, возвращается новый объект ListString, исходный не изменяется
    public List substring(int start, int end) {
        if(start <= 0 || end <= 0 || start > end || end < start+1 )
            throw new myException("Не подходящий индекс");

        //поиск начала и конца искомого отрезка строки
        Wrapper x = this.search(start);
        Wrapper y = this.search(end);
        if (x.position == -1 || y.position == -1)  throw new myException("Такой позиции нет в списке");

        //Если отрезок нужной строки находится в одном ноде
        if (x.node==y.node){
            //копируем часть массива чаров и переделываем его в строку
            return(new List(String.copyValueOf(x.node.symbols, x.position, y.position - x.position)));
        } else {
            StringItem h = x.node;

            //копируем все элементы (с нужного по последний) в первом айтеме и добавляем к строке
            List str = new List(String.copyValueOf(h.symbols, x.position, h.size-x.position));
            h = x.node.next;
            //str.printSize();

            //полностью копируем остальные айтемы в строку
            while (h!=y.node) {
                str.append(String.copyValueOf(h.symbols, 0, h.size));
                h = h.next;
            }

            //копирование с первого элемента по нужный в последнем айтеме
            str.append(String.copyValueOf(y.node.symbols, 0, y.position));
            return(str);
        }
    }

    //добавить в конец строки символ (в конец символьного массива последнего блока, если там есть свободное место, иначе в начало символьного массива нового блока)
    public void append(char symbol) {
        //если нужно создавать новый айтем
        if (this.length() % SIZE == 0) {
            char[] arr = new char[SIZE];
            arr[0] = symbol;
            head = new StringItem(arr, (byte) 1, head);
        }
        else {
            //если новый айтем не нужен - добавляем элемент в последний айтем.
            Wrapper x = this.searchEnd();
            x.node.symbols[x.position] = symbol;
            this.searchEnd().node.size += 1;
        }
    }

    //добавить в конец строку String (перекинуть указатель на следующий последнего блока исходной строки на голову добавляемой строки)
    public void append(String string1) {
        //преобразуем строку в наш класс и вызываем другой метод
        this.append(new List(string1));
    }

    //добавить в конец строку ListString (перекинуть указатель на следующий последнего блока исходной строки на голову добавляемой строки)
    public void append(List string1) {
        //создание копии объекта и добавление ее к последнему айтему
        List l = new List(string1);
        this.searchEnd().node.next = l.head;
    }

    //вспомогательный метод для отладки (показывает количество занятых элементов в каждом айтеме)
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
        if(index <= 0)
            throw new myException("Не подходящий индекс");

        //создаем копию объекта, а также ищем айтем в который надо вставить, а также предыдущий айтем

        Wrapper x = this.search(index);
        Wrapper x1 = this.search(index - x.position - 1);
        if (x.position == -1)  throw new myException("Не подходящий индекс");

        List l = new List(str);

        //вставка в первый айтем
        if (x.position == 0 && x.node == head) {
            l.append(this);
            this.head = l.head;
            return;
        }

        //вставка, между двумя айтемами в конец
        if (x.position == x.node.size - 1) {
            l.searchEnd().node.next = x.node.next;
            x.node.next = l.head;
            return;
        }

        //остальные варианты
        str = new String(x.node.symbols);
        //деление айтема на два
        StringItem q1 = new StringItem(getCharArrayFromString(str, 0, x.position), (byte) (x.position), null);
        StringItem q2 = new StringItem(
                getCharArrayFromString(str, x.position, x.node.size),
                (byte) (x.node.size - x.position),
                x.node.next);

        //если делится голова - обрабатываеся отдельно, поскольку в таком случае x1 = -1 (у головы нет предыдущего айтема)
        if (head == x.node)
            head = q1;
        else
            x1.node.next = q1;

        //перекидывание ссылок
        q1.next = l.head;
        x = l.searchEnd();
        x.node.next = q2;
    }

    //реальная длина строки
    public int length() {
        StringItem g = this.head;
        StringItem h = g.next;
        int q = g.size;

        while (h != null) {
            q += h.size;

            //если один элемент полностью помещается во второй, то переносим второй массив в первый и перекидываем ссылку
            if(g.size + h.size <= SIZE) {
                unionArrays(g.symbols, h.symbols, g.size, h.size);
                g.size += h.size;
                g.next = h.next;
            }

            g = h;
            h = h.next;
        }
        return q;
    }

    //Строковое представление объекта ListString (переопределить метод)
    public String toString() {
        String str = "";
        StringItem g = this.head;

        while (g != null) {
            str += String.copyValueOf(g.symbols, 0, g.size);
            g = g.next;
        }

        return str;
    }

    public static void main(String[] args){
        System.out.println("Input String:");
        List l1 = new List("Hello wonderful world");
        System.out.println(l1);
        System.out.println();

        System.out.println("Appending exclamation mark to the end:");
        l1.append('!');
        System.out.println(l1);
        System.out.println();

        System.out.println("Setting 'W' in 'wonderful' to uppercase:");
        l1.setCharAt(7,  'W');
        System.out.println(l1);
        System.out.println();

        System.out.println("Inserting a comma after 'Hello'");
        l1.insert(6, ",");
        System.out.println(l1);
        System.out.println();

        System.out.println("getting the word 'Wonderful' with subString:");
        System.out.println(l1.substring(7, 16));
        System.out.println();

        System.out.println("String length: " + "Hello, Wonderful world!".length() + " | Our String length: " + l1.length());
    }
}