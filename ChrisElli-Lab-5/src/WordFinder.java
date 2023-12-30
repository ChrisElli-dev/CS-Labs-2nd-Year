import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class WordFinder {
    public static void main(String[] args) {
        String text = "I'm from United States"; //строка, в которой мы ищем слова.
        char startingLetter = 'f'; //буква, с которой должны начинаться искомые слова

        Pattern pattern = Pattern.compile("\\b" + startingLetter + "\\w+"); // w - обозначает букву или цифру
        //компилирует регулярное выражение, которое ищет слова, начинающиеся с заданной буквы
        Matcher matcher = pattern.matcher(text);
        //создает объект Matcher, который будет искать совпадения в тексте с использованием регулярного выражения

        List<String> words = new ArrayList<>();
        while (matcher.find()) {
            words.add(matcher.group());
        }

        System.out.println("The word is starting with '" + startingLetter + "': " + words);
    }
}