package ua.sampsa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;
import ua.sampsa.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions_atLeastOneGroupIsPresent(){
    app.goTo().groupPage();
    if(app.group().all().size() == 0){
      app.group().create(new GroupData().withName("newGroupsName").withHeader( "newGroupsHeader").withFooter( "newGroupsFooter"));
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line !=null){
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter( split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }
  @DataProvider
  public Iterator<Object[]> invalidGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/invalid-groups.csv")));
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
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/edited-groups.csv")));
    String line = reader.readLine();
    while (line !=null){
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter( split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }


  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData newGroup) throws Exception {
    Groups before = app.group().all();
    app.group().create(newGroup);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(newGroup.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));  // get Set 'after' with all Id`s, make stream from it, create stream of Id`s (mapToInt) and search max value
    }

  @Test(dataProvider = "invalidGroups")
  public void testBadNameGroupCreation(GroupData newGroup) throws Exception {
    Groups before = app.group().all();
    app.group().create(newGroup);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }

  @Test//(dataProvider = "editedGroups")
  public void testGroupModification(){
    Groups before = app.group().all();
    GroupData randomEditedGroup = before.iterator().next(); // select from Set random group that will be modified. 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    GroupData modifiedGroup = new GroupData().withId(randomEditedGroup.getId()).withName("GroupRename").withHeader("HeaderRename").withFooter("FooterRename"); // take id from object above
    app.group().modify(modifiedGroup);
    assertThat(app.group().count(), equalTo( before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo (before.without(randomEditedGroup).withAdded(modifiedGroup)));
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.group().all();
    GroupData randomDeletedGroup = before.iterator().next();
    app.group().delete(randomDeletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();
    assertThat(after, equalTo (before.without(randomDeletedGroup)));
  }
}