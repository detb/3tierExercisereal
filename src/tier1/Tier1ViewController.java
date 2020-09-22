package tier1;

import shared.Account;
import shared.Type;
import shared.User;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tier1ViewController
{
  ArrayList<Account> accounts;
  Tier1Client client;
  User user;

  public Tier1ViewController(List<Account> accounts, Tier1Client client)
      throws RemoteException
  {
    this.accounts = new ArrayList<>();
    this.accounts = (ArrayList<Account>) accounts;
    this.client = client;
    this.user = client.getUser();
    if (user.getType() == Type.USER)
      showAccounts(Type.USER);
    else if (user.getType() == Type.ADMIN)
      createAccount();
    else if (user.getType() == Type.ASSISTANT)
    {
      showAccounts(Type.ASSISTANT);
    }
    else
      System.out.println(client.getUser().getType());
  }

  public void update(){
    try {
      showAccounts(user.getType());
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void createAccount()  throws RemoteException
  {
    String userID, name;
    do
    {
      Scanner in = new Scanner(System.in);
      System.out.println("Type userID for the user:");
      userID = in.nextLine();
      System.out.println("Type name for account: ");
      name = in.nextLine();
    }
    while(client.createAccount(userID, name));
    System.out.println("Success");
  }


  public void showAccounts(Type userclass) throws RemoteException
  {
    Scanner in = new Scanner(System.in);
    clearScreen();
    if (userclass == Type.USER)
      accounts = (ArrayList<Account>) client.getAccounts(client.getUser().getUserID());
    else if (userclass == Type.ASSISTANT)
    {
      System.out.println("Please enter userID: ");
      int userID = in.nextInt();
      in.nextLine();
      accounts = (ArrayList<Account>) client.getAccounts(userID);
    }

    int w = 36;
    System.out.println(client.getUser().getBank() + " says: ");
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
    if (userclass == Type.USER) {
      System.out.println(" ");
      System.out.println("Which account would you like to ");
      System.out.println("withdraw from?");

      withdraw(in.nextInt() - 1);
    }
    else if (userclass == Type.ASSISTANT)
    {
      int choice;
      do {
        System.out.println(" ");
        System.out.println("Would you like to: \n1) Deposit \n2) Withdraw");
        choice = in.nextInt();
        in.nextLine();
        if (choice == 1) {
          System.out.println(" ");
          System.out.println("Which account would you like to ");
          System.out.println("deposit to?");

          deposit(in.nextInt() - 1);

        } else if (choice == 2) {
          System.out.println(" ");
          System.out.println("Which account would you like to ");
          System.out.println("withdraw from?");

          withdraw(in.nextInt() - 1);
        }
      }
      while (choice != 1 && choice != 2);
    }
  }

  public void deposit(int i) throws RemoteException
  {
    System.out.println("Account total: " + accounts.get(i).getBalance());
    System.out.println("Amount to deposit:");
    Scanner in = new Scanner(System.in);
    int amount = in.nextInt();
    in.nextLine();
    if (client.deposit(accounts.get(i).getAccountID(), amount)){
      accounts.get(i).deposit(amount);
      System.out.println("Success");
    }
    else {
      System.out.println("Something went wrong!");
    }
    showAccounts(client.getUser().getType());
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
    showAccounts(client.getUser().getType());
  }
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
