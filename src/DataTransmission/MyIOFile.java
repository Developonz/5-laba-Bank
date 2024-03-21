package DataTransmission;

import Data.BankAccs.BankAccount;
import Data.Client;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class MyIOFile {
    public static Client clientSerialization(String nameField, String surnameField, String bank, String typesAcc)  {
        String fileName = "src/" + bank + "Clients.txt";
        Client client;
        ArrayList<Client> list = deserializeClients(bank);
        list.add(client = new Client(nameField, surnameField, list.size() + 1, bank, typesAcc));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка записи", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return client;
    }

    public static void clientsSerialization(ArrayList<Client> clients, String bank)  {
        String fileName = "src/" + bank + "Clients.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(clients);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка записи", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<Client> deserializeClients(String bank) {
        String fileName = "src/" + bank + "Clients.txt";
        ArrayList<Client> list = new ArrayList<>();
        File file = new File(fileName);
        if (file.length() == 0) {
            return list;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            list = (ArrayList<Client>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}