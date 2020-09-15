package tier1;

import tier2.Tier2Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Tier1ClientImpl implements Tier1Client{

    private Tier2Server tier2Server;

    public void startClient()
    {
        try
        {
            UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry("localhost", 1098);
            tier2Server = (Tier2Server) registry.lookup("BankTier2Server");
            System.out.println("Tier 1 client connected to tier 2 server.");
        }
        catch (RemoteException | NotBoundException e)
        {
            System.out.println("Could not connect to server");
            e.printStackTrace();
        }
    }
}
