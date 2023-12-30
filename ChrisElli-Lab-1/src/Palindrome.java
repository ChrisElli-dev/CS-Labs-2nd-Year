public class Palindrome {
    public static void main(String[] args) {
        for (String s : args) {
            if (isPalindrome(s)) {
                System.out.println(s + " is a palindrome.");
            } else {
                System.out.println(s + " is not a palindrome.");
            }
        }
    }

    // Метод для переворачивания строки
    public static String reverseString(String s) {
        StringBuilder reversed = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--)//length() возвращает длину строки
        {reversed.append(s.charAt(i));//charAt(int index)возвращает символ по указанному индексу.

        }
        return reversed.toString();
    }
    // проверкка, является ли слово палиндромом
    public static boolean isPalindrome(String s) {
        String reversed = reverseString(s);
        return s.equals(reversed); //equals(Object)проверка значения равенства.
    }
}