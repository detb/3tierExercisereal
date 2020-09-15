package shared;

import tier1.Tier1Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable
{
    private int port;
    private String name;

    public Bank(String name, int port) {
        this.name = name;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }
}
