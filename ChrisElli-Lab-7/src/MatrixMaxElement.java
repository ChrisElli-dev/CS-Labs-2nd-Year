public class MatrixMaxElement extends Thread {
    private int[] row; // будет использоваться для хранения одной строки матрицы.
    private int maxElement; //  для хранения максимального элемента в данной строке.

    public MatrixMaxElement(int[] row) {
        this.row = row;
    }

    public void run() {
        maxElement = row[0];
        for (int i = 1; i < row.length; i++) { // проходим по элементам строк
            // обновляем максимальный элемент, если он больше найденного максимального
            if (row[i] > maxElement) {
                maxElement = row[i];
            }
        }
    }

    public int getMaxElement() {
        return maxElement;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        MatrixMaxElement[] finders = new MatrixMaxElement[matrix.length];

        // Создаем потоки для каждой строки матрицы
        for (int i = 0; i < matrix.length; i++) {
            finders[i] = new MatrixMaxElement(matrix[i]);
            finders[i].start();
        }

        // Ожидаем завершения работы всех потоков
        try {
            for (MatrixMaxElement finder : finders) {
                finder.join();
            }
        } catch (InterruptedException e) { // если поток нарушен
            e.printStackTrace();
        }

        // Находим наибольший элемент в матрице
        int maxElement = finders[0].getMaxElement();
        for (int i = 1; i < finders.length; i++) {
            if (finders[i].getMaxElement() > maxElement) {
                maxElement = finders[i].getMaxElement();
            }
        }

        System.out.println("Biggest element of the Matrix is: " + maxElement);
    }
}
