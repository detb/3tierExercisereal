package tier1;

import tier2.Tier2Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Tier1ClientImpl implements Tier1Client{

    private Tier2Server tier2Server;

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
        }while(!connectClient(id));
        do
        {
            System.out.println("Enter userID:");
            un = in.nextInt();
            System.out.println("Enter password:");
            pass = in.nextLine();
        }
        while(tier2Server.login(un,pass) == null);
    }
}
