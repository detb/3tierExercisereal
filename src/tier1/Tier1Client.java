package tier1;

import shared.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Tier1Client extends Remote
{
  boolean withdraw(int accountID, int amount) throws RemoteException;
  User getUser() throws RemoteException;
}
