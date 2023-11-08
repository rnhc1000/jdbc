package stepTwo.application;

import stepTwo.db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Solution {
  public static void main(String[] args) {
    LocalDate date = LocalDate.of(2023, 10, 27);
    SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
    Connection connection = null;
    PreparedStatement st = null;

    try {
      connection = DB.getConnection();
      st = connection.prepareStatement(
              "INSERT INTO seller "
              + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
              + "VALUES "
              + "(?, ?, ?, ?, ?)",
              Statement.RETURN_GENERATED_KEYS
      );

      st.setString(1, "Carl Brown");
      st.setString(2, "carlB@gmail.com");
      st.setDate(3, new java.sql.Date(sdf.parse("27/11/1985").getTime()));
      st.setDouble(4, 3000.00);
      st.setInt(5,4);

      int rowsAffected = st.executeUpdate();

      if (rowsAffected > 0) {
        ResultSet rs = st.getGeneratedKeys();
        while(rs.next()) {
          int id = rs.getInt(1);
          System.out.println("Done: " + id);
        }

      } else {
        System.out.println("No rows affected!");
      }
      System.out.println("Done! Rows affected: " + rowsAffected);
      System.out.println("-----------------------");
      st = connection.prepareStatement(
              "INSERT INTO department (Name) VALUES ('D1'), ('D2')"
      , Statement.RETURN_GENERATED_KEYS);
      rowsAffected = st.executeUpdate();

      if (rowsAffected > 0) {
        ResultSet rs = st.getGeneratedKeys();
        while(rs.next()) {
          int id = rs.getInt(1);
          System.out.println("Done: " + id);
        }

      } else {
        System.out.println("No rows affected!");
      }
      System.out.println("Done! Rows affected: " + rowsAffected);
    } catch(SQLException ex){
      ex.printStackTrace();
    } catch (ParseException e) {
      throw new RuntimeException(e);
    } finally {
      DB.closeStatement(st);
      DB.closeConnection();
    }

  }
}
