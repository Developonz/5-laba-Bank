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
        ArrayList<Client> list = deserializeArrayList(fileName);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(client = new Client(nameField, surnameField, list.size() + 1, bank, typesAcc));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/" + bank + "Clients.txt"))) {
            oos.writeObject(list);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка записи", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return client;
    }

    public static void clientDesirealiz(String fileName) {
        ArrayList<Client> list = new ArrayList<>();
        File file = new File(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            list = (ArrayList<Client>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Client client : list) {
            System.out.println(client.getData(true));
            for (BankAccount acc : client.getAccs()) {
                System.out.println(acc.getTitle());
            }
            System.out.println();
        }
    }

    private static ArrayList<Client> deserializeArrayList(String fileName) {
        ArrayList<Client> list = new ArrayList<>();
        File file = new File(fileName);
        if (file.length() == 0) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            list = (ArrayList<Client>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}