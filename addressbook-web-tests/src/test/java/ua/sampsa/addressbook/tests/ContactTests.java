package ua.sampsa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions_atLeastOneContactIsPresent(){
    if(app.contact().all().size() == 0){
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
    Set<ContactData> beforeNewContactAdded = app.contact().all();
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
    Set<ContactData> afterNewContactAdded = app.contact().all();
    Assert.assertEquals(afterNewContactAdded.size(), beforeNewContactAdded.size() + 1);

    // Find in afterList max Id (which means new contact) and set it to beforeList
    newContact.withId(afterNewContactAdded.stream().mapToInt((c) -> c.getId()).max().getAsInt());;
    beforeNewContactAdded.add(newContact);
    Assert.assertEquals(beforeNewContactAdded, afterNewContactAdded);
  }

  @Test(enabled = true)
  public void testEditContact(){
    Set<ContactData> beforeContactModification = app.contact().all(); // Create Set of existing contacts
    ContactData randomEditedContact = beforeContactModification.iterator().next(); // select from Set random contact that will be modified. 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set

    ContactData modifiedContact = new ContactData()
            .withId(randomEditedContact.getId())
            .withFirstName("Gena")
            .withMiddleName("L.")
            .withLastName("Olennikof")
            .withNickName("ohne")
            .withCompanyName("mzilla")
           // .withAddress("MinskerStreet, 25")
            .withMobilePhone("0121212121211")
            .withEmail( "olen@yandex.ru")
            .withDayOfBirth("3")
            .withMonthOfBirth("March")
            .withYearOfBirth("1997");

    app.contact().modifyById(modifiedContact);
    Set<ContactData> afterContactModification = app.contact().all();
    Assert.assertEquals(afterContactModification.size(), beforeContactModification.size());

    beforeContactModification.remove(randomEditedContact);
    beforeContactModification.add(modifiedContact);
    Assert.assertEquals(beforeContactModification, afterContactModification);
  }

//  @Test(enabled = false)
//  public void testEditContactWithNULL() {
//    List<ContactData> beforeContactModification = app.contact().list();
//    ContactData modifiedContact = new ContactData()
//            .withId(beforeContactModification.get(beforeContactModification.size() - 1).getId())
//            .withLastName("Olennikoff")
//            .withFirstName("Rick")
//            .withCompanyName("Cisco")
//            .withMobilePhone("0799-77777777")
//            .withEmail("olen@ua.ua")
//            .withDayOfBirth("12")
//            .withMonthOfBirth("March")
//            .withYearOfBirth("1978");
//    int index = beforeContactModification.size() - 1;
//    app.contact().modify(modifiedContact, index);
//    List<ContactData> afterContactModification = app.contact().list();
//   // System.out.println("before modificaton - " + beforeContactModification.size() + "; after - " + afterContactModification.size());
//    Assert.assertEquals(afterContactModification.size(), beforeContactModification.size());
//
//    // Copy modified row with NULLs from afterList
//    ContactData buffer = afterContactModification.get(index);
//    ContactData newModifiedNULLContact = new ContactData().withId(buffer.getId()).withLastName(buffer.getLastName()).withFirstName(buffer.getFirstName());
//
//    beforeContactModification.remove(index);
//    // add modified row with NULLs to before for correct comparing
//    beforeContactModification.add(newModifiedNULLContact);
//
//    Comparator<? super ContactData> byId = (contact1, contact2) -> Integer.compare(contact1.getId(), contact2.getId());
//    beforeContactModification.sort(byId);
//    afterContactModification.sort(byId);
//    Assert.assertEquals(beforeContactModification, afterContactModification);
//  }

  @Test(enabled = true)
  public void testDeleteContact(){
    Set<ContactData> beforeDeletion = app.contact().all();
    ContactData randomDeletedContact = beforeDeletion.iterator().next(); // 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    app.contact().deleteById(randomDeletedContact);
    Set<ContactData> afterDeletion = app.contact().all();
    Assert.assertEquals(afterDeletion.size(), beforeDeletion.size() - 1);

    beforeDeletion.remove(randomDeletedContact); // to make sure, that we deleted expected contact, we delete it now forcibly and check if the Lists are equal
    Assert.assertEquals(beforeDeletion, afterDeletion); // compare the original set 'before' with the resulting 'after'
  }

}
