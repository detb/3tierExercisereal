package tier2;

import shared.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Tier2Server extends Remote
{
    List<Account> login(int ownerID, String password) throws RemoteException;

}
