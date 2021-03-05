package ua.sampsa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.ContactData;
import ua.sampsa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
             // .withHomePhone("111")
              .withMobilePhone("222")
             // .withWorkPhone("333")
              .withEmail("pupkin@yandex.ru")
              .withDayOfBirth("1")
              .withMonthOfBirth( "January")
              .withYearOfBirth("1987"));
      app.contact().returnToHomePage();
    }
  }

  @Test(enabled = false)
  public void testNewContact() throws Exception {
    Contacts beforeNewContactAdded = app.contact().all();
    app.goTo().addNewPage();
    ContactData newContact = new ContactData()
            .withFirstName("Vasya")
            .withMiddleName("Olegovich")
            .withLastName("Pupkin")
            .withNickName("Rock-n-Roll")
            .withCompanyName("Yandex")
            .withAddress("Pobedy Street, 25")
            .withHomePhone("444")
            .withMobilePhone("555")
            .withWorkPhone("666")
            .withEmail("pupkin@yandex.ru")
            .withDayOfBirth("1")
            .withMonthOfBirth( "January")
            .withYearOfBirth("1987");
    app.contact().create(newContact);
    app.contact().returnToHomePage();
    assertThat(app.contact().count(), equalTo(beforeNewContactAdded.size() + 1));
    Contacts afterNewContactAdded = app.contact().all();
    assertThat(afterNewContactAdded, equalTo(beforeNewContactAdded.withAdded(newContact.withId(afterNewContactAdded.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadNameContact() throws Exception {
    Contacts beforeNewContactAdded = app.contact().all();
    app.goTo().addNewPage();
    ContactData newContact = new ContactData()
            .withFirstName("Vasya1'")
            .withMiddleName("Olegovich")
            .withLastName("Pupkin")
            .withNickName("Rock-n-Roll")
            .withCompanyName("Yandex")
            .withAddress("Pobedy Street, 25")
            .withHomePhone("444")
            .withMobilePhone("555")
            .withWorkPhone("666")
            .withEmail("pupkin@yandex.ru")
            .withDayOfBirth("1")
            .withMonthOfBirth( "January")
            .withYearOfBirth("1987");
    app.contact().create(newContact);
    app.contact().returnToHomePage();
    assertThat(app.contact().count(), equalTo(beforeNewContactAdded.size()));
    Contacts afterNewContactAdded = app.contact().all();
    assertThat(afterNewContactAdded, equalTo(beforeNewContactAdded));
  }

  @Test(enabled = true)
  public void testEditContact(){
    Contacts beforeContactModification = app.contact().all();
    ContactData randomEditedContact = beforeContactModification.iterator().next(); // select from Set random contact that will be modified. 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    ContactData modifiedContact = new ContactData()
            .withId(randomEditedContact.getId())
            .withFirstName("Gena")
            .withMiddleName("Lg.")
            .withLastName("Olennikof")
            .withNickName("ohne")
            .withCompanyName("mzilla")
           // .withAddress("MinskerStreet, 25")
            .withHomePhone("777")
            .withMobilePhone("888")
            .withWorkPhone("999")
            .withEmail( "olen@yandex.ru")
            .withDayOfBirth("3")
            .withMonthOfBirth("March")
            .withYearOfBirth("1997");

    app.contact().modifyById(modifiedContact);
    assertThat(app.contact().count(), equalTo(beforeContactModification.size()));
    Contacts afterContactModification = app.contact().all();
    assertThat(afterContactModification, equalTo(beforeContactModification.without(randomEditedContact).withAdded(modifiedContact)));
  }

  @Test(enabled = false)
  public void testDeleteContact(){
    Contacts beforeDeletion = app.contact().all();
    ContactData randomDeletedContact = beforeDeletion.iterator().next(); // 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    app.contact().deleteById(randomDeletedContact);
    assertThat(app.contact().count(), equalTo(beforeDeletion.size() - 1));
    Contacts afterDeletion = app.contact().all();
    assertThat(afterDeletion, equalTo(beforeDeletion.without(randomDeletedContact)));
  }

  @Test(enabled = false)
  public void testContactsPhones(){
    ContactData contact = app.contact().all().iterator().next();
    Contacts beforeContactModification = app.contact().all();


  }
}
