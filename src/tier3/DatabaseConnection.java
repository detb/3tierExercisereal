package tier3;
import java.sql.*;

public class DatabaseConnection
{
  private Connection c = null;
  private Statement stmt = null;

  public void startDB()
  {
    c = null;
    try {
      Class.forName("org.postgresql.Driver");
      c = DriverManager
          .getConnection("jdbc:postgresql://localhost:5432/postgres",
              "postgres", "1234");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully.");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  public void closeDB()
  {
    try {
      c.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public void closeStatement()
  {
    try {
      stmt.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }


}
