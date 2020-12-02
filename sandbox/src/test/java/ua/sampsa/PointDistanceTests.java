package ua.sampsa;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Maryna Tkachuk on 02.12.20.
 */
public class PointDistanceTests {

  @Test
  public void pointDistanceCalculation(){

    Point p1 = new Point(2,8);
    Point p2 = new Point(5,9);

    Assert.assertEquals(Point.distance(p1,p2), 3.16,0.01);
  }

  @Test
  public void pointDistanceError(){

    Point p1 = new Point(2,8);
    Point p2 = new Point(5,9);

    double actualResult = Point.distance(p1, p2);
    double expectedResult = 3.16;
    if (actualResult != expectedResult){
      System.out.println("The calculation has to be checked!");
    }

  }


}
