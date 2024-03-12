package Bank;

import BankAccs.BankAccount;
import MainsCl.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private String titleBank;
    private long countClients;
    private long countAccs;
    private ArrayList<Client> clients;
    private ArrayList<BankAccount> accountsBank;
    private double comission;

        public Bank(String title) {
        clients = new ArrayList<>();
        accountsBank = new ArrayList<>();
        this.titleBank = title;
        comission = 0.03;
        countClients = 0;
        countAccs = 0;
    }

    public Bank(String title, double comission) {
        clients = new ArrayList<>();
        accountsBank = new ArrayList<>();
        this.titleBank = title;
        this.comission = comission;
        countClients = 0;
        countAccs = 0;
    }

    public Client regClient() {
        Scanner scanner = new Scanner(System.in);
        String aws = "";
        System.out.print("Хотите ли вы ввести данные клиента случайно?(да/нет): ");
        aws = scanner.nextLine();
        Client client;
        while (!(aws.equalsIgnoreCase("да") || aws.equalsIgnoreCase("нет"))) {
            System.out.print("Неверный ввод. Введите заново: ");
            aws = scanner.nextLine();
        }
        if (aws.equalsIgnoreCase("да")) {
            client = new Client(this);
        } else {
            String name;
            String surname;
            System.out.print("Введите имя: ");
            name = scanner.nextLine();
            System.out.print("Введите фамилию: ");
            surname = scanner.nextLine();
            client = new Client(name, surname, this);
        }
        client = new Menu.ClientMenuC.AddAcc(client).execute() == 0 ? client : null;
        if (client != null) {
            this.clients.add(client);
        }
        return client;
    }

    public ArrayList<Client> autorizClient() {
        if (clients.isEmpty()) {
            System.out.println("В банке нет зарегистрированных клиентов.");
            return null;
        }
        return clients;
    }

    public double getComission() {
        return this.comission;
    }

    public String getTitleBank() {
        return titleBank;
    }

    public ArrayList<BankAccount> getAccountsBank() {
        return accountsBank;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public long getCountClients() {
        return countClients;
    }

    public long getCountAccs() {
        return countAccs;
    }

    public void setCountAccs() {
        ++countAccs;
    }
}