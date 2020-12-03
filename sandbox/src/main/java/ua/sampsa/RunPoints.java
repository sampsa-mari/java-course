package ua.sampsa;

/**
 * Created by Maryna Tkachuk on 02.12.20.
 */
public class RunPoints {
  public static void main(String args[]) {

    Point p1 = new Point(1,3);
    Point p2 = new Point(2,4);

    System.out.println("The distance between 2 points - A and B with the coordinates: PointA (x1 - " + p1.x + ", y1 - " + p1.y + ")," +
            " and PointB (x2 - " + p2.x + ", y2 - " + p2.y + ") = " + p1.distanceTo(p2));
  }

}
