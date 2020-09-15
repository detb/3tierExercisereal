package tier2;

import shared.Account;
import tier3.Tier3Server;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Tier2ServerImpl implements Tier2Server
{
    private Tier3Server tier3Server;

    public void startClient()
    {
        try
        {
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

    public void startServer(Bank bank)
    {
        try
        {
            String name = "BankTier2Server" + bank.getPort();

            UnicastRemoteObject.exportObject(this,0);
            Registry registry = LocateRegistry.createRegistry(bank.getPort());
            registry.bind(name, this);
            System.out.println("Tier 2 Server for: " + bank.getName());

        }
        catch (RemoteException | AlreadyBoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> login(int ownerID, String password){
        try {
            return tier3Server.login(ownerID, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
