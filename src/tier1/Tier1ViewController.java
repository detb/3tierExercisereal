package tier1;

import shared.Account;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tier1ViewController
{
  ArrayList<Account> accounts;
  Tier1Client client;

  public Tier1ViewController(List<Account> accounts, Tier1Client client)
      throws RemoteException
  {
    this.accounts = new ArrayList<>();
    this.accounts = (ArrayList<Account>) accounts;
    this.client = client;
    showAccounts();
  }

  public void showAccounts() throws RemoteException
  {
    clearScreen();
    int w = 36;
    System.out.println("      ___       __   __         ___ ");
    System.out.println("|  | |__  |    /  ` /  \\  |\\/| |__  ");
    System.out.println("|/\\| |___ |___ \\__, \\__/  |  | |___ ");
    for (int i = 0; i < (w-client.getUser().getName().length())/2; i++)
    {
      System.out.print(" ");
    }
    System.out.println(client.getUser().getName());
    System.out.println("");
    System.out.println("          Your accounts: ");
    System.out.println("");
    System.out.println("   Name                    Balance");
    for (int i = 0; i < accounts.size(); i++)
    {
      String b = accounts.get(i).getBalance() + "";
      System.out.print(i+1 + ") " + accounts.get(i).getName() + ":");
      for (int j = 0; j < w - 6 - accounts.get(i).getName().length() - b.length(); j++)
      {
        System.out.print(" ");
      }
      System.out.println(b);
    }
    System.out.println(" ");
    System.out.println("Which account would you like to ");
    System.out.println("withdraw from?");
    Scanner in = new Scanner(System.in);
    withdraw(in.nextInt() - 1);
  }
  public void withdraw(int i) throws RemoteException
  {
    System.out.println("Account total: " + accounts.get(i).getBalance());
    System.out.println("Amount to withdraw:");
    Scanner in = new Scanner(System.in);
    int amount = in.nextInt();
    in.nextLine();
    if (client.withdraw(accounts.get(i).getAccountID(), amount)){
      accounts.get(i).withdraw(amount);
      System.out.println("Success");
    }
    else {
      System.out.println("Something went wrong!");
    }
    showAccounts();
  }
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
