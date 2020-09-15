import shared.Bank;
import tier2.Tier2ServerImpl;

import java.util.Scanner;

public class RunTier2
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    System.out.println("Bank name: ");
    String name = in.nextLine();
    System.out.println("Port: ");
    String pin = in.nextLine();

    Tier2ServerImpl server = new Tier2ServerImpl();
    Bank bank1 = new Bank(name, Integer.parseInt(pin));
    server.startServer(bank1);
    server.startClient();
  }
}
