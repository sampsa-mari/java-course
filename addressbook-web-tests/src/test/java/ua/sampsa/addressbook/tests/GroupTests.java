package ua.sampsa.addressbook.tests;

import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;


public class GroupTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("newGroupsName", "newGroupsHeader", "newGroupsFooter"));
  }

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("newGroupsName", "newGroupsHeader", "newGroupsFooter"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("GroupRename", "HeaderRename", "FooterRename"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("newGroupsName", "newGroupsHeader", "newGroupsFooter"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }
}
