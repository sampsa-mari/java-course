package ua.sampsa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;
import ua.sampsa.addressbook.model.Groups;

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

  @Test(enabled = true)
  public void testGroupCreation() throws Exception {
    Groups before = app.group().all();
    GroupData newGroup = new GroupData().withName("newGroupsName").withHeader( "newGroupsHeader").withFooter( "newGroupsFooter");
    app.group().create(newGroup);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(newGroup.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));  // get Set 'after' with all Id`s, make stream from it, create stream of Id`s (mapToInt) and search max value
  }

  @Test(enabled = true)
  public void testBadNameGroupCreation() throws Exception {
    Groups before = app.group().all();
    GroupData newGroup = new GroupData().withName("newGroupsName'").withHeader( "newGroupsHeader").withFooter( "newGroupsFooter");
    app.group().create(newGroup);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }

  @Test(enabled = true)
  public void testGroupModification(){
    Groups before = app.group().all();
    GroupData randomEditedGroup = before.iterator().next(); // select from Set random group that will be modified. 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    GroupData modifiedGroup = new GroupData().withId(randomEditedGroup.getId()).withName("GroupRename").withHeader("HeaderRename").withFooter("FooterRename"); // take id from object above
    app.group().modify(modifiedGroup);
    assertThat(app.group().count(), equalTo( before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo (before.without(randomEditedGroup).withAdded(modifiedGroup)));
  }

  @Test(enabled = true)
  public void testGroupDeletion() throws Exception {
    Groups before = app.group().all();
    GroupData randomDeletedGroup = before.iterator().next();
    app.group().delete(randomDeletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();
    assertThat(after, equalTo (before.without(randomDeletedGroup)));
  }
}