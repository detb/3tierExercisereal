package tier2;

import tier3.Tier3Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Tier2ServerImpl implements Tier2Server
{



    public void startServer(Bank bank)
    {
        try
        {
            UnicastRemoteObject.exportObject(this,0);
            Registry registry = LocateRegistry.createRegistry(bank.getPort());
            registry.bind(bank.getName(), this);
            System.out.println("Tier 2 Server for: " + bank.getName());

        }
        catch (RemoteException | AlreadyBoundException e)
        {
            e.printStackTrace();
        }
    }
}
