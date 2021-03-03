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
      app.contact().create(new ContactData()
              .withFirstName("Vasya")
              .withMiddleName("Olegovich")
              .withLastName("Pupkin")
              .withNickName("Rock-n-Roll")
              .withCompanyName("Yandex")
              .withAddress("Pobedy Street, 25")
              .withMobilePhone("014789564711")
              .withEmail("pupkin@yandex.ru")
              .withDayOfBirth("1")
              .withMonthOfBirth( "January")
              .withYearOfBirth("1987"));
      app.contact().returnToHomePage();
    }
  }

  @Test(enabled = true)
  public void testNewContact() throws Exception {
    List<ContactData> beforeNewContactAdded = app.contact().list();
    app.goTo().addNewPage();
    ContactData newContact = new ContactData()
            .withFirstName("Vasya")
            .withMiddleName("Olegovich")
            .withLastName("Pupkin")
            .withNickName("Rock-n-Roll")
            .withCompanyName("Yandex")
            .withAddress("Pobedy Street, 25")
            .withMobilePhone("014789564711")
            .withEmail("pupkin@yandex.ru")
            .withDayOfBirth("1")
            .withMonthOfBirth( "January")
            .withYearOfBirth("1987");
    app.contact().create(newContact);
    app.contact().returnToHomePage();
    List<ContactData> afterNewContactAdded = app.contact().list();
//    System.out.println("before creation - " + beforeNewContactAdded.size() + "; after - " + afterNewContactAdded.size());
    Assert.assertEquals(afterNewContactAdded.size(), beforeNewContactAdded.size() + 1);
    // Find in afterList max Id (which means new contact) and set it to beforeList
    newContact.withId(afterNewContactAdded.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    beforeNewContactAdded.add(newContact);
    Assert.assertEquals(new HashSet<Object>(beforeNewContactAdded), new HashSet<Object>(afterNewContactAdded));
  }

  @Test(enabled = true)
  public void testEditContact(){
    List<ContactData> beforeContactModification = app.contact().list();
    ContactData modifiedContact = new ContactData()
            .withId(beforeContactModification.get(beforeContactModification.size() - 1).getId())
            .withFirstName("test1")
            .withMiddleName("test1")
            .withLastName("P")
            .withNickName("ohne")
            .withCompanyName("mzilla")
            .withAddress("MinskerStreet, 25")
            .withMobilePhone("0121212121211")
            .withEmail( "olen@yandex.ru")
            .withDayOfBirth("3")
            .withMonthOfBirth("March")
            .withYearOfBirth("1997");
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
    ContactData modifiedContact = new ContactData()
            .withId(beforeContactModification.get(beforeContactModification.size() - 1).getId())
            .withLastName("Olennikoff")
            .withFirstName("Rick")
            .withCompanyName("Cisco")
            .withMobilePhone("0799-77777777")
            .withEmail("olen@ua.ua")
            .withDayOfBirth("12")
            .withMonthOfBirth("March")
            .withYearOfBirth("1978");
    int index = beforeContactModification.size() - 1;
    app.contact().modify(modifiedContact, index);
    List<ContactData> afterContactModification = app.contact().list();
   // System.out.println("before modificaton - " + beforeContactModification.size() + "; after - " + afterContactModification.size());
    Assert.assertEquals(afterContactModification.size(), beforeContactModification.size());

    // Copy modified row with NULLs from afterList
    ContactData buffer = afterContactModification.get(index);
    ContactData newModifiedNULLContact = new ContactData().withId(buffer.getId()).withLastName(buffer.getLastName()).withFirstName(buffer.getFirstName());

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
