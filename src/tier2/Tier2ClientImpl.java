package tier2;

import tier3.Tier3Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Tier2ClientImpl implements Tier2Client{

    private Tier3Server tier3Server;

    public void startClient()
    {
        try
        {
            UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            tier3Server = (Tier3Server) registry.lookup("BankTier3Server");
            System.out.println("Tier 2 client connected to tier 3 server.");
        }
        catch (RemoteException | NotBoundException e)
        {
            System.out.println("Could not connect to server");
            e.printStackTrace();
        }
    }
}
