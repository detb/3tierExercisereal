package tier3;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Tier3ServerImpl implements Tier3Server
{
  private DatabaseConnection DBConn;
  public Tier3ServerImpl()
  {
    this.DBConn = new DatabaseConnection();
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    UnicastRemoteObject.exportObject(this,0);
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("BankTier3Server", this);
    DBConn.startDB();
    System.out.println("Server started.");
  }
}
