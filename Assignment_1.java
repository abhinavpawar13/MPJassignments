package sample;

import java.util.Scanner;

// Student Class
class Student {

    private String name;
    private int rollNo;
    private double[] marks = new double[5];
    private double percentage;

    // Constructor
    public Student(String name, int rollNo, double[] marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    // Method to calculate percentage
    public void calculatePercentage() {
        double total = 0;
        for (int i = 0; i < 5; i++) {
            total += marks[i];
        }
        percentage = total / 5;
    }

    // Method to determine grade
    public String getGrade() {
        if (percentage >= 75) {
            return "Grade A";
        } else if (percentage >= 60) {
            return "Grade B";
        } else {
            return "Grade C";
        }
    }

    // Method to display details
    public void display() {
        System.out.println("\n----- Student Details -----");
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNo);
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Result: " + getGrade());
    }
}

// Main Class
public class Assignment_1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Roll Number: ");
        int rollNo = sc.nextInt();

        double[] marks = new double[5];
        System.out.println("Enter Marks for 5 Subjects:");

        for (int i = 0; i < 5; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = sc.nextDouble();
        }

        // Creating object
        Student s1 = new Student(name, rollNo, marks);

        s1.calculatePercentage();
        s1.display();

        sc.close();
    }
}