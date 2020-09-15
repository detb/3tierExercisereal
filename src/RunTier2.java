import tier2.Bank;
import tier2.Tier2ClientImpl;
import tier2.Tier2ServerImpl;

public class RunTier2
{
  public static void main(String[] args)
  {
    Tier2ServerImpl server = new Tier2ServerImpl();
    Tier2ClientImpl client = new Tier2ClientImpl();
    Bank bank1 = new Bank("First and best bank", 1098);
    server.startServer(bank1);
    client.startClient();

  }
}
