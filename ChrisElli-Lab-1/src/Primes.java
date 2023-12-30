/*Просто́е число́ — натуральное число,
 имеющее ровно два различных натуральных
 делителя. Другими словами, натуральное число p
  является простым, если оно отлично от 1
  и делится без остатка только на 1 и на само p.
 */
public class Primes {
    public static void main(String[] args) {
        for (int i = 2; i < 100; i++) { //перебираем числа от 2 до 100
        if (IsPrime(i))//выводим простые
        {System.out.println(i + " - простое число");}
        }
    }
    public static boolean IsPrime(int n) { // проверка простое ли число
        for (int i = 2; i < n; i++) {
            if (n % i == 0) { // деление без остатка
                return false;
            }
        }   return true;
    }
}