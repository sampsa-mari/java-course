package ua.sampsa.addressbook.tests;

import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;

public class ContactTests extends TestBase {

  @Test
  public void testNewContact() throws Exception {
    app.getNavigationHelper().goToAddNewPage();
    app.getContactHelper().fillNewContactForm(new GroupData.NewContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987"));
    app.getContactHelper().submitNewContact();
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testEditContact(){
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewContactForm(new GroupData.NewContactData("Petya", "P", "Olennikov", "ohne", "mzilla", "MinskerStreet, 25", "0121212121211", "olen@yandex.ru", "3", "March", "1997"));
    app.getContactHelper().updateNewContact();
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testDeleteContact(){
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().deleteContact();
  }
}
