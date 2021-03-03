package ua.sampsa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions_atLeastOneGroupIsPresent(){
    app.goTo().groupPage();
    if(app.group().list().size() == 0){
      app.group().create(new GroupData().withName("newGroupsName").withHeader( "newGroupsHeader").withFooter( "newGroupsFooter"));
    }
  }

  @Test(enabled = true)
  public void testGroupCreation() throws Exception {
    List<GroupData> before = app.group().list();
    GroupData newGroup = new GroupData().withName("newGroupsName").withHeader( "newGroupsHeader").withFooter( "newGroupsFooter");
    int index = before.size() + 1;
    app.group().create(newGroup);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), index);

    newGroup.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); // Find in afterList max Id (which means new Group) and set it to beforeList
    before.add(newGroup);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

  @Test(enabled = true)
  public void testGroupModification(){
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData modifiedGroup = new GroupData()
            .withId(before.get(index).getId()).withName("GroupRename").withHeader("HeaderRename").withFooter("FooterRename");
    app.group().modify(index, modifiedGroup);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index); // remove the element, that was modified in the test to make List 'before' the same as we have before this test started
    before.add(modifiedGroup); // what we EXPECT
    Comparator<? super GroupData> byId = (group1, group2) -> Integer.compare(group1.getId(), group2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after); //compare sorted by id Lists
  }

  @Test(enabled = true)
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index); // remove the same element from old list (before our deletion) to make lists equal, because list 'after' is smaller
      Assert.assertEquals(before, after); // compare lists
  }
}