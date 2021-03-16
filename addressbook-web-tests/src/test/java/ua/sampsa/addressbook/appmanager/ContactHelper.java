package ua.sampsa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ua.sampsa.addressbook.model.ContactData;
import ua.sampsa.addressbook.model.Contacts;

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
    type(By.name("home"),newContactData.getHomePhone());
    type(By.name("mobile"),newContactData.getMobilePhone());
    type(By.name("work"),newContactData.getWorkPhone());
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

  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href ='edit.php?id=" + id + "']")).click();
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
    contactsCache = null;
  }

  public void modifyById(ContactData modifiedContact) {
    initContactModificationById(modifiedContact.getId());
    fillNewContactForm(modifiedContact);
    updateNewContact();
    contactsCache = null;
    returnToHomePage();
  }

  public void deleteById(ContactData randomDeletedContact) {
    selectById(randomDeletedContact.getId());
    delete();
    contactsCache = null;
    returnToHomePage();
  }

  public boolean isThereAnyContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public String cleaned(String phone){ return phone.replaceAll("\\s", "").replaceAll("[-()]", ""); }

  public int count()  {
    return wd.findElements(By.name("selected[]")).size();
  }

//  public List<ContactData> list() {
//    List<ContactData> contacts = new ArrayList<ContactData>(); //Create the List to fill
//    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));     //Get all rows
//    for (WebElement row : rows ) {                                 //Get data (id, firstName, lastName) from each row
//      int id = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
//      String lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
//      String firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
//      contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
//    }
//    return contacts;
//  }

  private Contacts contactsCache = null;

  public Contacts all() {
    contactsCache = new Contacts(); //Create the Set to fill (object that creating in Contacts class)
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));     //Get all rows
    for (WebElement row : rows ) {                                 //Get data (id, firstName, lastName) from each row
      int id = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
      String lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String[] allPhones = row.findElement(By.cssSelector("td:nth-child(6)")).getText().split("\n");
      contactsCache.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName)
              .withHomePhone(allPhones[0]).withMobilePhone(allPhones[1]).withWorkPhone(allPhones[2]));
    }
    return contactsCache;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homephone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).
            withHomePhone(homephone).withMobilePhone(mobilephone).withWorkPhone(workphone);
  }
//  public Contacts all() {
//    if (contactsCache != null){
//      return new Contacts(contactsCache);
//    }
//    contactsCache = new Contacts(); //Create the Set to fill (object that creating in Contacts class)
//    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));     //Get all rows
//    for (WebElement row : rows ) {                               //Get data (id, firstName, lastName) from each row
//      List<WebElement> cells = row.findElements(By.tagName("td"));
//      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
//      String lastName = cells.get(1).getText();
//      String firstName = cells.get(2).getText();
//      String[] allPhones = cells.get(5).getText().split("\n");
//      contactsCache.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
//    }
//    return new Contacts(contactsCache);
//  }

}
