import tier1.Tier1ClientImpl;

public class RunTier1
{
  public static void main(String[] args)
  {
    Tier1ClientImpl tier1Client = new Tier1ClientImpl();
    tier1Client.startClient();
  }
}
