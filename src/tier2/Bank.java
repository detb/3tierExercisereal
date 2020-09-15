package tier2;

import tier1.Tier1Client;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private int port;
    private String name;
    private List<Tier1Client> clients;

    public Bank(String name, int port) {
        this.name = name;
        this.port = port;
        this.clients = new ArrayList<Tier1Client>();
    }

    public void addClient(Tier1Client client)
    {
        this.clients.add(client);
    }

    public void removeClient(Tier1Client client)
    {
        this.clients.remove(client);
    }

    public String getName() {
        return name;
    }

    public List<Tier1Client> getClients() {
        return clients;
    }

    public int getPort() {
        return port;
    }
}
