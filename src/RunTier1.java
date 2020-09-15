import tier1.Tier1ClientImpl;

import java.rmi.RemoteException;

public class RunTier1
{
  public static void main(String[] args) throws RemoteException
  {
    Tier1ClientImpl tier1Client = new Tier1ClientImpl();
    tier1Client.startClient();
  }
}
