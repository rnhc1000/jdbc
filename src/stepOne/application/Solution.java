package stepOne.application;



import stepOne.db.DB;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Solution {
  public static void main(String[] args) {

    Connection connection = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      connection = DB.getConnection();
      st = connection.createStatement();
      rs = st.executeQuery("select * from department");
      while (rs.next()) {
        System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      DB.closeResultSet(rs);
      DB.closeStatement(st);
      DB.closeConnection();
    }
  }
}
