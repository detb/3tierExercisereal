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
        ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Bank\".Accounts WHERE custmerID = "
                + ownerID + "';");
        List<Account> accounts = new ArrayList<>();
        while ( rs.next() ) {
            int accountID = rs.getInt("accountID");
            String accountName = rs.getString("accountName");
            Account account = new Account(accountID, accountName, ownerID);
            accounts.add(account);
        }
        DBConn.closeStatement();
        rs.close();
        return accounts;
    }
}
