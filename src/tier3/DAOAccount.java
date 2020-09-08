package tier3;

import shared.Account;

import java.util.List;

public class DAOAccount
{
  private DatabaseConnection DBConn;

  public DAOAccount(DatabaseConnection DBConn) {
    this.DBConn = DBConn;
  }

  public List<Account> retrieveAccounts(int ownerID)
  {
    return null;
  }
}
