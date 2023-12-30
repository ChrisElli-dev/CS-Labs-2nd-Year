import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Task 1: Нахождение среднего арифметического элементов массива
        Object[] arr = {1, 2, 3, 4, 5, "six"}; // создание массива объектов
        double sum = 0;
        try { // выполняется код в который может вызвать исключение
            for (Object obj : arr) { // цикл for-each, проходящий по каждому элементу массива
                if (obj instanceof Integer) { // является ли массив целым числом
                    sum += (Integer) obj; // если да, то он прибавляется к сумме
                } else {
                    // если нет, генерируется исключение с сообщением об ошибке
                    throw new IllegalArgumentException("Элемент массива не является числом: " + obj);
                }
            }
            double average = sum / arr.length; // ср.арифметическое всех элементов массива
            System.out.println("Среднее арифметическое: " + average);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: выход за границы массива");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }


        // Task 2: Копирование содержимого файла в другой файл
        try {
            String sourceFileName = "источник.txt";
            String destinationFileName = "цель.txt";//Определение имен исходного и целевого файлов
            //для чтения из исходного файла и записи в целевой файл соответственно
            FileInputStream sourceFile = new FileInputStream(sourceFileName);
            FileOutputStream destinationFile = new FileOutputStream(destinationFileName);

            byte[] buffer = new byte[1024];
            int bytesRead;// переменная для прочитанных байт

            while ((bytesRead = sourceFile.read(buffer)) != -1) {//чтение файла до конца
                destinationFile.write(buffer, 0, bytesRead);//Запись данных из буфера в целевой файл
            }

            System.out.println("Файл успешно скопирован.");
            sourceFile.close();
            destinationFile.close();
        } catch (IOException e) {//обрабатывает исключения, которые могут возникнуть во время операций чтения
            System.err.println("Произошла ошибка при копировании файла: " + e.getMessage());
        }

        // Задание 3: Обработка пользовательского исключения CustomAgeException
        try {
            // используется для чтения данных, введенных пользователем через консоль.
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите возраст пользователя: ");
            int age = scanner.nextInt(); // после ввода возраста информация хранится в age

            if (age < 0 || age > 120) {
                throw new CustomAgeException("Недопустимый возраст"); // выброшено исключение
            }

            System.out.println("Возраст пользователя: " + age);
        } catch (CustomAgeException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }
}

class CustomAgeException extends Exception {
    public CustomAgeException(String message) {
        super(message);
    }//Определение пользовательского класса исключений CustomAgeException, который наследуется от класса Exception
}
