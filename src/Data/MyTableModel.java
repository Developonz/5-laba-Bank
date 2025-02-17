package Data;

import Data.BankAccs.BankAccount;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;

public class MyTableModel extends AbstractTableModel {
    private String[] headers;
    private Object[][] data;
    private int rowCount;
    private int colCount;
    private ArrayList<Client> clients;

    public MyTableModel(ClientsRepository repo) {
        this.clients = repo.getClients();
        this.rowCount = repo.getCount();
        this.colCount = 3;
        this.headers = new String[] {"id", "Фамилия", "Имя"};
        data = new Object[rowCount][colCount];
        int i = 0;
        int empty = 0;
        for (Client client : clients) {
            if (client != null) {
                String[] str = client.getData(true).split(" ");
                Object[] obj = {client.getId(), str[0], str[1]};
                data[i++] = obj;
            } else {
                ++empty;
            }
        }
        this.rowCount -= empty;
    }

    public MyTableModel(ClientsRepository[] repo, BankAccount acc) {
        String[] str = acc.getOwner().getData(true).split(" ");
        Object[] surplus = {acc.getOwner().getBank(), acc.getOwner().getId(), str[0], str[1], acc.getTitle(), acc.getMoney()};
        clients = new ArrayList<Client>();
        for (int i = 0; i < repo.length; ++i) {
            clients.addAll(repo[i].getClients());
        }
        this.rowCount = 0;
        for (Client client : clients) {
            if (client != null) {
                this.rowCount += client.getAccs().size();
            }
        }
        this.colCount = 6;
        this.headers = new String[] {"Банк", "id", "Фамилия", "Имя", "Счёт", "Сумма"};
        data = new Object[rowCount][colCount];
        int i = 0;
        int empty = 0;
        for (Client client : clients) {
            if (client != null) {
                for (int j = 0; j < client.getAccs().size(); ++j) {
                    if (client.getAccs().get(j) != acc) {
                        String[] str1 = client.getData(true).split(" ");
                        Object[] obj = {client.getBank(), client.getId(), str1[0], str1[1], client.getAccs().get(j).getTitle(), client.getAccs().get(j).getMoney()};
                        if (!Arrays.equals(surplus, obj)) {
                            data[i++] = obj;
                        } else {
                            ++empty;
                        }
                    }
                }
            }
        }
        this.rowCount -= empty;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return colCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }
}
