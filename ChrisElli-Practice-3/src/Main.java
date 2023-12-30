import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Task 1
        String example = "United States of America";
        String result = replaceVowels(example);
        System.out.println(result);

        String anotherExample = "I'm Christopher and I'm from New York City, United States";
        String anotherResult = replaceVowels(anotherExample);
        System.out.println(anotherResult);
        System.out.println("-----------------");

        // Task 2
        String example_2 = "hello";
        String result_2 = stringTransform(example_2);
        System.out.println(result_2);

        String anotherExample_2 = "bookkeeper";
        String anotherResult_2 = stringTransform(anotherExample_2);
        System.out.println(anotherResult_2);
        System.out.println("-----------------");

        // Task 3
        System.out.println(doesBlockFit(1, 3, 5, 4, 5)); // true
        System.out.println(doesBlockFit(1, 8, 1, 1, 1)); // true
        System.out.println(doesBlockFit(1, 2, 2, 1, 1)); // false
        System.out.println("-----------------");

        // Task 4
        System.out.println(numCheck(243)); // true
        System.out.println(numCheck(52));  // false
        System.out.println("-----------------");

        // Task 5
        int[] equation1 = {1, -3, 2};
        int[] equation2 = {2, 5, 2};
        int[] equation3 = {1, -6, 9};

        System.out.println(countRoots(equation1)); // ➞ 2
        System.out.println(countRoots(equation2)); // ➞ 1
        System.out.println(countRoots(equation3)); // ➞ 1
        System.out.println("-----------------");

        // Task 6
        System.out.println(Arrays.toString(salesData(new String[][]{
                {"Fridge", "Shop2", "Shop3"},
                {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Laptop", "Shop3", "Shop4"},
                {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}})) + "\n");
        System.out.println("-----------------");

        // Task 7
        System.out.println(validSplit("apple eagle egg goat")); // true
        System.out.println(validSplit("cat dog goose fish"));   // false
        System.out.println("-----------------");

        // Task 8
        int[] arr1 = {3, 1, 4, 2, 7, 5};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {1, 2, -6, 10, 3};

        System.out.println(waveForm(arr1)); // ➞ true
        System.out.println(waveForm(arr2)); // ➞ false
        System.out.println(waveForm(arr3)); // ➞ true
        System.out.println("-----------------");

        // Task 9
        System.out.println(commonVowel("Hello world")); // 'o'
        System.out.println(commonVowel("paaii   ") + "\n"); // 'a'

        // Task 10
        System.out.println(Arrays.deepToString(dataScience(new int[][]{
                {6, 4, 19, 0, 0},
                {81, 25, 3, 1, 17},
                {48, 12, 60, 32, 14},
                {91, 47, 16, 65, 217},
                {5, 73, 0, 4, 21}})).replace("], [", "],\n ["));
    }

    // Task 1
    public static String replaceVowels(String input) {
        // Заменяем все гласные буквы (строчные и прописные) на звёздочки
        return input.replaceAll("[aeiouAEIOU]", "*");
    }


    // Task 2

    public static String stringTransform(String input) {
        // Заменяем каждую пару повторяющихся символов на "Double" и этот символ
        return input.replaceAll("(.)\\1", "Double$1");
    }


    // Task 3
    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        // Проверяем, можно ли поместить блок в отверстие, проверяя все возможные ориентации блока
        boolean fitsInWidth = (a <= w) && (b <= h) || (b <= w) && (a <= h);
        boolean fitsInHeight = (a <= h) && (c <= w) || (c <= h) && (a <= w);
        boolean fitsInDepth = (b <= w) && (c <= h) || (c <= w) && (b <= h);

        // Возвращаем true, если блок подходит хотя бы одним способом
        return fitsInWidth || fitsInHeight || fitsInDepth;
    }


    // Task 4
    public static boolean numCheck(int num) {
        // Считаем сумму квадратов цифр числа
        int sumOfSquares = 0;
        int tempNum = num;

        while (tempNum != 0) {
            int digit = tempNum % 10;
            sumOfSquares += digit * digit;
            tempNum /= 10;
        }

        // Проверяем, совпадает ли чётность числа с чётностью суммы квадратов его цифр
        return num % 2 == sumOfSquares % 2;
    }


    // Task 5
    public static int countRoots(int[] coefficient) {
        // Вычисляем дискриминант квадратного уравнения
        int a = coefficient[0];
        int b = coefficient[1];
        int c = coefficient[2];

        int d = b * b - 4 * a * c;
        int RootCount = 0;

        // Подсчитываем корни, если дискриминант неотрицательный
        if (d >= 0) {
            double x1 = (-b + Math.sqrt(d)) / (2.0 * a);
            double x2  = (-b - Math.sqrt(d)) / (2.0 * a);

            RootCount += (int)x1 == x1 ? 1 : 0;
            if (d > 0) {
                RootCount += (int)x2 == x2 ? 1 : 0;
            }
        }

        // Возвращаем количество целочисленных корней
        return RootCount;
    }

    // Task 6
    public static String[] salesData(String[][] data) {
        // Находим максимальную длину строки в двумерном массиве
        int maxLen = 0;
        for (String[] row : data) {
            if (row.length > maxLen) {
                maxLen = row.length;
            }
        }
        // Выбираем строки с максимальной длиной
        int count = 0;
        String[] result = new String[data.length];
        for (String[] row : data) {
            if (row.length == maxLen) {
                result[count] = row[0];
                count++;
            }
        }
        // Возвращаем массив с первыми элементами этих строк
        return Arrays.copyOf(result, count);
    }

    // Task 7
    public static boolean validSplit(String sentence) {
        // Разделяем предложение на слова
        String[] words = sentence.split(" ");
        // Если в предложении меньше двух слов, возвращаем false
        if (words.length <= 1) {
            return false;
        }

        // Проверяем, заканчивается ли каждое слово на ту же букву, на которую начинается следующее
        for (int i = 1; i < words.length; i++) {
            if (words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
                return false;
            }
        }

        // Возвращаем true, если все проверки пройдены
        return true;
    }


    // Task 8
    public static boolean waveForm(int[] wave) {
        // Проверяем, достаточно ли элементов для волнообразного массива
        if (wave.length < 2) {
            return false;
        }

        // Определяем, возрастающий ли порядок у первых двух элементов
        boolean increasing = wave[1] > wave[0];
        for (int i = 2; i < wave.length; i++) {
            // Проверяем чередование возрастания и убывания
            if ((increasing && wave[i] >= wave[i - 1]) || (!increasing && wave[i] <= wave[i - 1])) {
                return false;
            }
            increasing = !increasing;
        }

        // Возвращаем true, если массив волнообразный
        return true;
    }


    // Task 9
    public static char commonVowel(String sentence) {
        // Инициализируем массив для подсчёта гласных
        String vowels = "aeiouAEiOU";
        int[] counts = new int[200];
        char commonVowel = ' ';

        // Подсчитываем частоту каждой гласной
        for (char c : sentence.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                if (++counts[c] > counts[commonVowel]) {
                    commonVowel = c;
                }
            }
        }

        // Возвращаем наиболее часто встречающую


        // Task 10
        public static int[][] dataScience(int[][] matrica) {
            // Работаем с квадратной матрицей
            int n = matrica.length;
            for (int i = 0; i < n; i++) {
                int summary = 0;
                // Суммируем элементы в столбце, исключая диагональный элемент
                for (int k = 0; k < n; k++) {
                    if (k != i) {
                        summary += matrica[k][i];
                    }
                }
                // Заменяем диагональный элемент на среднее значение остальных элементов в столбце
                matrica[i][i] = summary / (n - 1);
            }
            // Возвращаем изменённую матрицу
            return matrica;
        }
