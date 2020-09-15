package tier3;

import shared.Account;
import shared.User;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Tier3Server extends Remote
{
  void startServer() throws RemoteException, AlreadyBoundException;
  List<Account> getAccounts(int ownerID) throws RemoteException;
  User login(int ownerID, String password) throws RemoteException;
  double getBalance(int accountID) throws RemoteException;
  boolean withdraw(double amount, int accountID) throws RemoteException;
}
