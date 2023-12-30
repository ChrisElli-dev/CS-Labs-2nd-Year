public class Main {
    public static void main(String[] args) {

        System.out.println(convert(5)); // 18.925
        System.out.println(convert(3)); // 11.355
        System.out.println(convert(8)); // 30.28

        System.out.println(fitCalc(15, 1));  // 15
        System.out.println(fitCalc(24, 2));  // 48
        System.out.println(fitCalc(41, 3));  // 123

        System.out.println(containers(3, 4, 2));  // 460
        System.out.println(containers(5, 0, 2));  // 300
        System.out.println(containers(4, 1, 4));  // 530

        System.out.println(triangleType(5, 5, 5)); // equilateral
        System.out.println(triangleType(5, 4, 5)); // isosceles
        System.out.println(triangleType(3, 4, 5)); // different-sided
        System.out.println(triangleType(5, 1, 1)); // not a triangle

        System.out.println(ternaryEvaluation(8, 4));  // 8
        System.out.println(ternaryEvaluation(1, 11)); // 11
        System.out.println(ternaryEvaluation(5, 9));  // 9

        System.out.println(howManyItems(22, 1.4, 2));  // 3
        System.out.println(howManyItems(45, 1.8, 1.9)); // 6
        System.out.println(howManyItems(100, 2, 2));  // 12

        System.out.println(factorial(3));  // 6
        System.out.println(factorial(5));  // 120
        System.out.println(factorial(7));  // 5040

        System.out.println(gcd(48, 18));  // 6
        System.out.println(gcd(52, 8));   // 4
        System.out.println(gcd(259, 28)); // 7

        System.out.println(ticketSaler(70, 1500));  // 75600
        System.out.println(ticketSaler(24, 950));   // 16416
        System.out.println(ticketSaler(53, 1250));  // 47700

        System.out.println(tables(5, 2));  // 1
        System.out.println(tables(31, 20));  // 0
        System.out.println(tables(123, 58));  // 4
    }

/*
    Task №1 - Make a function that turns gallons into litters.
    The "convert" function takes an integer "x" as an argument and returns
    a floating point number
 */
    public static float convert(int x) {
        return x * 3.785f;
    }

/*
    Task №2 - Quasi-Fitness application. I have to create a function to count how
    many calories users have burned during their workout time. The function should use
    minutes and intensity of workouts 1-low intensity, 2-medium intensity, 3-high intensity
    and then count the amount of burned calories according to that information
 */

    public static int fitCalc(int minutes, int intensity) {
        return minutes * intensity;
    }

/*
    Task №3 - Warehouse Management. 3 types of containers:
    Boxes, each containing 20 items.
    Bags, each containing 50 items.
    Barrels, each containing 100 items.
    Create a function that returns total items in the warehouse, with different
    storage (box, barrel, bag) types in the storage
 */

    public static int containers(int boxes, int bags, int barrels) {
        return boxes * 20 + bags * 50 + barrels * 100;
    }


/*
    Task №4 - Create a function that has variables "X", "Y", "Z";
    "||" (Logical OR), "&&" (Logical AND).
 */

    public static String triangleType(int x, int y, int z) {

        if (x + y <= z || x + z <= y || y + z <= x) {
            return "not a triangle";
        } else if (x == y && y == z) {
            return "equilateral"; // равносторонний
        } else if (x == y || y == z || x == z) {
            return "isosceles"; // равнобедренный
        } else {
            return "different-sideAd"; // разносторонний
        }
    }

/* Task N5 - "Ternary Operator" - the shorten way to write a simple conditional expression
    (сокращённый способ записи простого условного выражения)
   Example:
    int x = 10;
    int y = 20;
    int result = (x > y) ? x : y;
    If x > y then the value returns y=20 because the condition is false

*/
    public static int ternaryEvaluation(int a, int b) {
        return a > b ? a : b;
    }

/*
    Task №6 - У меня есть ограниченное количество ткани определенной длины,
     и я хочу сшить как можно больше пододеяльников. Создайте функцию,
     которая будет принимать длину ткани (в метрах) и размер одной детали
     (ширина и длина в метрах), а затем возвращать количество пододеяльников,
      которые я смогу сшить, прежде чем кончится рулон.

    n * 2 – это количество квадратных метров имеющейся ткани,
    w и h – это длина и ширина одной детали
 */

    public static int howManyItems(double n, double w, double h) {
        double fabricForOneItem = w * h * 2;
        int maxFabric = (int) (n / fabricForOneItem);
        return maxFabric;
    }

// Task №7 - Create a function that counts factorial of the given number

    public static int factorial(int n) {
        if (n <= 1) { // Если n равно 0 или 1, то функция возвращает 1, потому что факториал 0 и 1 равен 1.
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

// Task №8 - Создайте функцию, которая находит наибольший общий делитель двух чисел

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

// Task №9 - Create a function that takes the number of concert tickets sold through a web service
// and the cost per ticket, taking into account a fixed commission.
// The function should return the total revenue from ticket sales.

    public static int ticketSaler(int tickets, int cost) {
        double commission = 0.72;
        return (int) (tickets * cost * commission);
    }

/*
    Task №10 - Create a function that takes an integer number of students
    and the number of desks in the classroom. The function must determine
    how many desks are not enough to accommodate all students if two students can fit at one desk.
 */

    public static int tables(int students, int FreeTables) {
        int tablesRequired = (students + 1) / 2;
        int tablesNeeded = tablesRequired - FreeTables;

        return Math.max(tablesNeeded, 0);
    }
}