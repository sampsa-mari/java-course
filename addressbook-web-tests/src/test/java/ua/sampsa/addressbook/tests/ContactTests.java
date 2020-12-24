package ua.sampsa.addressbook.tests;

import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.ContactData;

public class ContactTests extends TestBase {

  @Test
  public void testNewContact() throws Exception {
    app.getNavigationHelper().goToAddNewPage();
    app.getContactHelper().createContact(new ContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987"));
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testEditContact(){
    if(! app.getContactHelper().isThereAnyContact()){
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987"));
      app.getNavigationHelper().goToHomePage();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewContactForm(new ContactData("Petya", "P", "Olennikov", "ohne", "mzilla", "MinskerStreet, 25", "0121212121211", "olen@yandex.ru", "3", "March", "1997"));
    app.getContactHelper().updateNewContact();
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testEditContactWithNULL() {
    if (!app.getContactHelper().isThereAnyContact()) {
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987"));
      app.getNavigationHelper().goToHomePage();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewContactForm(new ContactData("Rick", null, "O", null, null, "M25", "77777", "olen@ua.ua", "4", "March", "1990"));
    app.getContactHelper().updateNewContact();
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testDeleteContact(){
    if(! app.getContactHelper().isThereAnyContact()){
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987"));
      app.getNavigationHelper().goToHomePage();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().deleteContact();
  }
}
