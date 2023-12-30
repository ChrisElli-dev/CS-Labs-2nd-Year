public class LinkReplacer {
    public static void main(String[] args) {
        String text = "Visit our website at http://www.example.com for more information.";
        //строка, в которой мы заменяем ссылки
        String changedText = text.replaceAll("(http[s]?)://[\\w./]+", "<a href=\"$0\">$0</a>");
        //использует метод для замены всех найденных ссылок в тексте на гиперссылки выражение (http[s]?)://[\\w./]+ ищет ссылки в тексте
        System.out.println(changedText);
    }
}