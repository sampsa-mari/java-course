package ua.sampsa.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupDeletionTests {
  private WebDriver wd;

  @BeforeMethod
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testGroupDeletion() throws Exception {
    wd.get("http://localhost/addressbook/index.php");
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.xpath("//input[@value='Login']")).click();
    wd.findElement(By.linkText("groups")).click();
    wd.findElement(By.name("selected[]")).click();
    wd.findElement(By.name("delete")).click();
    wd.findElement(By.linkText("group page")).click();
    wd.findElement(By.linkText("Logout")).click();
  }

  @AfterMethod
  public void tearDown() throws Exception {
    wd.quit();
  }


}
