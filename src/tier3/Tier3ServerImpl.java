package tier3;

import shared.Account;
import shared.User;
import shared.Bank;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tier3ServerImpl implements Tier3Server
{
  private DatabaseConnection DBConn;
  private DAOAccount accountData;
  private ArrayList<Bank> banks;
  public Tier3ServerImpl()
  {
    this.DBConn = new DatabaseConnection();
    banks = new ArrayList<>();
  }

  public void startServer()
  {
    try
    {
      UnicastRemoteObject.exportObject(this,0);
      Registry registry = LocateRegistry.createRegistry(1099);
      registry.bind("BankTier3Server", this);
      DBConn.startDB();
      System.out.println("Server started.");
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    catch (AlreadyBoundException e)
    {
      e.printStackTrace();
    }

  }

  @Override public List<Account> getAccounts(int ownerID)
  {
    if (accountData == null)
    {
      accountData = new DAOAccount(DBConn);
    }
    try
    {
      List<Account> accounts = accountData.getAccounts(ownerID);
      return accounts;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    } return null;
  }

  @Override public User login(int ownerID, String password)
  {
    if (accountData == null)
    {
      accountData = new DAOAccount(DBConn);
    }
    try
    {
      return accountData.login(ownerID,password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public double getBalance(int accountID) throws RemoteException
  {
    return 0;
  }

  @Override public boolean withdraw(double amount, int accountID)
      throws RemoteException
  {
    if (accountData == null)
    {
      accountData = new DAOAccount(DBConn);
    }
    try
    {
      if (accountData.withdraw(accountID, amount))
        return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public void connect(Bank bank) throws RemoteException
  {
    banks.add(bank);
    System.out.println(bank.getName() + ", successfully connected with ID: " + bank.getPort());
  }

  @Override public boolean createAccount(String userID, String name)
      throws RemoteException
      {
        if (accountData == null)
      {
        accountData = new DAOAccount(DBConn);
      }
      try
      {
        if (accountData.createAccount(userID, name))
          return true;
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      return false;
  }
}
