package shared;

import java.io.Serializable;

public class User implements Serializable
{
  private int ssn;
  private int userID;
  private String name;
  private Type type;

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
}
