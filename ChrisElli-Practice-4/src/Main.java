import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.TreeMap;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(nonRepeatableCharacters("abracadabra"));
        System.out.println(nonRepeatableCharacters("paparazzi"));

        System.out.println(generateParentheses(1));
        System.out.println(generateParentheses(2));
        System.out.println(generateParentheses(3));

        System.out.println(binarySequences(3));
        System.out.println(binarySequences(4));

        System.out.println(sequentialAlphabetic("abcdjuwx"));
        System.out.println(sequentialAlphabetic("klmabzyxw"));

        System.out.println(compressThenSort("aaabbcdd"));
        System.out.println(compressThenSort("vvvvaajaaaaa"));

        System.out.println(textToNumber("eight"));
        System.out.println(textToNumber("five hundred sixty seven"));
        System.out.println(textToNumber("thirty one"));

        System.out.println(uniqueSubsequence("123412324"));
        System.out.println(uniqueSubsequence("111111"));
        System.out.println(uniqueSubsequence("77897898"));

        int[][] matrix1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(minimumPathSum(matrix1));
        int[][] matrix2 = {
                {2, 7, 3},
                {1, 4, 8},
                {4, 5, 9}
        };
        System.out.println(minimumPathSum(matrix2));

        System.out.println(orderByNumber("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(orderByNumber("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));


        System.out.println(exchangeDigits(519, 723));
        System.out.println(exchangeDigits(491, 3912));
        System.out.println(exchangeDigits(6274, 71259));
    }

    // Задание 1
    public static String nonRepeatableCharacters(String str) {
        // Этот метод возвращает строку, в которой каждый символ встречается только один раз.
        return helperFunction(str, new HashSet<>(), 0); // Использует helperFunction для удаления повторов.
    }

    private static String helperFunction(String str, HashSet<Character> seen, int index) {
        // Это рекурсивная вспомогательная функция.
        if (index >= str.length()) {
            // Проверяет, если индекс достиг конца строки, возвращает пустую строку.
            return "";
        }

        char ch = str.charAt(index); // Получает символ из строки по текущему индексу.
        if (seen.contains(ch)) {
            // Если этот символ уже встречался, пропускает его и вызывает функцию для следующего символа.
            return helperFunction(str, seen, index + 1);
        }

        seen.add(ch); // Добавляет новый символ в набор уже встреченных символов.
        return ch + helperFunction(str, seen, index + 1); // Возвращает этот символ и продолжает рекурсию.
    }

    // Задание 2
    public static List<String> generateParentheses(int n) {
        // Генерирует все возможные правильные скобочные последовательности для n пар скобок.
        List<String> result = new ArrayList<>(); // Создает список для хранения результатов.
        generateSequence("", 0, 0, n, result); // Вызывает рекурсивный метод generateSequence.
        return result; // Возвращает список сгенерированных последовательностей.
    }

    private static void generateSequence(String cur, int open, int close, int max, List<String> res) {
        // Рекурсивная функция для генерации скобочных последовательностей.
        if (cur.length() == max * 2) {
            // Если текущая строка достигла максимальной длины, добавляет ее в результат.
            res.add(cur);
            return;
        }
        if (open < max) generateSequence(cur + "(", open + 1, close, max, res); // Добавляет открывающую скобку и продолжает рекурсию.
        if (close < open) generateSequence(cur + ")", open, close + 1, max, res); // Добавляет закрывающую скобку и продолжает рекурсию.
    }


    // Задание 3
    public static List<String> binarySequences(int n) {
        // Генерирует бинарные последовательности длины n без двух подряд идущих нулей.
        List<String> result = new ArrayList<>(); // Создает список для хранения результатов.
        binaryHelper("", n, result); // Вызывает вспомогательный рекурсивный метод.
        return result; // Возвращает список сгенерированных последовательностей.
    }

    private static void binaryHelper(String current, int n, List<String> result) {
        // Рекурсивная функция для генерации бинарных последовательностей.
        if (current.length() == n) {
            // Если длина текущей строки равна n, добавляет ее в результат.
            result.add(current);
            return;
        }
        if (current.endsWith("0")) {
            // Если строка заканчивается на 0, добавляет только 1.
            binaryHelper(current + "1", n, result);
        } else {
            // Иначе добавляет и 0, и 1.
            binaryHelper(current + "0", n, result);
            binaryHelper(current + "1", n, result);
        }
    }


    // Задание 4
    public static String sequentialAlphabetic(String str) {
        // Находит самую длинную подстроку, в которой буквы идут в алфавитном порядке.
        String longest = "", current = str.substring(0, 1);
        // 'longest' хранит самую длинную последовательность, 'current' - текущую последовательность. Начинаем с первого символа строки.

        for (int i = 1; i < str.length(); i++) {
            // Итерация по строке, начиная со второго символа.
            if (Math.abs(str.charAt(i) - str.charAt(i - 1)) == 1) {
                // Проверяет, следует ли текущий символ непосредственно за предыдущим в алфавите.
                current += str.charAt(i);
                // Если да, добавляет символ к текущей последовательности.
            } else {
                longest = current.length() > longest.length() ? current : longest;
                // Если текущая последовательность длиннее 'longest', обновляет 'longest'.
                current = String.valueOf(str.charAt(i));
                // Начинает новую текущую последовательность с текущего символа.
            }
        }

        return current.length() > longest.length() ? current : longest;
        // Возвращает самую длинную алфавитную последовательность в строке.
    }


// Задание 5

    public static String compressThenSort(String str) {
        // Функция сжимает строку, группируя повторяющиеся символы, и сортирует эти группы по длине.
        StringBuilder result = new StringBuilder(); // Используется для построения итоговой строки.
        ArrayList<String> segments = new ArrayList<>(); // Список для хранения сжатых групп символов.

        int count = 1; // Счетчик повторений текущего символа.
        for (int i = 1; i <= str.length(); i++) {
            // Перебор символов строки.
            if (i == str.length() || str.charAt(i) != str.charAt(i - 1)) {
                // Если достигнут конец строки или символ отличается от предыдущего.
                segments.add(str.charAt(i - 1) + String.valueOf(count)); // Добавление сжатого сегмента в список.
                count = 1; // Сброс счетчика для следующего символа.
            } else {
                count++; // Увеличение счетчика при повторении символа.
            }
        }

        Collections.sort(segments, Comparator.comparingInt(String::length)); // Сортировка сегментов по длине.
        for (String segment : segments) {
            result.append(segment); // Добавление каждого сегмента в итоговую строку.
        }

        return result.toString(); // Преобразование StringBuilder в строку и возврат результата.
    }


    // Задание 6 ⦁
    public static int textToNumber(String str) {
        // Преобразует числительное, записанное словами, в числовое значение
        String[] words = str.split(" ");
        // Разбивает входную строку на отдельные слова
        int result = 0, current = 0;
        // 'result' хранит итоговое значение, 'current' - промежуточное значение числа

        HashMap<String, Integer> map = new HashMap<>();
        // Создает словарь для сопоставления слов с их числовыми значениями
        String[] keys = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
                "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety", "hundred", "thousand"};
        // Массив слов, представляющих числа
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60, 70, 80, 90, 100, 1000};
        // Массив числовых значений для соответствующих слов

        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        // Наполняет словарь парами "слово-число"

        for (String word : words) {
            // Перебирает все слова в входной строке
            int num = map.get(word);
            // Получает числовое значение для каждого слова
            if (num >= 100) {
                // Если слово представляет собой 'hundred' или 'thousand'
                current = (current == 0 ? 1 : current) * num;
                // Умножает 'current' на 100 или 1000, или присваивает ему эти значения, если 'current' равен 0
                if (num == 1000) {
                    result += current;
                    // Добавляет значение 'current' к итоговому результату при 'thousand'
                    current = 0;
                    // Обнуляет 'current' после тысячи
                }
            } else {
                current += num;
                // Добавляет числовое значение слова к 'current' для чисел меньше 100
            }
        }
        return result + current;
        // Возвращает итоговую сумму
    }


    // Задание 7
    public static String uniqueSubsequence(String str) {
        // Находит самую длинную подстроку без повторяющихся символов
        int start = 0, end = 0, maxLength = 0;
        // 'start' и 'end' определяют текущие границы подстроки, 'maxLength' хранит длину самой длинной подстроки
        String longestSubstring = "";
        // Строка для хранения самой длинной уникальной подстроки
        int[] charIndex = new int[256];
        // Массив для отслеживания последнего индекса каждого символа

        while (end < str.length()) {
            // Цикл продолжается, пока 'end' не достигнет конца строки
            if (charIndex[str.charAt(end)] > start) {
                // Если текущий символ уже встречался и его последний индекс больше 'start'
                start = charIndex[str.charAt(end)];
                // Обновление 'start' до индекса после предыдущего вхождения этого символа
            }
            if (end - start > maxLength) {
                // Если текущая подстрока длиннее предыдущей найденной самой длинной подстроки
                maxLength = end - start;
                // Обновление 'maxLength' длиной текущей подстроки
                longestSubstring = str.substring(start, end + 1);
                // Обновление 'longestSubstring' текущей подстрокой
            }
            charIndex[str.charAt(end)] = end + 1;
            // Обновление индекса последнего вхождения текущего символа
            end++;
            // Перемещение 'end' к следующему символу
        }
        return longestSubstring;
        // Возврат самой длинной уникальной подстроки
    }


// Задание 8

    public static int minimumPathSum(int[][] matrix) {
        // Находит путь с минимальной суммой чисел в матрице, двигаясь только вниз или вправо
        int rows = matrix.length; // Получение количества строк в матрице
        int cols = matrix[0].length; // Получение количества столбцов в матрице
        int[][] dp = new int[rows][cols]; // Создание вспомогательной матрицы для динамического программирования

        dp[0][0] = matrix[0][0]; // Инициализация первой ячейки матрицы dp

        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0]; // Заполнение первого столбца матрицы dp
        }
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j]; // Заполнение первой строки матрицы dp
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
                // Вычисление минимальной суммы для каждой ячейки, основываясь на предыдущих значениях
            }
        }

        return dp[rows - 1][cols - 1]; // Возвращение значения из нижнего правого угла матрицы dp, представляющего минимальный путь
    }


// Задание 9

    public static String orderByNumber(String str) {
        // Сортирует слова в строке на основе чисел, содержащихся в словах
        String[] words = str.split(" ");
        // Разбивает входную строку на слова
        TreeMap<Integer, String> sortedWords = new TreeMap<>();
        // Использует TreeMap для автоматической сортировки слов по числовому ключу
        Pattern pattern = Pattern.compile("\\d+");
        // Создает шаблон для поиска чисел в строке

        for (String word : words) {
            // Перебор всех слов во входной строке
            Matcher matcher = pattern.matcher(word);
            // Создает объект Matcher для поиска чисел в каждом слове
            if (matcher.find()) {
                // Если в слове найдено число
                int number = Integer.parseInt(matcher.group());
                // Преобразует найденное число из строки в целое число
                sortedWords.put(number, word.replaceAll("\\d+", ""));
                // Добавляет слово в TreeMap, удаляя из него числа
            }
        }

        StringBuilder result = new StringBuilder();
        // Использует StringBuilder для сборки отсортированной строки
        for (String word : sortedWords.values()) {
            // Перебирает слова в TreeMap, которые уже отсортированы по числу
            result.append(" ").append(word);
            // Добавляет каждое слово в итоговую строку, разделяя их пробелами
        }

        return result.toString();
        // Преобразует StringBuilder в строку и возвращает ее
    }


// Задание 10

    public static int exchangeDigits(int num1, int num2) {
        // Заменяет цифры во втором числе на наибольшие цифры из первого числа
        char[] digits1 = Integer.toString(num1).toCharArray();
        // Преобразует первое число в массив символов (цифр)
        char[] digits2 = Integer.toString(num2).toCharArray();
        // Преобразует второе число в массив символов (цифр)

        Arrays.sort(digits1);
        // Сортирует цифры первого числа в порядке возрастания
        int index1 = digits1.length - 1;
        // Устанавливает индекс на последний символ в отсортированном массиве первого числа

        for (int index2 = 0; index2 < digits2.length; index2++) {
            // Перебирает цифры второго числа
            while (index1 >= 0 && digits1[index1] <= digits2[index2]) {
                // Пока не найдена цифра в первом числе, которая больше текущей цифры второго числа
                index1--;
                // Перемещается назад по массиву первого числа
            }

            if (index1 >= 0) {
                // Если найдена подходящая цифра для замены
                digits2[index2] = digits1[index1];
                // Заменяет цифру во втором числе на найденную цифру
                index1--;
                // Перемещает индекс для следующей итерации
            }
        }

        return Integer.parseInt(new String(digits2));
        // Преобразует измененный массив цифр обратно в число и возвращает его
    }

}
