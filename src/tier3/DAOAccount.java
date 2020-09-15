package tier3;

import shared.Account;

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

    public boolean login(int ownerID, String password) throws SQLException
    {
      ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Tier3Bank\".Users WHERE userID = '"
          + ownerID + "' AND password = '" + password + "';");
      while ( rs.next() )
      {
        rs.getString("password");
        DBConn.closeStatement();
        rs.close();
        return true;
      }
      DBConn.closeStatement();
      rs.close();
      return false;
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
