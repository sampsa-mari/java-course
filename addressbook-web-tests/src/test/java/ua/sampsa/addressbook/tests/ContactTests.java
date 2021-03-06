package ua.sampsa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.ContactData;
import ua.sampsa.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions_atLeastOneContactIsPresent(){
    if(app.db().contacts().size() == 0){
      app.goTo().addNewPage();
      File photo = new File("src/test/resources/dataFiles/Mila.png");
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

  @DataProvider
  public Iterator<Object[]> newValidContactsFromCSV() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataFiles/contacts/contacts.csv")))){
      String line = reader.readLine();
      while (line !=null){
        String[] split = line.split(";");
        list.add(new Object[]{new ContactData().withFirstName(split[0]).withLastName(split[1]).withAddress(split[2])
                .withNickName(split[3]).withCompanyName(split[4]).withHomePhone(split[5])
                .withMobilePhone(split[6]).withWorkPhone(split[7]).withEmail(split[8])
                .withEmail2(split[9]).withEmail3(split[10]).withDayOfBirth(split[11]).withMonthOfBirth(split[12]).withYearOfBirth(split[13]).withPhoto(new File(split[14]))});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> newValidContactsFromXML() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataFiles/contacts/contacts.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line !=null){
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> newValidContactsFromJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataFiles/contacts/contacts.json")))){
      String json = "";
      String line = reader.readLine();
      while (line !=null){
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> invalidContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataFiles/contacts/invalid-contacts.csv")));
    String line = reader.readLine();
    while (line !=null){
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withFirstName(split[0]).withLastName(split[1]).withAddress(split[2])
              .withNickName(split[3]).withCompanyName(split[4]).withHomePhone(split[5])
              .withMobilePhone(split[6]).withWorkPhone(split[7]).withEmail(split[8])
              .withEmail2(split[9]).withEmail3(split[10]).withDayOfBirth(split[11]).withMonthOfBirth(split[12]).withYearOfBirth(split[13])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "newValidContactsFromXML")
  public void testNewContactWithPhotoXML(ContactData newContact) throws Exception {
    Contacts beforeNewContactAdded = app.db().contacts();
    app.goTo().addNewPage();
    app.contact().create(newContact);
    app.contact().returnToHomePage();
    assertThat(app.contact().count(), equalTo(beforeNewContactAdded.size() + 1));
    Contacts afterNewContactAdded = app.db().contacts();
    assertThat(afterNewContactAdded, equalTo(beforeNewContactAdded.withAdded(newContact.withId(afterNewContactAdded.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(dataProvider = "newValidContactsFromCSV")
  public void testNewContactWithPhotoCSV(ContactData newContact) throws Exception {
    Contacts beforeNewContactAdded = app.db().contacts();
    app.goTo().addNewPage();
    app.contact().create(newContact);
    app.contact().returnToHomePage();
    assertThat(app.contact().count(), equalTo(beforeNewContactAdded.size() + 1));
    Contacts afterNewContactAdded = app.db().contacts();
    assertThat(afterNewContactAdded, equalTo(beforeNewContactAdded.withAdded(newContact.withId(afterNewContactAdded.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
  @Test(dataProvider = "newValidContactsFromJSON")
  public void testNewContactWithoutPhotoJSON(ContactData newContact) throws Exception {
    Contacts beforeNewContactAdded = app.db().contacts();
    app.goTo().addNewPage();
    app.contact().create(newContact);
    app.contact().returnToHomePage();
    assertThat(app.contact().count(), equalTo(beforeNewContactAdded.size() + 1));
    Contacts afterNewContactAdded = app.db().contacts();
    assertThat(afterNewContactAdded, equalTo(beforeNewContactAdded.withAdded(newContact.withId(afterNewContactAdded.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = true)
  public void testNewContactWithPhoto() throws Exception {
    Contacts beforeNewContactAdded = app.db().contacts();
    app.goTo().addNewPage();
    File photo = new File("src/test/resources/dataFiles/Mila.png");
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
    Contacts afterNewContactAdded = app.db().contacts();
    assertThat(afterNewContactAdded, equalTo(beforeNewContactAdded.withAdded(newContact.withId(afterNewContactAdded.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

//  Test to identify path to file and existence of the file

//  @Test(enabled = true)
//  public void testCurrentDir(){
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File("src/test/resources/files/Mila.png");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }

  @Test(dataProvider = "invalidContacts")
  public void testBadNameContact(ContactData newContact) throws Exception {
    Contacts beforeNewContactAdded = app.db().contacts();
    app.goTo().addNewPage();
    app.contact().create(newContact);
    app.contact().returnToHomePage();
    assertThat(app.contact().count(), equalTo(beforeNewContactAdded.size()));
    Contacts afterNewContactAdded = app.db().contacts();
    assertThat(afterNewContactAdded, equalTo(beforeNewContactAdded));
  }

  @Test(enabled = true)
  public void testEditContact(){
    Contacts beforeContactModification = app.db().contacts();
    ContactData randomEditedContact = beforeContactModification.iterator().next(); // select from Set random contact that will be modified. 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    File photo = new File("src/test/resources/dataFiles/edited.jpg");
    ContactData modifiedContact = new ContactData()
            .withId(randomEditedContact.getId())
            .withFirstName("Gena")
            .withMiddleName("Lg.")
            .withLastName("Olennikof")
            .withNickName("ohne")
            .withCompanyName("mzilla")
            .withAddress("MinskerStreet, 25")
            .withHomePhone("888777")
            .withMobilePhone("888888")
            .withWorkPhone("888999")
            .withEmail("email1@yandex.ru")
            .withEmail2("email2@yandex.ru")
            .withEmail3("email3@yandex.ru")
            .withDayOfBirth("3")
            .withMonthOfBirth("March")
            .withYearOfBirth("1997")
            .withPhoto(photo);
    app.contact().modifyById(modifiedContact);
    assertThat(app.contact().count(), equalTo(beforeContactModification.size()));
    Contacts afterContactModification = app.db().contacts();
    assertThat(afterContactModification, equalTo(beforeContactModification.without(randomEditedContact).withAdded(modifiedContact)));
  }

  @Test(enabled = true)
  public void testDeleteContact(){
    Contacts beforeDeletion = app.db().contacts();
    ContactData randomDeletedContact = beforeDeletion.iterator().next(); // 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    app.contact().deleteById(randomDeletedContact);
    assertThat(app.contact().count(), equalTo(beforeDeletion.size() - 1));
    Contacts afterDeletion = app.db().contacts();
    assertThat(afterDeletion, equalTo(beforeDeletion.without(randomDeletedContact)));
  }

  @Test(enabled = true)
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


  @Test(enabled = true)
  public void testPostAddress(){
    ContactData selectContactForModification = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(selectContactForModification);
    assertThat(selectContactForModification.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  @Test(enabled = true)
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
