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
    System.out.println("Your accounts: ");
    for (int i = 0; i < accounts.size(); i++)
    {
      System.out.println(i+1 + ") " + accounts.get(i).getName() + ":    " + accounts.get(i).getBalance());
    }
    System.out.println("Which account would you like to withdraw from?");
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
}
