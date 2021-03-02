package ua.sampsa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.ContactData;
import ua.sampsa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactTests extends TestBase {

  @Test
  public void testNewContact() throws Exception {

    List<ContactData> beforeNewContactAdded = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToAddNewPage();

    ContactData newContact = new ContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987");
    app.getContactHelper().createContact(newContact);
    app.getNavigationHelper().goToHomePage();

    List<ContactData> afterNewContactAdded = app.getContactHelper().getContactList();
    System.out.println("before creation - " + beforeNewContactAdded.size() + "; after - " + afterNewContactAdded.size());
    Assert.assertEquals(afterNewContactAdded.size(), beforeNewContactAdded.size() + 1);

    // Find in afterList max Id (which means new contact) and set it to beforeList
    newContact.setId(afterNewContactAdded.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    beforeNewContactAdded.add(newContact);
    Assert.assertEquals(new HashSet<Object>(beforeNewContactAdded), new HashSet<Object>(afterNewContactAdded));
  }

  @Test
  public void testEditContact(){
    if(! app.getContactHelper().isThereAnyContact()){
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData(
              "Vasya",
              "Olegovich",
              "Pupkin",
              "Rock-n-Roll",
              "Yandex",
              "Pobedy Street, 25",
              "014789564711",
              "pupkin@yandex.ru",
              "1",
              "January",
              "1987"));
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> beforeContactModification = app.getContactHelper().getContactList();
    //app.getContactHelper().selectContact(beforeContactModification.size() - 1); //select last contact (click on checkbox)
    app.getContactHelper().initContactModification(beforeContactModification.size() - 1); // click on 'Edit' button (last one according to index)

    ContactData modifiedContact = new ContactData(beforeContactModification.get(beforeContactModification.size() - 1).getId(),
            "test1",
            "test1",
            "P",
            "ohne",
            "mzilla",
            "MinskerStreet, 25",
            "0121212121211",
            "olen@yandex.ru",
            "3",
            "March",
            "1997");
    app.getContactHelper().fillNewContactForm(modifiedContact);
    app.getContactHelper().updateNewContact();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> afterContactModification = app.getContactHelper().getContactList();
    Assert.assertEquals(afterContactModification.size(), beforeContactModification.size());

    beforeContactModification.remove(beforeContactModification.size() - 1);
    beforeContactModification.add(modifiedContact);

    Comparator<? super ContactData> byId = (contact1, contact2) -> Integer.compare(contact1.getId(), contact2.getId());
    beforeContactModification.sort(byId);
    afterContactModification.sort(byId);
    Assert.assertEquals(beforeContactModification, afterContactModification);
  }

  @Test
  public void testEditContactWithNULL() {
    if (!app.getContactHelper().isThereAnyContact()) {
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987"));
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> beforeContactModification = app.getContactHelper().getContactList();
    // app.getContactHelper().selectContact(beforeContactModification.size() - 1); //select last contact (click on checkbox)
    app.getContactHelper().initContactModification(beforeContactModification.size() - 1);  // click on 'Edit' button (last one according to index)
    ContactData modifiedContact = new ContactData(beforeContactModification.get(beforeContactModification.size() - 1).getId(),
            "Rick",
            null,
            "O",
            null,
            null,
            "M25",
            "77777",
            "olen@ua.ua",
            "4",
            "March",
            "1990");
    app.getContactHelper().fillNewContactForm(modifiedContact);
    app.getContactHelper().updateNewContact();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> afterContactModification = app.getContactHelper().getContactList();
   // System.out.println("before modificaton - " + beforeContactModification.size() + "; after - " + afterContactModification.size());
    Assert.assertEquals(afterContactModification.size(), beforeContactModification.size());

    // Copy modified row with NULLs from afterList
    ContactData buffer = afterContactModification.get(beforeContactModification.size() - 1);
    ContactData newModifiedNULLContact = new ContactData(buffer.getId(), buffer.getLastName(), buffer.getFirstName(), null,null,null,null,null,null,null,null,null );

    beforeContactModification.remove(beforeContactModification.size() - 1);
    // add modified row with NULLs to before for correct comparing
    beforeContactModification.add(newModifiedNULLContact);

    Comparator<? super ContactData> byId = (contact1, contact2) -> Integer.compare(contact1.getId(), contact2.getId());
    beforeContactModification.sort(byId);
    afterContactModification.sort(byId);
    Assert.assertEquals(beforeContactModification, afterContactModification);

  }

  @Test
  public void testDeleteContact(){
    if(! app.getContactHelper().isThereAnyContact()){
      app.getNavigationHelper().goToAddNewPage();
      app.getContactHelper().createContact(new ContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987"));
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> beforeDeletion = app.getContactHelper().getContactList();

    app.getContactHelper().selectContact(beforeDeletion.size() - 1); //select last contact (click on checkbox)
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().goToHomePage();

    List<ContactData> afterDeletion = app.getContactHelper().getContactList();
    System.out.println("before deletion - " + beforeDeletion.size() + "; after deletion - " + afterDeletion.size());
    Assert.assertEquals(afterDeletion.size(), beforeDeletion.size() - 1);

    beforeDeletion.remove(beforeDeletion.size() - 1); // to make sure, that we deleted expected contact, we delete it now forcibly and check if the Lists are equal
    Assert.assertEquals(beforeDeletion, afterDeletion); // compare the original list 'before' with the resulting 'after'
  }

}
