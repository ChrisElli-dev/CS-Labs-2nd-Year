import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class NumberFinder {
    public static void main(String[] args) {
        String text = "The cost of the Xbox is gonna be around $999.99 and for Iphone $1499";
        //компилирует регулярное выражение, которое ищет числа с десятичной точкой
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        //создает объект, который будет искать совпадения в тексте с использованием регулярного выражения.
        Matcher matcher = pattern.matcher(text);

        List<String> numbers = new ArrayList<>();
        //запускает цикл, который выполняется до тех пор, пока в тексте найдутся числа
        while (matcher.find()) {
            numbers.add(matcher.group());
        }

        System.out.println("Spotted numbers: " + numbers);
    }
}
