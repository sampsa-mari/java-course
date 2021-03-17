package ua.sampsa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
    save(contact, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());

    Writer writer = new FileWriter(file);
    for(ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
              contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getNickName(), contact.getCompanyName(),
              contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
              contact.getDayOfBirth(), contact.getMonthOfBirth(), contact.getYearOfBirth()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++){
      contacts.add(new ContactData()
              .withFirstName(String.format("FirstName %s", i))
              .withLastName(String.format("LastName %s", i))
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
