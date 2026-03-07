/*
 * CREATE DATABASE studentdb;
USE studentdb;

CREATE TABLE students(
rollno INT PRIMARY KEY,
name VARCHAR(50),
sub1 DOUBLE,
sub2 DOUBLE,
sub3 DOUBLE,
sub4 DOUBLE,
sub5 DOUBLE,
average DOUBLE,
grade CHAR(1)
);
*/
package sample;

import java.sql.*;
import java.util.Scanner;

public class Extra_assignment_MySQL_CRUD {

    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";        // change if different
    static final String PASSWORD = "root";    // change to your MySQL password

    static Scanner sc = new Scanner(System.in);

    // Method to calculate grade
    public static String calculateGrade(double avg) {
        if (avg >= 75)
            return "A";
        else if (avg >= 50)
            return "B";
        else
            return "C";
    }

    // CREATE - Insert student
    public static void insertStudent() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            double[] marks = new double[5];
            double total = 0;

            for (int i = 0; i < 5; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = sc.nextDouble();
                total += marks[i];
            }

            double avg = total / 5;
            String grade = calculateGrade(avg);

            String query = "INSERT INTO students VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, roll);
            pst.setString(2, name);
            pst.setDouble(3, marks[0]);
            pst.setDouble(4, marks[1]);
            pst.setDouble(5, marks[2]);
            pst.setDouble(6, marks[3]);
            pst.setDouble(7, marks[4]);
            pst.setDouble(8, avg);
            pst.setString(9, grade);

            pst.executeUpdate();

            System.out.println("Student inserted successfully");
            System.out.println("Average = " + avg);
            System.out.println("Grade = " + grade);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ - View students
    public static void viewStudents() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            String query = "SELECT * FROM students";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("\nRoll No: " + rs.getInt("rollno"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Average: " + rs.getDouble("average"));
                System.out.println("Grade: " + rs.getString("grade"));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE student marks
    public static void updateStudent() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Roll No to update: ");
            int roll = sc.nextInt();

            double[] marks = new double[5];
            double total = 0;

            for (int i = 0; i < 5; i++) {
                System.out.print("Enter new marks for subject " + (i + 1) + ": ");
                marks[i] = sc.nextDouble();
                total += marks[i];
            }

            double avg = total / 5;
            String grade = calculateGrade(avg);

            String query = "UPDATE students SET sub1=?,sub2=?,sub3=?,sub4=?,sub5=?,average=?,grade=? WHERE rollno=?";
            PreparedStatement pst = con.prepareStatement(query);

            pst.setDouble(1, marks[0]);
            pst.setDouble(2, marks[1]);
            pst.setDouble(3, marks[2]);
            pst.setDouble(4, marks[3]);
            pst.setDouble(5, marks[4]);
            pst.setDouble(6, avg);
            pst.setString(7, grade);
            pst.setInt(8, roll);

            pst.executeUpdate();

            System.out.println("Student updated successfully");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE student
    public static void deleteStudent() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.print("Enter Roll No to delete: ");
            int roll = sc.nextInt();

            String query = "DELETE FROM students WHERE rollno=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, roll);

            pst.executeUpdate();

            System.out.println("Student deleted successfully");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n1. Insert Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}