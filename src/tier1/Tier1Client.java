package tier1;

import shared.Account;
import shared.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Tier1Client extends Remote
{
  boolean withdraw(int accountID, int amount) throws RemoteException;
  User getUser() throws RemoteException;
  List<Account> getAccounts() throws RemoteException;
  boolean createAccount(String userID, String name) throws RemoteException;
}
