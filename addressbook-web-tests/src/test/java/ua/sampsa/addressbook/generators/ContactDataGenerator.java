package ua.sampsa.addressbook.generators;

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
  public static void main(String[] args) throws IOException {

    //Number of Groups
    int count = Integer.parseInt(args[0]);

    //Path to file
    File file = new File(args[1]);

    List<ContactData> contact = generateContacts(count);
    save(contact, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());

    Writer writer = new FileWriter(file);
    for(ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getAddress()));
    }
    writer.close();

  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++){
      contacts.add(new ContactData()
              .withFirstName(String.format("FirstName %s", i))
              .withLastName(String.format("LastName %s", i))
              .withAddress(String.format("Address %s", i)));
    }
    return contacts;
  }
}
