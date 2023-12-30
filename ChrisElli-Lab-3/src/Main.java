import java.util.LinkedList;

// Объявления 3 приватных метода: name, price, quantity
class Product {
    private String name;
    private double price;
    private int quantity;

    // Конструктор класса, который инициализирует все 3 поля
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Геттеры для полей класса Product
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

// Объявляется класс Entry с двумя типами параметров K и V.
// Этот класс будет использоваться для хранения пар "ключ-значение" в хеш-таблице.
class Entry<K, V> {
    private K key;
    private V value;
// // Конструктор класса Entry, который инициализирует 2 значения
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
}

class HashTable<K, V> {
    private LinkedList<Entry<K, V>>[] table;
    private int size; // для хранения размера таблицы

    @SuppressWarnings("unchecked") // для подавления предупреждений компилятора c непроверенными операциями в коде
    public HashTable() {
        table = new LinkedList[16];
        size = 0;
    }

    // используется для добавления элемента в хэш-таблицу
    public void put(K key, V value) {
        int index = hash(key); // вычисление индекса в массиве для хранения элемента, используя функцию hash, основанную на ключе.
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) { // совпадает ли ключ элемента из списка с переданным ключом.
                return; // Если ключи совпадают, то метод завершается, и новый элемент не добавляется.
            }
        }
        table[index].add(new Entry<>(key, value));
        size++; // размер таблицы увеличивается на 1
    }

    // для получения значения по ключу.
    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue(); // Если ключи совпадают, метод возвращает соответствующее значение.
                }
            }
        }
        return null; // иначе пустое
    }

    // используется для удаления элемента по ключу
    public void remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            // если полученный ключ совпадает с переданным ключом, то он удаляется
            table[index].removeIf(entry -> entry.getKey().equals(key));
        }
    }

    // возвращает текущий размер хэш-таблицы.
    public int size() {
        return size;
    }

    // используется для вычисления индекса в массиве, основываясь на ключе.
    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length; // определяет индекс для хранения элемента в массиве.
    }
}

class Warehouse {
    private HashTable<String, Product> products;

    public Warehouse() {
        this.products = new HashTable<>();
    }

    public void addProduct(String barcode, Product product) {
        // Use getPrice() and getQuantity() methods
        double price = product.getPrice();
        int quantity = product.getQuantity();
        Product newProduct = new Product(product.getName(), price, quantity); // чтобы избежать изм в ориг. продукте
        products.put(barcode, newProduct);
    }

    public Product findProduct(String barcode) {
        return products.get(barcode);
    }

    public void removeProduct(String barcode) {
        products.remove(barcode);
    }

    public int getNumberOfProducts() {
        return products.size();
    }
}

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        Product product1 = new Product("Apple", 4.5, 100);
        Product product2 = new Product("Banana", 9.4, 150);

        warehouse.addProduct("1234567890", product1);
        warehouse.addProduct("9876543210", product2);

        Product foundProduct = warehouse.findProduct("1234567890");
        if (foundProduct != null) {
            System.out.println("Found product: " + foundProduct.getName() + " (Price: $" + foundProduct.getPrice() + ", Quantity: " + foundProduct.getQuantity() + ")");
        } else {
            System.out.println("Product not found.");
        }

        warehouse.removeProduct("9876543210");

        int numberOfProducts = warehouse.getNumberOfProducts();
        System.out.println("Number of products: " + numberOfProducts);
    }
}
