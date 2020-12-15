package ua.sampsa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ua.sampsa.addressbook.model.ContactData;


public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillNewContactForm(ContactData newContactData) {
    type(By.name("firstname"), newContactData.getFirstName());
    type(By.name("middlename"),newContactData.getMiddleName());
    type(By.name("lastname"),newContactData.getLastName());
    type(By.name("nickname"),newContactData.getNickName());
    type(By.name("company"),newContactData.getCompanyName());
    type(By.name("address"),newContactData.getAddress());
    type(By.name("mobile"),newContactData.getMobilePhone());
    type(By.name("email"),newContactData.getEmail());

    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(newContactData.getDayOfBirth());
    wd.findElement(By.xpath("//option[@value='1']")).click();

    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(newContactData.getMonthOfBirth());
    wd.findElement(By.xpath("//option[@value='January']")).click();

    type(By.name("byear"), newContactData.getYearOfBirth());
  }

  public void selectContact() {
    click(By.name("selected[]"));

  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void updateNewContact() {
    click(By.xpath("//*[@name='update'][@value='Update']"));
  }

  public void deleteContact() {
    click(By.xpath("//*[@name='update'][@value='Delete']"));
  }
}
