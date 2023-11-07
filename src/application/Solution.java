package application;

import java.sql.Connection;
import db.DB;
public class Solution {
  public static void main(String[] args) {

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("Driver loaded!");
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("Cannot find the driver in the classpath!", e);
    }
    Connection connection = DB.getConnection();
    DB.closeConnection();
  }
}
