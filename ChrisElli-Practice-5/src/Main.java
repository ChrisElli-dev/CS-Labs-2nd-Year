import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Locale;

public class Main {
    private static final Map<String, String> timeZones = new HashMap<>();

    static {
        timeZones.put("Los Angeles", "-08:00");
        timeZones.put("New York", "-05:00");
        timeZones.put("Caracas", "-04:30");
        timeZones.put("Buenos Aires", "-03:00");
        timeZones.put("London", "Z");
        timeZones.put("Rome", "+01:00");
        timeZones.put("Moscow", "+03:00");
        timeZones.put("Tehran", "+03:30");
        timeZones.put("New Delhi", "+05:30");
        timeZones.put("Beijing", "+08:00");
        timeZones.put("Canberra", "+10:00");
    }

    public static void main(String[] args) {
        System.out.println(sameLetterPattern("ABAB", "CDCD")); // ➞ true
        System.out.println(sameLetterPattern("ABCBA", "BCDCB")); // ➞ true
        System.out.println(sameLetterPattern("FFGG", "CDCD")); // ➞ false
        System.out.println(sameLetterPattern("FFFF", "ABCD")); // ➞ false
        System.out.println("---");

        System.out.println(spiderVsFly("H3", "E2")); // ➞ "H3-H2-H1-A0-E1-E2"
        System.out.println(spiderVsFly("A4", "B2")); // ➞ "A4-A3-A2-B2"
        System.out.println(spiderVsFly("A4", "C2")); // ➞ "A4-A3-A2-B2-C2"
        System.out.println("---");

        System.out.println(digitsCount(4666)); // ➞ 4
        System.out.println(digitsCount(544)); // ➞ 3
        System.out.println(digitsCount(121317)); // ➞ 6
        System.out.println(digitsCount(0)); // ➞ 1
        System.out.println(digitsCount(12345)); // ➞ 5
        System.out.println(digitsCount(1289396387328L)); // ➞ 13
        System.out.println("---");

        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster")); // ➞ 2
        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant")); // ➞ 108
        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed")); // ➞ 13
        System.out.println("---");

        System.out.println(sumsUp(new int[]{1, 2, 3, 4, 5})); // ➞ [[3, 5]]
        System.out.println(sumsUp(new int[]{1, 2, 3, 7, 9})); // ➞ [[1, 7]]
        System.out.println(sumsUp(new int[]{10, 9, 7, 2, 8})); // ➞ []
        System.out.println(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7})); // ➞ [[2, 6], [3, 5], [1, 7]]
        System.out.println("---");

        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"})); // ➞ "54%"
        System.out.println(takeDownAverage(new String[]{"10%"})); // ➞ "0%"
        System.out.println(takeDownAverage(new String[]{"53%", "79%"})); // ➞ "51%"
        System.out.println("---");

        System.out.println(caesarCipher("encode", "hello world", 3)); // ➞ "KHOOR ZRUOG"
        System.out.println(caesarCipher("decode", "EPQSWX PEWX XEWO!", 4)); // ➞ "ALMOST LAST TASK!"
        System.out.println("---");

        System.out.println(setSetup(5, 3)); // ➞ 60
        System.out.println(setSetup(7, 3)); // ➞ 210
        System.out.println("---");

        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra")); // ➞ "2011-4-2 17:23"
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome")); // ➞ "1983-8-1 00:01"
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing")); // ➞ "1971-1-1 02:40"
        System.out.println("---");

        System.out.println(isNew(3)); // ➞ true
        System.out.println(isNew(30)); // ➞ true
        System.out.println(isNew(321)); // ➞ false
        System.out.println(isNew(123)); // ➞ true
        System.out.println("---");

    }

    // Задание 1 Создайте функцию, которая возвращает true, если две строки имеют один и тот же буквенный шаблон, и false в противном случае.
    public static boolean sameLetterPattern(String str1, String str2) {
        // Проверка равенства длин входных строк. Если длины разные, шаблоны букв не могут быть одинаковыми.
        if (str1.length() != str2.length()) {
            return false;
        }

        // Создание карты для хранения соответствия между символами первой и второй строки.
        Map<Character, Character> patternMap = new HashMap<>();

        // Перебор всех символов в строках.
        for (int i = 0; i < str1.length(); i++) {
            // Получение текущих символов из обеих строк.
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);

            // Проверка, был ли символ ch1 из первой строки уже добавлен в карту.
            if (patternMap.containsKey(ch1)) {
                // Если да, проверяем, соответствует ли он тому же символу во второй строке.
                if (patternMap.get(ch1) != ch2) {
                    // Если символы не соответствуют, возвращаем false, так как шаблон нарушен.
                    return false;
                }
            } else {
                // Если символ ch1 не был добавлен в карту:
                if (patternMap.containsValue(ch2)) {
                    // Проверяем, был ли символ ch2 уже сопоставлен с другим символом из первой строки.
                    return false; // Если да, возвращаем false, так как один символ второй строки соответствует разным символам первой строки.
                }
                // Сопоставляем символ ch1 из первой строки с символом ch2 из второй строки.
                patternMap.put(ch1, ch2);
            }
        }
        // Возвращаем true, если все символы соответствуют шаблону.
        return true;
    }

// Задание 2 Паутина определяется кольцами, пронумерованными от 0 до 4 от центра, и радиалами, помеченными по часовой стрелке сверху как A-H.
//Создайте функцию, которая принимает координаты паука и мухи и
// возвращает кратчайший путь для паука, чтобы добраться до мухи.

    private static final String RADIALS = "ABCDEFGH"; // Статическая константа, содержащая радиалы паутины, обозначенные буквами от A до H

    public static String spiderVsFly(String spider, String fly) {
        // Получение индекса радиала для паука и мухи. Это индекс буквы в строке RADIALS, соответствующий первому символу их координат.
        int spiderRadial = RADIALS.indexOf(spider.charAt(0));
        int spiderRing = Character.getNumericValue(spider.charAt(1)); // Получение номера кольца для паука
        int flyRadial = RADIALS.indexOf(fly.charAt(0));
        int flyRing = Character.getNumericValue(fly.charAt(1)); // Получение номера кольца для мухи

        StringBuilder path = new StringBuilder(); // Создание объекта StringBuilder для построения пути паука
        path.append(spider); // Добавление начальной позиции паука в путь

        // Вычисление минимального углового расстояния между радиалами паука и мухи
        int angleDistance = Math.min(Math.abs(spiderRadial - flyRadial), RADIALS.length() - Math.abs(spiderRadial - flyRadial));

        // Проверка условий для перемещения через центр или прямого перемещения
        if (angleDistance >= 3 || spiderRing == 0 || flyRing == 0) {
            // Перемещение паука к центру, если угловое расстояние больше или равно 3 или одно из колец равно 0
            for (int i = spiderRing - 1; i > 0; i--) {
                path.append("-").append(RADIALS.charAt(spiderRadial)).append(i); // Движение к центру по текущему радиалу
            }
            path.append("-A0"); // Перемещение в центральную точку паутины
            // Перемещение от центра к мухе
            for (int i = 1; i <= flyRing; i++) {
                path.append("-").append(RADIALS.charAt(flyRadial)).append(i); // Движение от центра к мухе по её радиалу
            }
        } else {
            // Обработка ситуации, когда паук и муха находятся на одном радиале
            if (spiderRadial == flyRadial) {
                for (int i = spiderRing - 1; i >= flyRing; i--) {
                    path.append("-").append(RADIALS.charAt(spiderRadial)).append(i); // Движение вдоль радиала
                }
            } else {
                // Обработка ситуации, когда радиалы разные
                // Определение направления перемещения по кольцам
                int ringChange = spiderRing > flyRing ? -1 : 1;
                for (int ring = spiderRing; ring != flyRing; ring += ringChange) {
                    path.append("-").append(RADIALS.charAt(spiderRadial)).append(ring + ringChange); // Движение к нужному кольцу
                }
                // Определение направления перемещения по радиалам
                int radialChange = spiderRadial < flyRadial ? 1 : -1;
                if (Math.abs(spiderRadial - flyRadial) > RADIALS.length() / 2) {
                    radialChange = -radialChange; // Изменение направления, если это короче
                }
                // Перемещение по радиалам к нужному радиалу мухи
                for (int radial = spiderRadial; radial != flyRadial; radial = (radial + radialChange + RADIALS.length()) % RADIALS.length()) {
                    path.append("-").append(RADIALS.charAt((radial + radialChange + RADIALS.length()) % RADIALS.length())).append(flyRing);
                }
            }
        }
        return path.toString(); // Возвращение пути в виде строки
    }


    // Задание 3 ⦁	Создайте функцию, которая будет рекурсивно подсчитывать количество цифр числа.
// Преобразование числа в строку не допускается, поэтому подход является рекурсивным
    public static int digitsCount(long n) {
        // Проверка, является ли текущее значение 'n' однозначным числом.
        if (n >= 0 && n <= 9) {
            // Если число однозначное, возвращаем 1, так как у однозначного числа всегда одна цифра.
            return 1;
        }

        // Рекурсивный вызов функции с уменьшением числа в 10 раз.
        // Это делает число короче на одну цифру на каждом шаге.
        return 1 + digitsCount(n / 10);
    }

    // Задание 4 ⦁	Игроки пытаются набрать очки, формируя слова, используя буквы из 6-буквенного скремблированного слова.
// Они выигрывают раунд, если им удается успешно расшифровать слово из 6 букв.
    public static int totalPoints(String[] guesses, String word) {
        int totalPoints = 0; // Инициализация переменной для подсчета общего количества очков

        // Перебор всех предположений, предоставленных игроком
        for (String guess : guesses) {
            // Проверка, допустимо ли предположение (слово) с учетом заданного слова 'word'
            if (isValidWord(guess, word)) {
                // Если предположение допустимо, добавляем к общему количеству очков очки за это предположение
                totalPoints += getWordPoints(guess, word);
            }
        }

        return totalPoints; // Возвращаем общее количество набранных очков
    }


    // Функция проверяет, является ли предположение (guess) допустимым словом на основе заданного слова (word)
    private static boolean isValidWord(String guess, String word) {
        Map<Character, Integer> letterCounts = new HashMap<>(); // Словарь для подсчета количества каждой буквы в слове 'word'

        // Заполняем словарь, подсчитывая количество каждого символа в слове 'word'
        for (char c : word.toCharArray()) {
            letterCounts.put(c, letterCounts.getOrDefault(c, 0) + 1);
        }

        // Перебираем каждую букву из предположения 'guess'
        for (char c : guess.toCharArray()) {
            // Проверяем, есть ли буква в словаре и достаточно ли их для формирования слова
            if (!letterCounts.containsKey(c) || letterCounts.get(c) == 0) {
                return false; // Если буквы нет в словаре или их количество не достаточно, возвращаем false
            }
            // Уменьшаем количество доступных букв в словаре
            letterCounts.put(c, letterCounts.get(c) - 1);
        }
        return true; // Возвращаем true, если слово можно сформировать
    }

    // Функция для подсчета очков за каждое предположение
    private static int getWordPoints(String guess, String word) {
        // Очки начисляются в зависимости от длины предположения
        if (guess.length() == 3) {
            return 1; // 3 буквы = 1 очко
        } else if (guess.length() == 4) {
            return 2; // 4 буквы = 2 очка
        } else if (guess.length() == 5) {
            return 3; // 5 букв = 3 очка
        } else if (guess.length() == 6) {
            // Для слов из 6 букв
            if (guess.equals(word)) {
                return 54; // Если слово совпадает с заданным словом, начисляем 54 очка
            }
            // Проверка на анаграммы
            Map<Character, Integer> guessCounts = new HashMap<>();
            Map<Character, Integer> wordCounts = new HashMap<>();
            // Подсчет количества каждого символа в предположении и в заданном слове
            for (char c : guess.toCharArray()) {
                guessCounts.put(c, guessCounts.getOrDefault(c, 0) + 1);
            }
            for (char c : word.toCharArray()) {
                wordCounts.put(c, wordCounts.getOrDefault(c, 0) + 1);
            }
            // Если количество символов совпадает, это анаграмма
            if (guessCounts.equals(wordCounts)) {
                return 54; // За анаграмму также начисляем 54 очка
            }
        }
        return 0; // Возвращаем 0 очков, если предположение не соответствует ни одному из условий
    }


    // Задание 5 Создайте функцию, которая получает каждую пару чисел из массива,
// который суммирует до восьми, и возвращает его как массив пар (отсортированный по возрастанию).
    public static List<List<Integer>> sumsUp(int[] arr) {//ищет пары чисел в массиве
        Arrays.sort(arr); // Сортировка массива для упрощения поиска пар

        List<List<Integer>> pairs = new ArrayList<>(); // Создание списка для хранения пар чисел
        int left = 0; // Индекс для начала массива
        int right = arr.length - 1; // Индекс для конца массива

        // Цикл, который продолжается, пока индексы не встретятся или не перекрестятся
        while (left < right) {
            int sum = arr[left] + arr[right]; // Вычисление суммы чисел на текущих позициях

            // Проверка, равна ли сумма 8
            if (sum == 8) {
                // Добавление пары чисел в список, если их сумма равна 8
                pairs.add(Arrays.asList(arr[left], arr[right]));
                // Перемещение обоих индексов к центру массива
                left++;
                right--;
            } else if (sum < 8) {
                // Если сумма меньше 8, перемещаем левый индекс вправо
                left++;
            } else {
                // Если сумма больше 8, перемещаем правый индекс влево
                right--;
            }
        }

        return pairs; // Возврат списка всех найденных пар
    }


    // Задание 6 Какой процент вы можете набрать на тесте, который в одиночку снижает средний балл по классу на 5%?
// Учитывая массив оценок ваших одноклассников, создайте функцию, которая возвращает ответ.
// Округлите до ближайшего процента.
    public static String takeDownAverage(String[] scores) {//принимает массив строковых оценок scores и возвращает строку
        // Вычисление текущего среднего процента класса
        double currentAverage = Arrays.stream(scores) // Создание потока из массива строк процентов
                .mapToInt(score -> Integer.parseInt(score.replace("%", ""))) // Удаление символов '%' и преобразование строк в целые числа
                .average() // Вычисление среднего значения
                .orElse(0); // Возврат 0, если массив пуст

        double newAverage = currentAverage - 5; // Вычитание 5 из текущего среднего для получения нового среднего

        int n = scores.length; // Количество оценок в классе
        // Расчет необходимого процента, который должен получить ученик, чтобы снизить средний балл на 5%
        double requiredScore = newAverage * (n + 1) - currentAverage * n;

        requiredScore = Math.max(0, requiredScore); // Гарантируем, что оценка не будет отрицательной
        return String.format("%d%%", Math.round(requiredScore)); // Возвращаем необходимый процент в формате строки с символом '%'
    }


    // Задание 7 Создайте функцию, которая будет шифровать и дешифровать сообщения с использованием шифра Цезаря. Шифр Цезаря – это метод шифрования,
// в котором каждая буква в сообщении сдвигается на фиксированное количество позиций в алфавите.
    public static String caesarCipher(String mode, String message, int shift) {
        StringBuilder result = new StringBuilder(); // Используем StringBuilder для построения результата

        // Если режим - декодирование, меняем сдвиг на обратный
        if (mode.equals("decode")) {
            shift = 26 - (shift % 26); // Обратный сдвиг для декодирования
        }

        // Перебор каждого символа в сообщении
        for (char character : message.toCharArray()) {
            // Проверяем, является ли символ большой буквой
            if (character >= 'A' && character <= 'Z') {
                // Вычисляем новую позицию символа после сдвига и обрабатываем циклический перенос через 'Z'
                char shiftedChar = (char) ((character - 'A' + shift) % 26 + 'A');
                result.append(shiftedChar); // Добавляем преобразованный символ в результат
            }
            // Проверяем, является ли символ маленькой буквой
            else if (character >= 'a' && character <= 'z') {
                // Аналогичное преобразование для маленьких букв
                char shiftedChar = (char) (((character - 'a') + shift) % 26 + 'A');
                result.append(shiftedChar); // Добавляем преобразованный символ в результат
            }
            // Если символ не является буквой, добавляем его без изменений
            else {
                result.append(character);
            }
        }

        return result.toString(); // Преобразуем StringBuilder в строку и возвращаем результат
    }


    // Задание 8 Создайте метод для рекурсивного вычисления количества различных способов
// как можно разместить k элементов из множества из n элементов без повторений
    // Функция для вычисления количества различных способов, которыми можно выбрать k элементов из n элементов
    public static int setSetup(int n, int k) {
        // Вычисление количества комбинаций с использованием формулы комбинаторики: n! / (n-k)!
        return factorial(n) / factorial(n - k);
    }

    // Вспомогательная рекурсивная функция для вычисления факториала числа n
    private static int factorial(int n) {
        // Базовый случай рекурсии: факториал 0 или 1 равен 1
        if (n == 0 || n == 1) {
            return 1;
        }
        // Рекурсивный шаг: факториал n равен n, умноженному на факториал (n-1)
        return n * factorial(n - 1); // n! = n * (n-1)!
    }


    // Задание 9 В этой задаче цель состоит в том, чтобы вычислить, сколько времени сейчас в двух разных городах.
// Вы должны вернуть новую метку времени с датой и соответствующим временем
    public static String timeDifference(String cityA, String timestamp, String cityB) {
        // Создание форматтера для преобразования входной строки времени в объект LocalDateTime
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.ENGLISH);

        // Преобразование входной строки времени в объект LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, inputFormatter);

        // Преобразование LocalDateTime в ZonedDateTime для города A с учетом его часового пояса
        ZonedDateTime zonedDateTimeA = ZonedDateTime.of(localDateTime, ZoneOffset.of(timeZones.get(cityA)));

        // Преобразование времени из часового пояса города A в часовой пояс города B
        ZonedDateTime zonedDateTimeB = zonedDateTimeA.withZoneSameInstant(ZoneOffset.of(timeZones.get(cityB)));

        // Создание форматтера для вывода преобразованного времени
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");

        // Возвращение преобразованного времени в виде строки
        return zonedDateTimeB.format(outputFormatter);
    }


    // Задание 10 Напишите функцию, которая принимает неотрицательное целое число и возвращает true, если целое число является новым числом, и false, если это не так.
    public static boolean isNew(int number) {
        // Проверка, является ли число однозначным (меньше 10)
        if (number < 10) {
            return true; // Все однозначные числа считаются "новыми"
        }

        // Преобразование числа в строку, а затем в массив символов
        String numberStr = Integer.toString(number);
        char[] numberChars = numberStr.toCharArray();
        Arrays.sort(numberChars); // Сортировка символов числа в порядке возрастания

        // Перебор всех чисел меньше данного числа
        for (int i = 1; i < number; i++) {
            // Преобразование меньшего числа в строку и массив символов
            String smallerNumberStr = Integer.toString(i);
            char[] smallerNumberChars = smallerNumberStr.toCharArray();
            Arrays.sort(smallerNumberChars); // Сортировка символов меньшего числа

            // Проверка, совпадает ли отсортированный массив символов меньшего числа с массивом исходного числа
            if (Arrays.equals(numberChars, smallerNumberChars)) {
                return false; // Если совпадает, число не является "новым"
            }
        }

        return true; // Если не найдено совпадений, число является "новым"
    }
}
