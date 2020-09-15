package shared;

import java.io.Serializable;

public class Account implements Serializable
{
    private int accountID;
    private String name;
    private int ownerID;

    public Account(int accountID, String name, int ownerID){
        this.accountID = accountID;
        this.name = name;
        this.ownerID = ownerID;
    }

    public int getAccountID() {
        return accountID;

    }

    public String getName() {
        return name;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public double getBalance(){
        return 0;
    }

    public void withdraw(double amount){

    }

    public void deposit(double amount){

    }
}
