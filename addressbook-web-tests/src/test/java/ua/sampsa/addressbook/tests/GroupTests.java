package ua.sampsa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();

    GroupData newGroup = new GroupData("newGroupsName", "newGroupsHeader", "newGroupsFooter");
    app.getGroupHelper().createGroup(newGroup);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    // Find in afterList max Id (which means new Group) and set it to beforeList
    newGroup.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(newGroup);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("newGroupsName", "newGroupsHeader", "newGroupsFooter"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1); //select the last group from the List
    app.getGroupHelper().initGroupModification();

    GroupData modifiedGroup = new GroupData(before.get(before.size() - 1).getId(),"GroupRename", "HeaderRename", "FooterRename");
    app.getGroupHelper().fillGroupForm(modifiedGroup);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1); // remove the element, that was modified in the test to make List 'before' the same as we have before this test started
    before.add(modifiedGroup); // what we EXPECT
    Comparator<? super GroupData> byId = (group1, group2) -> Integer.compare(group1.getId(), group2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after); //compare sorted by id Lists

  }

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("newGroupsName", "newGroupsHeader", "newGroupsFooter"));
    }

    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(before.size() - 1); //select the last group from the List
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1); // remove the same element from old list (before our deletion) to make lists equal, because list 'after' is smaller
      Assert.assertEquals(before, after); // compare lists
  }
}
