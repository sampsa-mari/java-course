package ua.sampsa;

/**
 * Created by Maryna Tkachuk on 02.12.20.
 */
public class RunPoints {
  public static void main(String args[]) {

    Point p1 = new Point();
    Point p2 = new Point();

    p1.x = 1;
    p2.x = 2;
    p1.y = 3;
    p2.y = 4;

    System.out.println("The distance between 2 points - A and B with the coordinates: PointA (x1 - " + p1.x + ", y1 - " + p1.y + "), and PointB (x2 - " + p2.x + ", y2 - " + p2.y + ") = " + distance(p1, p2));
  }

    public static double distance(Point p1, Point p2){
      return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }

  }
