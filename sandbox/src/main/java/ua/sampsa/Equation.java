package ua.sampsa;

/**
 * Created by Maryna Tkachuk on 13.12.20.
 */
public class Equation {
  private double a;
  private double b;
  private double c;

  private int number;

  public Equation(double a, double b, double c) {

    this.a = a;
    this.b = b;
    this.c = c;

    double discriminant = b * b - 4 * a * c;

    if (discriminant > 0) {
        number = 2;
    } else if (discriminant == 0) {
        number = 1;
    } else {
        number = 0;
    }
  }

  public int rootNumber(){
      return number;
  }
}
