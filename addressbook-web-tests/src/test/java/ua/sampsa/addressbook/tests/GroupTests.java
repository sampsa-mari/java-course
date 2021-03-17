package ua.sampsa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;
import ua.sampsa.addressbook.model.Groups;

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
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new GroupData().withName("GroupName - 1").withHeader("Header - 1").withFooter("Footer - 1")});
    list.add(new Object[]{new GroupData().withName("GroupName - 2").withHeader("Header - 2").withFooter("Footer - 2")});
    list.add(new Object[]{new GroupData().withName("GroupName - 3").withHeader("Header - 3").withFooter("Footer - 3")});
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

  @Test(enabled = false)
  public void testBadNameGroupCreation() throws Exception {
    Groups before = app.group().all();
    GroupData newGroup = new GroupData().withName("newGroupsName'").withHeader( "newGroupsHeader").withFooter( "newGroupsFooter");
    app.group().create(newGroup);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }

  @Test(enabled = false)
  public void testGroupModification(){
    Groups before = app.group().all();
    GroupData randomEditedGroup = before.iterator().next(); // select from Set random group that will be modified. 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    GroupData modifiedGroup = new GroupData().withId(randomEditedGroup.getId()).withName("GroupRename").withHeader("HeaderRename").withFooter("FooterRename"); // take id from object above
    app.group().modify(modifiedGroup);
    assertThat(app.group().count(), equalTo( before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo (before.without(randomEditedGroup).withAdded(modifiedGroup)));
  }

  @Test(enabled = false)
  public void testGroupDeletion() throws Exception {
    Groups before = app.group().all();
    GroupData randomDeletedGroup = before.iterator().next();
    app.group().delete(randomDeletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();
    assertThat(after, equalTo (before.without(randomDeletedGroup)));
  }
}