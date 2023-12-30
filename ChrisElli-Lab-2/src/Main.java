public class Main {
    public static void main(String[] args) {
        Administrator admin = new Administrator("Alice", 30, 60000, "HR");
        Programmer prog = new Programmer("Bob", 25, 70000, "Java");
        Manager mgr = new Manager("Charlie", 35, 80000, "Sales");

        // Вывод информации о сотрудниках
        System.out.println("Administrator Name: " + admin.getName());
        admin.work();
        System.out.println("Programmer Name: " + prog.getName());
        prog.work();
        System.out.println("Manager Name: " + mgr.getName());
        mgr.work();

        // Вывод счетчика созданных программистов
        System.out.println("Total Programmers: " + Programmer.getProgrammerCount());
    }
}