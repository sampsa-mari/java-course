package ua.sampsa.addressbook.tests;

import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;

public class NewContactTests extends TestBase {

  @Test
  public void testNewContact() throws Exception {
    app.getNavigationHelper().goToAddNewPage();
    app.fillNewContactForm(new GroupData.NewContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987"));
    app.submitNewContact();
    app.getNavigationHelper().goToHomePage();
  }
}
