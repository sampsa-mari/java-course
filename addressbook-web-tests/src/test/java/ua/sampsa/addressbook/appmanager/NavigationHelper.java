package ua.sampsa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super (wd);
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }

  public void goToHomePage() {
    click(By.linkText("home page"));
  }

  public void goToAddNewPage() {
    click(By.linkText("add new"));
  }
}
