package ua.sampsa.addressbook;

import org.testng.annotations.Test;

public class NewContact extends TestBase{

  @Test
  public void testNewContact() throws Exception {
    goToAddNewPage();
    fillNewContactForm(new NewContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987"));
    submitNewContact();
    goToHomePage();
  }
}
