package ua.sampsa;

public class MyFirstProgram {

    public static void main(String args[]) {
        hello("world");
        hello("user");
        hello("Aleksei");

        double length = 5;
        System.out.println("The area of square with side length " + length + " = " + area(length));

        double a = 4;
        double b = 5;
        System.out.println("The area of a rectangle with a sides length " + a + " and " + b + " = " + area(a, b));

    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody +"!");
    }

    public static double area(double l){
       return l * l;
    }

    public static double area(double x, double y){
        return x * y;
    }
}