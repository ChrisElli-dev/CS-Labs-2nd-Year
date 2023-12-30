import java.util.regex.*;

public class IP_Validator {
    public static void main(String[] args) {
        String ip = "132.255.126.1";//это строка, представляющая IP-адрес, который мы проверяем

        Pattern pattern = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        //компилирует регулярное выражение, которое проверяет корректность IP-адреса

        Matcher matcher = pattern.matcher(ip);
        //создает объект Matcher, который будет искать совпадения в IP-адресе с использованием регулярного выражения.

        if (matcher.matches()) {
            System.out.println("This IP Address is Valid!");
        } else {
            System.out.println("This I{ Address is Invalid!");
        }//проверяет, соответствует ли IP-адрес критериям, заданным в регулярном выражении
    }
}