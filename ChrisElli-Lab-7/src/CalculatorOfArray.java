public class CalculatorOfArray extends Thread {
    private int[] array; // массив, сумму элементов которого нужно вычислить.
    private int start; // начальный индекс подмассива, с которого начинается расчёт.
    private int end; // конечный индекс подмассива, до которого ведётся расчёт.
    private int sum; // переменная для хранения суммы элементов подмассива

    public CalculatorOfArray(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // Переопределяет метод run() из класса Thread.
    // Вычисляет сумму элементов массива в указанном диапазоне.
    public void run() {
        sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
    }

    public int getSum() {
        return sum; // Возвращает вычисленную сумму.
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int mid = array.length / 2; // Определяется середина списка

        // Создаем два потока для вычисления суммы элементов массива по половинкам
        CalculatorOfArray thread1 = new CalculatorOfArray(array, 0, mid);
        CalculatorOfArray thread2 = new CalculatorOfArray(array, mid, array.length);

        // Запускаем потоки
        thread1.start();
        thread2.start();

        // Ожидаем завершение работы потоков
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) { // если было прерывание потока
            e.printStackTrace();
        }

        // Складываем результаты вычислений
        int totalSum = thread1.getSum() + thread2.getSum();
        System.out.println("Total sum of array elements: " + totalSum);
    }
}
