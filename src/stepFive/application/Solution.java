package stepFive.application;

import stepTwo.db.DB;
import stepTwo.db.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Transactions ACID compliant
 * Java API
 * -> setAutoCommit(false)
 * -> commit()
 * -> rollback()
 * To simulate, inject a Fake SQLException thru the code
 *  int x = 1;
 *  if (x < 2) {
 *      throw new SQLException("Fake error");
 *  }
 *
 */
public class Solution {
  public static void main(String[] args) {

    Connection connection = null;
    Statement st = null;
    try {
      connection = DB.getConnection();

      connection.setAutoCommit(false);

      st = connection.createStatement();

      int rowsOne = st.executeUpdate(
              "UPDATE seller "
              + "SET BaseSalary = 1090.00 "
              + "WHERE DepartmentId = 1"
      );

      int rowsTwo = st.executeUpdate(
              "UPDATE seller "
              + "SET BaseSalary = 2090.00 "
              + "WHERE DepartmentId = 2"
      );
      int rowsThree = st.executeUpdate(
              "UPDATE seller "
              + "SET BaseSalary = 3090.00 "
              + "WHERE DepartmentId = 3"
      );

//      int x = 1;
//      if (x < 2) {
//        throw new SQLException("Fake error");
//      }

      int rowsFour = st.executeUpdate(
              "UPDATE seller "
              + "SET BaseSalary = 4090.00 "
              + "WHERE DepartmentId = 4"
      );
      connection.commit();

      System.out.println("Departmend Id 1 - Rows Affected: " + rowsOne);
      System.out.println("Departmend Id 2 - Rows Affected: " + rowsTwo);
      System.out.println("Departmend Id 3 - Rows Affected: " + rowsThree);
      System.out.println("Departmend Id 4 - Rows Affected: " + rowsFour);

    } catch (SQLException ex) {
      try {
        connection.rollback();
        throw new DbException("Transaction rolled back! Caused by: " + ex.getMessage());
      } catch (SQLException e) {
        throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
      }
    } finally {
      DB.closeStatement(st);
      DB.closeConnection();
    }

  }
}
