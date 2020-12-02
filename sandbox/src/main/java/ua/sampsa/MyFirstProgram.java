package ua.sampsa;

public class MyFirstProgram {

    public static void main(String args[]) {
        hello("world");
        hello("user");
        hello("Aleksei");

        Square s = new Square(5); // new keyword is used to create new objects
        System.out.println("The area of square with side length " + s.l + " = " + s.area());


        Rectangle pushkin = new Rectangle(4,5);
        System.out.println("The area of a rectangle with a sides length " + pushkin.x + " and " + pushkin.y + " = " + pushkin.area());
    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }
}