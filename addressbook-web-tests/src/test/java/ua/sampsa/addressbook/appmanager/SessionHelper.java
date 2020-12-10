package ua.sampsa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Maryna Tkachuk on 10.12.20.
 */
public class SessionHelper {
  private WebDriver wd;

  public SessionHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void login(String username, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }


  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

}
