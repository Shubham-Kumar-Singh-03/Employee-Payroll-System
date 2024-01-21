import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;
    private int age;

    Scanner sc = new Scanner(System.in);
    public Employee(){
        System.out.print("Enter ID Number: ");
        this.id = sc.nextInt();
        System.out.print("Enter Name: ");
        String s = sc.next();
        this.name = s;
        System.out.print("Enter age: ");
        this.age = sc.nextInt();
    }

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getage() {
        return age;
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", Age=" + age + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    Scanner sc = new Scanner(System.in);

    public FullTimeEmployee(){
        super();
        System.out.print("Enter Monthly Salary: ");
        this.monthlySalary = sc.nextDouble();
    }

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    Scanner sc = new Scanner(System.in);

    public PartTimeEmployee(){
        super();
        System.out.print("Enter No. of hour worked: ");
        this.hoursWorked = sc.nextInt();
        System.out.print("Enter the Rate per hour: ");
        this.hourlyRate = sc.nextDouble();
    }

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
    }

    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();

        Scanner sc = new Scanner(System.in);

        System.out.println("------------------------ WELCOME TO PAYROLL SYSTEM ------------------------");
        System.out.println("\n");

        boolean t = true;
        while(t){
            System.out.println("What you want to do");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Display All Employee");

            System.out.print("\nEnter your Choice: ");
            int a = sc.nextInt();
            
            switch (a) {
                case 1:{
                    System.out.println("------------------------ Add Employee ----------------------------");
                    System.out.println();
                    System.out.println("Enter Type of Employee\n1. Full Time\n2. Part Time");
                    int b = sc.nextInt();

                    if(b==1){
                        FullTimeEmployee emp = new FullTimeEmployee();
                        payrollSystem.addEmployee(emp);
                    }
                    else if(b==2){
                        PartTimeEmployee emp = new PartTimeEmployee();
                        payrollSystem.addEmployee(emp);
                    }
                    System.out.println();
                    break;
                }

                case 2: {
                    System.out.println("\n-------------------------------- Remove Employee ------------------------");
                    System.out.println("\n");
                    System.out.print("Enter Id Number: ");
                    int b = sc.nextInt();
                    payrollSystem.removeEmployee(b);
                    System.out.println("\n--------------------------------- Employee Removed --------------------------");
                    System.out.println();
                    break;
                }

                case 3:{
                    System.out.println("\n---------------------------------- All Employee Details ---------------------------------------");
                    payrollSystem.displayEmployees();
                    System.out.println();
                    break;
                }
                default:{
                    System.out.println("Wrong choice");
                    System.out.println();
                    break;
                }
            }
            
            System.out.print("Want to do anything (Y/N): ");
            char s = sc.next().charAt(0);

            if(s=='N' || s=='n'){
                t = false;
            }
            System.out.println();
        }

        // FullTimeEmployee emp1 = new FullTimeEmployee("Shubham", 101, 5000.0);
        // PartTimeEmployee emp2 = new PartTimeEmployee("Singh", 102, 30, 15.0);

        // payrollSystem.addEmployee(emp1);
        // payrollSystem.addEmployee(emp2);

        // System.out.println("Initial Employee Details:");
        // payrollSystem.displayEmployees();

        // System.out.println("\nRemoving Employee...");
        // payrollSystem.removeEmployee(101);

        // System.out.println("\nRemaining Employee Details:");
        // payrollSystem.displayEmployees();
    }
}