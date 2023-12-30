import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Storage {
    // Объявляет переменную для хранения общего веса на складе.
    private int totalWeight;
    // Объявляет переменную, которая будет использоваться для управления доступом к ресурсам в многопоточной среде.
    private final Lock lock;
    // Объявляет переменную, используемую для приостановки и возобновления потоков.
    private final Condition condition;

    // Создание конструктора для Storage
    public Storage() {
        this.totalWeight = 0;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    // Метод для добавления веса на склад.
    public void addWeight(int weight) {
        lock.lock(); // Захватывает блокировку перед выполнением операции.
        try {
            while (totalWeight + weight > 150) {
                condition.await(); // приостанавливает текущий поток, ожидая условия для проды
            }
            totalWeight += weight;
            System.out.println("Added " + weight + " kg. Total weight: " + totalWeight + " kg.");
            if (totalWeight >= 150) { // достиг ли вес 150 кг
                //  Сигнализирует всем ожидающим потокам о возможности продолжить работу.
                condition.signalAll();
            }
        } catch (InterruptedException e) { // обработка исключения
            e.printStackTrace(); // для вывода трассировки стека исключения
        } finally {
            lock.unlock(); // Освобождает блокировку
        }
    }

    //  Метод для выгрузки веса со склада
    public void unload() {
        lock.lock();
        try {
            while (totalWeight < 150) {
                condition.await();
            }
            System.out.println("Unloading 150 kg.");
            totalWeight -= 150;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Storage warehouse = new Storage();
        // Поток в котором будет добавляться вес 40 кг
        Thread loader1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                warehouse.addWeight(40);
            }
        });

        Thread loader2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                warehouse.addWeight(10);
            }
        });

        Thread loader3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                warehouse.addWeight(5);
            }
        });
        // поток отвечающий за разгрузку склада в случае переполнения
        Thread unloader = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                warehouse.unload();
            }
        });
        // создание потоков которые выполняют загрузку и выгрузку веса на складе
        loader1.start();
        loader2.start();
        loader3.start();
        unloader.start();
    }
}
