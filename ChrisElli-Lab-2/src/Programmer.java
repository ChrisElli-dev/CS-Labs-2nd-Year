public class Programmer extends Employee {
    private static int programmerCount = 0;
    private String programmingLanguage;

    public Programmer(String name, int age, double salary, String programmingLanguage) {
        super(name, age, salary);//наследование параметров
        this.programmingLanguage = programmingLanguage;
        programmerCount++;  // Увеличиваем счетчик при создании объекта
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    @Override//Аннотация о замещении метода из суперкласса
    public void work() {
        System.out.println("Programmer is coding in " + programmingLanguage + ".");
    }

    // Статический метод для получения счетчика объектов
    public static int getProgrammerCount() {
        return programmerCount;
    }
}