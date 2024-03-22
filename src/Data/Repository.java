package Data;

import DataTransmission.MyIOFile;

import java.util.ArrayList;

public interface Repository {
    void uploadData();

    void removeClient(Client client);

    void update(Client client);

    int getCount();

    Client getClient(int index);

    ArrayList<Client> getClients();
}
