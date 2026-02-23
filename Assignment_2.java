package sample;

import java.util.Scanner;

// Base Class
class Employee {
    protected String name;
    protected double salary;

    // Constructor
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Method to display salary
    public void displaySalary() {
        System.out.println("Employee Name: " + name);
        System.out.println("Salary before hike: " + salary);
    }
}

// Derived Class 1
class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    public void calculateSalary() {
        double oldSalary = salary;
        salary = salary + (salary * 0.50);

        System.out.println("\n--- Full Time Employee ---");
        System.out.println("Salary before hike: " + oldSalary);
        System.out.println("Salary after 50% hike: " + salary);
    }
}

// Derived Class 2
class InternEmployee extends Employee {

    public InternEmployee(String name, double salary) {
        super(name, salary);
    }

    public void calculateSalary() {
        double oldSalary = salary;
        salary = salary + (salary * 0.25);

        System.out.println("\n--- Intern Employee ---");
        System.out.println("Salary before hike: " + oldSalary);
        System.out.println("Salary after 25% hike: " + salary);
    }
}

// Main Class
public class Assignment_2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Full Time Employee
        System.out.print("Enter Full Time Employee Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter Salary: ");
        double fsalary = sc.nextDouble();
        sc.nextLine();

        FullTimeEmployee f1 = new FullTimeEmployee(fname, fsalary);
        f1.displaySalary();
        f1.calculateSalary();

        // Intern Employee
        System.out.print("\nEnter Intern Employee Name: ");
        String iname = sc.nextLine();
        System.out.print("Enter Salary: ");
        double isalary = sc.nextDouble();

        InternEmployee i1 = new InternEmployee(iname, isalary);
        i1.displaySalary();
        i1.calculateSalary();

        sc.close();
    }
}