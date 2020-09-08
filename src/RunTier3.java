import tier3.Tier3ServerImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunTier3
{
  public static void main(String[] args)
  {
    Tier3ServerImpl server = new Tier3ServerImpl();
    server.startServer();
  }
}
