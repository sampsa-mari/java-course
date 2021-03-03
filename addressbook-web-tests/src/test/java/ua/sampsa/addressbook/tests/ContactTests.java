package ua.sampsa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions_atLeastOneContactIsPresent(){
    if(app.contact().list().size() == 0){
      app.goTo().addNewPage();
      app.contact().create(new ContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987"));
      app.contact().returnToHomePage();
    }
  }

  @Test(enabled = true)
  public void testNewContact() throws Exception {
    List<ContactData> beforeNewContactAdded = app.contact().list();
    app.goTo().addNewPage();
    ContactData newContact = new ContactData("Vasya", "Olegovich", "Pupkin", "Rock-n-Roll", "Yandex", "Pobedy Street, 25", "014789564711", "pupkin@yandex.ru", "1", "January", "1987");
    app.contact().create(newContact);
    app.contact().returnToHomePage();
    List<ContactData> afterNewContactAdded = app.contact().list();
//    System.out.println("before creation - " + beforeNewContactAdded.size() + "; after - " + afterNewContactAdded.size());
    Assert.assertEquals(afterNewContactAdded.size(), beforeNewContactAdded.size() + 1);

    // Find in afterList max Id (which means new contact) and set it to beforeList
    newContact.setId(afterNewContactAdded.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    beforeNewContactAdded.add(newContact);
    Assert.assertEquals(new HashSet<Object>(beforeNewContactAdded), new HashSet<Object>(afterNewContactAdded));
  }

  @Test(enabled = true)
  public void testEditContact(){
    List<ContactData> beforeContactModification = app.contact().list();
    ContactData modifiedContact = new ContactData(beforeContactModification.get(beforeContactModification.size() - 1).getId(), "test1", "test1", "P", "ohne", "mzilla", "MinskerStreet, 25", "0121212121211", "olen@yandex.ru", "3", "March", "1997");
    int index = beforeContactModification.size() - 1;
    app.contact().modify(modifiedContact, index);
    List<ContactData> afterContactModification = app.contact().list();
    Assert.assertEquals(afterContactModification.size(), beforeContactModification.size());

    beforeContactModification.remove(index);
    beforeContactModification.add(modifiedContact);

    Comparator<? super ContactData> byId = (contact1, contact2) -> Integer.compare(contact1.getId(), contact2.getId());
    beforeContactModification.sort(byId);
    afterContactModification.sort(byId);
    Assert.assertEquals(beforeContactModification, afterContactModification);
  }


  @Test(enabled = true)
  public void testEditContactWithNULL() {
    List<ContactData> beforeContactModification = app.contact().list();
    ContactData modifiedContact = new ContactData(beforeContactModification.get(beforeContactModification.size() - 1).getId(), "Rick", null, "O", null, null, "M25", "77777", "olen@ua.ua", "4", "March", "1990");
    int index = beforeContactModification.size() - 1;
    app.contact().modify(modifiedContact, index);
    List<ContactData> afterContactModification = app.contact().list();
   // System.out.println("before modificaton - " + beforeContactModification.size() + "; after - " + afterContactModification.size());
    Assert.assertEquals(afterContactModification.size(), beforeContactModification.size());

    // Copy modified row with NULLs from afterList
    ContactData buffer = afterContactModification.get(index);
    ContactData newModifiedNULLContact = new ContactData(buffer.getId(), buffer.getLastName(), buffer.getFirstName(), null,null,null,null,null,null,null,null,null );

    beforeContactModification.remove(index);
    // add modified row with NULLs to before for correct comparing
    beforeContactModification.add(newModifiedNULLContact);

    Comparator<? super ContactData> byId = (contact1, contact2) -> Integer.compare(contact1.getId(), contact2.getId());
    beforeContactModification.sort(byId);
    afterContactModification.sort(byId);
    Assert.assertEquals(beforeContactModification, afterContactModification);
  }

  @Test(enabled = true)
  public void testDeleteContact(){
    List<ContactData> beforeDeletion = app.contact().list();
    int index = beforeDeletion.size() - 1;
    app.contact().delete(index);
    List<ContactData> afterDeletion = app.contact().list();
 //   System.out.println("before deletion - " + beforeDeletion.size() + "; after deletion - " + afterDeletion.size());
    Assert.assertEquals(afterDeletion.size(), index);

    beforeDeletion.remove(index); // to make sure, that we deleted expected contact, we delete it now forcibly and check if the Lists are equal
    Assert.assertEquals(beforeDeletion, afterDeletion); // compare the original list 'before' with the resulting 'after'
  }

}
