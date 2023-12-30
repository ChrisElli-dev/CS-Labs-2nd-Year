import java.util.EmptyStackException;
// для сигнализации о попытке извлечь элемент из пустого стека.
public class Stack<T> { // Stack с использованием обобщения (T), что делает его типо-безопасным.
    private T[] data; // data - массив для хранения элементов стека. size - текущий размер стека.
    private int size;

    public Stack(int capacity) { // capacity - максимальную емкость стека.
        data = (T[]) new Object[capacity]; // data инициализируется этим размером
        size = 0;
    }

    public void push(T element) {
        if (size == data.length) {
            throw new StackOverflowError("Stack is full");
        }
        data[size++] = element;
    } // Метод добавляет элемент в стек. Если стек уже полон, генерируется исключение StackOverflowError.

    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T element = data[--size];
        data[size] = null;
        return element;
    } // Метод удаляет и возвращает верхний элемент стека. Если стек пуст, генерируется исключение EmptyStackException.

    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return data[size - 1];
    } // Метод возвращает верхний элемент стека без его удаления. Если стек пуст, генерируется исключение EmptyStackException.
}
