package ua.sampsa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ua.sampsa.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maryna Tkachuk on 17.03.21.
 */
public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contact = generateContacts(count);
    if (format.equals("csv")){
      saveAsCsv(contact, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(contact, new File(file));
    } else if (format.equals("json")){
      saveAsJson(contact, new File(file));
    }else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<ContactData> contact, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contact);
    try (Writer writer = new FileWriter(file)){
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getNickName(), contact.getCompanyName(),
                contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                contact.getDayOfBirth(), contact.getMonthOfBirth(), contact.getYearOfBirth()));
      }
    }
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++){
      contacts.add(new ContactData()
              .withFirstName(String.format("CSVFirstName %s", i))
              .withLastName(String.format("CSVLastName %s", i))
              .withAddress(String.format("Address %s", i))
              .withNickName(String.format("Rock-n-Roll"))
              .withCompanyName(String.format("Yandex"))
              .withHomePhone(String.format("444%s", i))
              .withMobilePhone(String.format("555%s", i))
              .withWorkPhone(String.format("666%s", i))
              .withEmail(String.format("email1@yandex.ru"))
              .withEmail2(String.format("email2@yandex.ru"))
              .withEmail3(String.format("email3@yandex.ru"))
              .withDayOfBirth(String.format("1"))
              .withMonthOfBirth(String.format("January"))
              .withYearOfBirth(String.format("1984")));
    }
    return contacts;
  }
}
