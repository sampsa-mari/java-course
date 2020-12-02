package ua.sampsa;

/**
 * Created by Maryna Tkachuk on 01.12.20.
 */
public class Rectangle {

  public double x;
  public double y;

  public Rectangle(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double area(){
    return this.x * this.y;
  }

}
