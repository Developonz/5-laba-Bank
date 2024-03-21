package Data;

import DataTransmission.MyIOFile;

import java.util.ArrayList;

public class Repository {
    private ArrayList<Client> clients;
    private String bank;

    public Repository(String bank) {
        clients = new ArrayList<>();
        this.bank = bank;
        this.uploadData();
    }

    public void uploadData() {
        this.clients = MyIOFile.deserializeClients(bank);
    }

    public void removeClient(Client client) {
        uploadData();
        clients.set((int) client.getId() - 1, null);
        MyIOFile.clientsSerialization(clients, bank);
    }

    public void update(Client client) {
        uploadData();
        clients.set((int) client.getId() - 1, client);
        MyIOFile.clientsSerialization(clients, bank);
    }

    public int getCount() {
        return clients.size();
    }

    public Client getClient(int index) {
        return clients.get(index);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }


}
