package ua.sampsa.addressbook.tests;

import org.testng.annotations.Test;
import ua.sampsa.addressbook.model.GroupData;
import ua.sampsa.addressbook.model.Groups;

import java.sql.*;

/**
 * Created by Maryna Tkachuk on 18.03.21.
 */
public class DbConnectionTests {

  @Test
  public void testDbConnection(){
    Connection conn = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select * from group_list");
      Groups groups = new Groups();
      while (rs.next()){
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      rs.close();
      st.close();
      conn.close();

      System.out.println(groups);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}