package ua.sampsa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;

import java.util.Set;


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
    Set<GroupData> before = app.group().all();
    GroupData newGroup = new GroupData().withName("newGroupsName").withHeader( "newGroupsHeader").withFooter( "newGroupsFooter");
    int index = before.size() + 1;
    app.group().create(newGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), index);

    newGroup.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()); // get Set 'after' with all Id`s, make stream from it, create stream of Id`s (mapToInt) and search max value
    before.add(newGroup);
    Assert.assertEquals(before, after);
  }

  @Test(enabled = true)
  public void testGroupModification(){
    Set<GroupData> before = app.group().all();  // Create Set of existing groups
    GroupData randomEditedGroup = before.iterator().next(); // select from Set random group that will be modified. 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    GroupData modifiedGroup = new GroupData().withId(randomEditedGroup.getId()).withName("GroupRename").withHeader("HeaderRename").withFooter("FooterRename"); // take id from object above
    app.group().modify(modifiedGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(randomEditedGroup); // remove the element, that was modified in the test to make Set 'before' the same as we have before this test started
    before.add(modifiedGroup); // what we EXPECT
    Assert.assertEquals(before,after); //compare sorted by id Lists
  }

  @Test(enabled = true)
  public void testGroupDeletion() throws Exception {
    Set<GroupData> before = app.group().all();
    GroupData randomDeletedGroup = before.iterator().next(); // 'iterator()' iterates over the elements in Set sequentially and 'next()' returns random element from Set
    app.group().delete(randomDeletedGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(randomDeletedGroup); // remove the same element from old list (before our deletion) to make lists equal, because list 'after' is smaller
    Assert.assertEquals(before, after); // compare lists
  }
}