package ua.sampsa.addressbook.generators;

import ua.sampsa.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maryna Tkachuk on 17.03.21.
 */
public class GroupDataGenerator {

  public static void main(String[] args) throws IOException {

    //Number of Groups
    int count = Integer.parseInt(args[0]);

    //Path to file
    File file = new File(args[1]);

    List<GroupData> groups = generateGroups(count);
    save(groups, file);
  }

  private static void save(List<GroupData> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());

    Writer writer = new FileWriter(file);
    for(GroupData group : groups){
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
    }
    writer.close();

  }

  private static List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++){
      groups.add(new GroupData().withName(String.format("GroupName %s", i))
              .withHeader(String.format("Header %s", i)).withFooter(String.format("Footer %s", i)));
    }
    return groups;
  }
}
