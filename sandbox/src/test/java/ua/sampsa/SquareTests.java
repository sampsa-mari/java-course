package ua.sampsa;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Maryna Tkachuk on 02.12.20.
 */
public class SquareTests {

  @Test
  public void testArea(){

    Square s = new Square(5);
    Assert.assertEquals(s.area(),25.0);
  }

}
