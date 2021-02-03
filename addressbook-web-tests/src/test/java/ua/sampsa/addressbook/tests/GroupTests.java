package ua.sampsa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;


public class GroupTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("newGroupsName", "newGroupsHeader", "newGroupsFooter"));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before +1);

  }

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("newGroupsName", "newGroupsHeader", "newGroupsFooter"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("GroupRename", "HeaderRename", "FooterRename"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);

  }

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("newGroupsName", "newGroupsHeader", "newGroupsFooter"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);
  }
}
