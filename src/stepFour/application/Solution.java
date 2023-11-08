package stepFour.application;

import stepFour.db.DbIntegrityException;
import stepTwo.db.DB;

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
              "DELETE FROM department "
              + "WHERE "
              + "Id = ?"
      );

      st.setInt(1,2);

      int rowsAffected = st.executeUpdate();
      System.out.println("Done! Rows Affected: " + rowsAffected);

    } catch(SQLException ex) {
      throw new DbIntegrityException(ex.getMessage());
    } finally {
      DB.closeStatement(st);
      DB.closeConnection();
      System.out.println("Done!!!!");
    }

  }
}
