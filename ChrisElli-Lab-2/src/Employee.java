// Абстрактный базовый класс
public abstract class Employee {
    private String name;
    private int age;
    private double salary;

    //конструктор по умолчанию
    public Employee() {
        this.name = "Unknown";
        this.age = 0;
        this.salary = 0.0;
    }
    //конструктор с параметрами
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    //геттеры и сеттеры
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Абстрактный метод
    public abstract void work();
}