package tier2;

import shared.Account;
import shared.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Tier2Server extends Remote
{
  User login(int ownerID, String password) throws RemoteException;
  boolean withdraw(int ownerID, int accountID, int amount) throws RemoteException;
  List<Account> getAccounts(int un) throws RemoteException;
  List<Account> getAccounts(User user) throws RemoteException;
  boolean createAccount(String userID, String name) throws RemoteException;
  boolean deposit(int accountID, int amount) throws RemoteException;
}
