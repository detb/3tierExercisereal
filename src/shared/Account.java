package shared;

import java.io.Serializable;

public class Account implements Serializable
{
    private int accountID;
    private String name;
    private int ownerID;
    private double saldo;

    public Account(int accountID, String name, int ownerID, double saldo){
        this.accountID = accountID;
        this.name = name;
        this.ownerID = ownerID;
        this.saldo = saldo;
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
        return saldo;
    }

    public void withdraw(double amount){
        saldo -= amount;
    }

    public void deposit(double amount){
        saldo += amount;
    }
}
