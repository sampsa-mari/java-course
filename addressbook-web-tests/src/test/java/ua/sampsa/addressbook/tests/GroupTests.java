package ua.sampsa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;
import ua.sampsa.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions_atLeastOneGroupIsPresent(){
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("newGroupsName").withHeader( "newGroupsHeader").withFooter( "newGroupsFooter"));
    }
  }

  @DataProvider
  public Iterator<Object[]> newValidGroupsFromCSV() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataFiles/groups/groups.csv")))){
      String line = reader.readLine();
      while (line !=null){
        String[] split = line.split(";");
        list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter( split[2])});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> newValidGroupsFromXML() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataFiles/groups/groups.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line !=null){
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> newValidGroupsFromJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataFiles/groups/groups.json")))){
      String json = "";
      String line = reader.readLine();
      while (line !=null){
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); //which means like - List<GroupData>.class
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> invalidGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataFiles/groups/invalid-groups.csv")));
    String line = reader.readLine();
    while (line !=null){
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter( split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> editedGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataFiles/groups/edited-groups.csv")));
    String line = reader.readLine();
    while (line !=null){
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter( split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "newValidGroupsFromCSV")
  public void testGroupCreation(GroupData newGroup) throws Exception {
    Groups before = app.db().groups();
    app.goTo().groupPage();
    app.group().create(newGroup);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.withAdded(newGroup.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));  // get Set 'after' with all Id`s, make stream from it, create stream of Id`s (mapToInt) and search max value
  }

  @Test(dataProvider = "invalidGroups")
  public void testBadNameGroupCreation(GroupData newGroup) throws Exception {
    Groups before = app.db().groups();
    app.goTo().groupPage();
    app.group().create(newGroup);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before));
  }

  @Test//(dataProvider = "editedGroups")
  public void testGroupModification(){
    Groups before = app.db().groups();
    GroupData randomEditedGroup = before.iterator().next(); // select from Set random group that will be modified. 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    GroupData modifiedGroup = new GroupData().withId(randomEditedGroup.getId()).withName("GroupRename").withHeader("HeaderRename").withFooter("FooterRename"); // take id from object above
    app.goTo().groupPage();
    app.group().modify(modifiedGroup);
    assertThat(app.group().count(), equalTo( before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo (before.without(randomEditedGroup).withAdded(modifiedGroup)));
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    GroupData randomDeletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(randomDeletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo (before.without(randomDeletedGroup)));
  }
}