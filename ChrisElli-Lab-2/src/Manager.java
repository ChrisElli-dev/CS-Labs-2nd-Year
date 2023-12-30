public class Manager extends Employee {
    private String teamName;

    public Manager(String name, int age, double salary, String teamName) {
        super(name, age, salary);
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override//Аннотация о замещении метода из суперкласса
    public void work() {
        System.out.println("Manager is leading the " + teamName + " team.");
    }
}