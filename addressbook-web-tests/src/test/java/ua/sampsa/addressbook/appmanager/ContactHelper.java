package ua.sampsa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ua.sampsa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitNewContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
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

  public void select(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void updateNewContact() {
    click(By.xpath("//*[@name='update'][@value='Update']"));
  }

  public void delete() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact) {
    fillNewContactForm(contact);
    submitNewContact();
  }

  public void modify(ContactData modifiedContact, int index) {
    initContactModification(index);
    fillNewContactForm(modifiedContact);
    updateNewContact();
    returnToHomePage();
  }

  public void delete(int index) {
    select(index);
    delete();
    returnToHomePage();
  }

  public boolean isThereAnyContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount()  {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {

    List<ContactData> contacts = new ArrayList<ContactData>(); //Create the List to fill

    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));     //Get all rows

    for (WebElement row : rows ) {                                 //Get data (id, firstName, lastName) from each row
      int id = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
      String lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();

      ContactData contactData = new ContactData(id, lastName, firstName, null,null, null,null,null,null,null,null,null);

      contacts.add(contactData);
    }

    return contacts;
  }
}
