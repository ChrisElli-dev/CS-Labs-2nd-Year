public class Administrator extends Employee {
    private String department;

    public Administrator(String name, int age, double salary, String department) {
        super(name, age, salary);
        this.department = department;
    }
 //геттер и сеттеры
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override//Аннотация о замещении метода из суперкласса
    public void  work() {
        System.out.println("Administrator is managing the " + department + " department.");
    }
}