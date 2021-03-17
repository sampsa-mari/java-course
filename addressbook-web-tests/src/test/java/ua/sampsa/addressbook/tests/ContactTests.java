package ua.sampsa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.ContactData;
import ua.sampsa.addressbook.model.Contacts;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions_atLeastOneContactIsPresent(){
    if(app.contact().all().size() == 0){
      app.goTo().addNewPage();
      File photo = new File("src/test/resources/Mila.png");
      app.contact().create(new ContactData()
              .withFirstName("Vasya")
              .withMiddleName("Olegovich")
              .withLastName("Pupkin")
              .withNickName("Rock-n-Roll")
              .withCompanyName("Yandex")
              .withAddress("Pobedy Street, 25")
              .withHomePhone("111")
              .withMobilePhone("222")
              .withWorkPhone("333")
              .withEmail("email1@yandex.ru")
              .withEmail2("email2@yandex.ru")
              .withEmail3("email3@yandex.ru")
              .withDayOfBirth("1")
              .withMonthOfBirth( "January")
              .withYearOfBirth("1987")
              .withPhoto(photo));

      app.contact().returnToHomePage();
    }
  }

  @Test(enabled = true)
  public void testNewContact() throws Exception {
    Contacts beforeNewContactAdded = app.contact().all();
    app.goTo().addNewPage();
     File photo = new File("src/test/resources/Mila.png");
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
             .withEmail("email1@yandex.ru")
             .withEmail2("email2@yandex.ru")
             .withEmail3("email3@yandex.ru")
             .withDayOfBirth("1")
             .withMonthOfBirth("January")
             .withYearOfBirth("1987")
             .withPhoto(photo);

    app.contact().create(newContact);
    app.contact().returnToHomePage();
    assertThat(app.contact().count(), equalTo(beforeNewContactAdded.size() + 1));
    Contacts afterNewContactAdded = app.contact().all();
    assertThat(afterNewContactAdded, equalTo(beforeNewContactAdded.withAdded(newContact.withId(afterNewContactAdded.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  //Test to identify path to file and existence of the file
  @Test(enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/Mila.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
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
            .withEmail("email1@yandex.ru")
            .withEmail2("email2@yandex.ru")
            .withEmail3("email3@yandex.ru")
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
    File photo = new File("src/test/resources/edited.jpg");
    ContactData modifiedContact = new ContactData()
            .withId(randomEditedContact.getId())
            .withFirstName("Gena")
            .withMiddleName("Lg.")
            .withLastName("Olennikof")
            .withNickName("ohne")
            .withCompanyName("mzilla")
           // .withAddress("MinskerStreet, 25")
            .withHomePhone("888777")
            .withMobilePhone("888888")
            .withWorkPhone("888999")
            .withEmail("email1@yandex.ru")
            .withEmail2("email2@yandex.ru")
            .withEmail3("email3@yandex.ru")
            .withDayOfBirth("3")
            .withMonthOfBirth("March")
            .withYearOfBirth("1997")
            .withPhoto(photo);;


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
    ContactData selectContactForModification = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(selectContactForModification);

    assertThat(selectContactForModification.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

  }

  private String mergePhones(ContactData contactInfoFromEditForm) {
    return Arrays.asList(contactInfoFromEditForm.getHomePhone(), contactInfoFromEditForm.getMobilePhone(),contactInfoFromEditForm.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){ return phone.replaceAll("\\s", "").replaceAll("[-()]", ""); }


  @Test(enabled = false)
  public void testPostAddress(){
    ContactData selectContactForModification = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(selectContactForModification);
    assertThat(selectContactForModification.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  @Test(enabled = false)
  public void testEMails(){
    ContactData selectContactForModification = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(selectContactForModification);
    assertThat(selectContactForModification.getAllEMails(), equalTo(mergeEMails(contactInfoFromEditForm)));
  }
  private String mergeEMails(ContactData contactInfoFromEditForm) {
    return Arrays.asList(contactInfoFromEditForm.getEmail(), contactInfoFromEditForm.getEmail2(),contactInfoFromEditForm.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactTests::cleaned)
            .collect(Collectors.joining("\n"));
  }
}
