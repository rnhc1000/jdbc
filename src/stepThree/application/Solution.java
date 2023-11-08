package stepThree.application;

import stepOne.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Solution {
  public static void main(String[] args) {
    Connection connection = null;
    PreparedStatement st = null;
    try {
      connection = DB.getConnection();
      st = connection.prepareStatement(
              "UPDATE seller "
              + "SET BaseSalary = BaseSalary + ? "
              + "WHERE "
              + "(DepartmentId = ?)"
      );
      st.setDouble(1, 1000.00);
      st.setInt(2,4);
      int rowsAffected = st.executeUpdate();
      System.out.println("Done! Rows affected: " + rowsAffected);

    } catch(SQLException ex) {
      ex.printStackTrace();
    } finally {
      DB.closeStatement(st);
      DB.closeConnection();
    }
  }
}
