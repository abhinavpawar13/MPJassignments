package sample;

public class Assignment_3 {

    double radius, length, breadth, side;

    Assignment_3a(double r) {
        radius = r;
    }

    Assignment_3a(double l, double b) {
        length = l;
        breadth = b;
    }

    Assignment_3a(int s) {
        side = s;
    }

    double area(double r) {
        return Math.PI * r * r;
    }

    double area(double l, double b) {
        return l * b;
    }

    double area(int s) {
        return s * s;
    }

    public static void main(String[] args) {

        Assignment_3a circle = new Assignment_3a(5.0);
        System.out.println("Area of Circle: " + circle.area(5.0));

        Assignment_3a rectangle = new Assignment_3a(4.0, 6.0);
        System.out.println("Area of Rectangle: " + rectangle.area(4.0, 6.0));

        Assignment_3a square = new Assignment_3a(4);
        System.out.println("Area of Square: " + square.area(4));
    }
}