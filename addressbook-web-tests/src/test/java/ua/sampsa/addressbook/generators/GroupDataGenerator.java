package ua.sampsa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;


  public static void main(String[] args) throws IOException {
    //Creating the object of current class
    GroupDataGenerator generator = new GroupDataGenerator();

    //Creating the object JCommander
    JCommander jCommander= new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<GroupData> groups = generateGroups(count);
    save(groups, new File(file));
  }

  private void save(List<GroupData> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());

    Writer writer = new FileWriter(file);
    for(GroupData group : groups){
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
    }
    writer.close();
  }

  private List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++){
      groups.add(new GroupData().withName(String.format("EditedGroupName %s", i))
              .withHeader(String.format("EditedHeader %s", i)).withFooter(String.format("EditedFooter %s", i)));
    }
    return groups;
  }
}
