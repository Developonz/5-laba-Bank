package Data;

import DataTransmission.MyIOFile;

import java.util.ArrayList;

public class ClientsRepository implements Repository {
    private ArrayList<Client> clients;
    private String bank;

    public ClientsRepository(String bank) {
        clients = new ArrayList<>();
        this.bank = bank;
        this.uploadData();
    }

    @Override
    public void uploadData() {
        this.clients = MyIOFile.deserializeClients(bank);
    }

    @Override
    public void removeClient(Client client) {
        clients.set((int) client.getId() - 1, null);
        MyIOFile.clientsSerialization(clients, bank);
    }

    @Override
    public void update(Client client) {
        clients.set((int) client.getId() - 1, client);
        MyIOFile.clientsSerialization(clients, bank);
    }

    @Override
    public int getCount() {
        return clients.size();
    }

    @Override
    public Client getClient(int index) {
        return clients.get(index);
    }

    @Override
    public ArrayList<Client> getClients() {
        return clients;
    }
}
