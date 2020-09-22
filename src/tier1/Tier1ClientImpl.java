package tier1;

import shared.Account;
import shared.User;
import tier2.Tier2Server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

public class Tier1ClientImpl implements Tier1Client{

    private Tier2Server tier2Server;
    private Tier1ViewController tier1ViewController;
    private User user;
    private PropertyChangeSupport support;

    public Tier1ClientImpl(){
        support = new PropertyChangeSupport(this);
    }

    public boolean connectClient(int port)
    {
        try
        {
            UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry("localhost", port);
            tier2Server = (Tier2Server) registry.lookup("BankTier2Server" + port);
            System.out.println("Tier 1 client connected to tier 2 server.");
            return true;
        }
        catch (RemoteException | NotBoundException e)
        {
            System.out.println("Could not connect to server");
            e.printStackTrace();
        }
        return false;
    }
    public void startClient() throws RemoteException
    {
        int id, un;
        String pass;
        Scanner in = new Scanner(System.in);
        do
        {
            System.out.println("Enter bank id:");
            id = in.nextInt();
            in.nextLine();
        }while(!connectClient(id));
        do
        {
            System.out.println("Enter userID:");
            un = in.nextInt();
            in.nextLine();
            System.out.println("Enter password:");
            pass = in.nextLine();
            user = tier2Server.login(un,pass);
        }
        while(user == null);
        System.out.println("Login success..");
        tier1ViewController = new Tier1ViewController(tier2Server.getAccounts(un), this);
    }

    @Override public boolean withdraw(int accountID, int amount)
        throws RemoteException
    {
        return tier2Server.withdraw(accountID, amount);
    }

    @Override public User getUser()
    {
        return user;
    }

    @Override public List<Account> getAccounts(int userID) throws RemoteException
    {
        return tier2Server.getAccounts(userID);
    }

    @Override public boolean createAccount(String userID, String name)
        throws RemoteException
    {
        return tier2Server.createAccount(userID, name);
    }

    @Override
    public boolean deposit(int accountID, int amount) throws RemoteException {
        return tier2Server.deposit(accountID, amount);
    }

    @Override public void addListener(String eventName,
                                      PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override public void removeListener(String eventName,
                                         PropertyChangeListener listener)
    {
        support.removePropertyChangeListener(eventName, listener);
    }
}
