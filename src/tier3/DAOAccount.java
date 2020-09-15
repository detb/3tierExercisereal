package tier3;

import shared.Account;
import shared.Type;
import shared.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOAccount
{
  private DatabaseConnection DBConn;

  public DAOAccount(DatabaseConnection DBConn) {
    this.DBConn = DBConn;
  }

    public List<Account> getAccounts(int ownerID) throws SQLException {
        ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Tier3Bank\".Accounts WHERE userID = '"
                + ownerID + "';");
        List<Account> accounts = new ArrayList<>();
        while ( rs.next() ) {
            int accountID = rs.getInt("accountID");
            String accountName = rs.getString("accountName");
            int saldo = rs.getInt("saldo");
            Account account = new Account(accountID, accountName, ownerID, saldo);
            accounts.add(account);
        }
        DBConn.closeStatement();
        rs.close();
        return accounts;
    }

    public User login(int ownerID, String password) throws SQLException
    {
      ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Tier3Bank\".Users WHERE userID = '"
          + ownerID + "' AND password = '" + password + "';");
      while ( rs.next() )
      {
        rs.getString("password");
        int ssn = rs.getInt("ssn");
        String name = rs.getString("name");
        String isAss = rs.getString("isAssistant");
        String isAdm = rs.getString("isAdmin");
        Type type;
        if (isAss.equals("1"))
          type = Type.ASSISTANT;
        else if (isAdm.equals("1"))
          type = Type.ADMIN;
        else
          type = Type.USER;
        DBConn.closeStatement();
        rs.close();
        return new User(ssn, ownerID, name, type);
      }
      DBConn.closeStatement();
      rs.close();
      return null;
    }

    public boolean withdraw(int accountID, double amount)  throws SQLException{
      ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Tier3Bank\".Accounts WHERE accountID = '"
          + accountID + "' AND saldo >= '" + amount + "';");
      while ( rs.next() )
      {
        double saldo = rs.getDouble("saldo");
        DBConn.closeStatement();
        rs.close();
        System.out.println("Set saldo to: " + (saldo - amount));
        DBConn.updateData("UPDATE \"Tier3Bank\".Accounts SET saldo = " + (saldo - amount) + " where accountID = " + accountID + ";");
        return true;
      }
      DBConn.closeStatement();
      rs.close();
      return false;
    }

}
