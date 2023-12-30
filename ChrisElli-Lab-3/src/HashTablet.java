import java.util.LinkedList; // импорт класс LinkedList для пакета java.util

public class HashTablet<K, V> {
    private class Entry {
        K key; // ключ
        V value; // значение

        // имеет конструктор для иниц и методы для доступа к полям
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) { // сеттер для установки нового значения
            this.value = value;
        }
    }

    // массив связанных списков для хранения объектов типа Entry
    // Этот массив будет использоваться для реализации хэш-таблицы.
    private LinkedList<Entry>[] table;
    private int size; // представляет кол-во элементов в хэш-таблице

    // конструктор инициализирует массив table с заданной емкостью и устанавливает значение size равным 0.
    public HashTablet(int capacity) {
        table = new LinkedList[capacity];
        size = 0;
    }

    // Этот метод вычисляет индекс хэша для данного ключа.
    // Он использует метод hashCode() для вычисления хэш-кода ключа, а затем
    // использует остаточный оператор (%) для вычисления индекса в пределах массива table.
    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length; // вычисляет и возвращает индекс ключа
    }

    public void put(K key, V value) {
        int index = hash(key); // вычисляет индекс для ключа
        if (table[index] == null) { // проверка, есть ли связанный список на этом индексе
            table[index] = new LinkedList<>(); // если нет - создаем новый список
        }

        for (Entry entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        table[index].add(new Entry(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null; // не найден
    }

    public V remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    V value = entry.getValue();
                    table[index].remove(entry);
                    size--;
                    return value;
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0; // true, если таблица пуста
    }

    public static void main(String[] args) {
        HashTablet<String, Integer> hashTable = new HashTablet<>(10);
        hashTable.put("apple", 5);
        hashTable.put("banana", 3);
        hashTable.put("orange", 7);

        System.out.println("Size after adding 3 elements: " + hashTable.size());
        System.out.println("Value for key 'apple': " + hashTable.get("apple"));
        System.out.println("Value for key 'banana': " + hashTable.get("banana"));
        System.out.println("Value for key 'orange': " + hashTable.get("orange"));

        System.out.println("Removed value for key 'apple': " + hashTable.remove("apple"));
        System.out.println("Size after removing 1 element: " + hashTable.size());

        System.out.println("Is hash table empty? " + hashTable.isEmpty());
    }
}