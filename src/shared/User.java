package shared;

import tier1.Tier1Client;

import java.io.Serializable;

public class User implements Serializable
{
  private int ssn;
  private int userID;
  private String name;
  private Type type;
  private String bank;
  private Tier1Client client;

  public User(int ssn, int userID, String name, Type type)
  {
    this.ssn = ssn;
    this.userID = userID;
    this.name = name;
    this.type = type;
  }

  public int getSsn()
  {
    return ssn;
  }

  public int getUserID()
  {
    return userID;
  }

  public String getName()
  {
    return name;
  }

  public Type getType()
  {
    return type;
  }

  public String getBank()
  {
    return bank;
  }

  public void setBank(String bank)
  {
    this.bank = bank;
  }

  public Tier1Client getClient() {
    return client;
  }

  public void setClient(Tier1Client client) {
    this.client = client;
  }
}
