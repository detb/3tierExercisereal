package tier1;

import shared.User;
import tier2.Tier2Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Tier1ClientImpl implements Tier1Client{

    private Tier2Server tier2Server;
    private Tier1ViewController tier1ViewController;
    private User user;

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
}
