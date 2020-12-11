package ua.sampsa.addressbook.tests;

import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;

/**
 * Created by Maryna Tkachuk on 11.12.20.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("newGroup", "newHeader", "newFooter"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}
