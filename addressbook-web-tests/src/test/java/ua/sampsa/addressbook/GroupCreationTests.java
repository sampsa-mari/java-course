package ua.sampsa.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("newGroup", "newHeader", "newFooter"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
