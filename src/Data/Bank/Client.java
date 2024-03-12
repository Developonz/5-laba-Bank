package Bank;

import BankAccs.*;
import MainsCl.MyExeption;

import java.util.ArrayList;
import java.util.Random;

public class Client {
    private String firstName;
    private String surName;
    private String phone;
    private long id;
    private Bank bank;
    private String[] surnames = new String[]{"Запруто", "Зелент", "Красников", "Новиков", "Дульский"};
    private String[] names = new String[]{"Евгений", "Никита", "Кирилл", "Дмитрий", "Александр"};
    private ArrayList<BankAccount> accountsClient;

    public Client(String firstName, String surName, Bank bank) {
        this.firstName = firstName;
        this.surName = surName;
        this.bank = bank;
        id = bank.getCountClients() + 1;
        phone = genRandPhone();
        accountsClient = new ArrayList<>();
    }

    public Client(Bank bank) {
        this.firstName = names[new Random().nextInt(5)];
        this.surName = surnames[new Random().nextInt(5)];
        this.bank = bank;
        id = bank.getClients().size() + 1;
        phone = genRandPhone();
        accountsClient = new ArrayList<>();
    }

    public void addAccount(BankAccount account) throws MyExeption.AccException {
        if (account.getOwner() == this) {
            this.accountsClient.add(account);
            bank.setCountAccs();
            System.out.println("Счёт успешно добавлен");
        } else {
            throw new MyExeption.AccException("У этого счёта есть владелец.");
        }
    }

    public void printAccs() {
        System.out.println("Ваши счета:");
        int i = 1;
        for (BankAccount acc : accountsClient) {
            System.out.println((i++) + ". id: " + acc.getId() + " title: " + acc.getTitle() + "\n   Сумма: " + acc.getMoney());
        }
    }

    private static String genRandPhone() {
        Random random = new Random();
        StringBuilder phoneNumberBuilder = new StringBuilder("8");
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            phoneNumberBuilder.append(digit);
        }
        return phoneNumberBuilder.toString();
    }

    public String getData() {
        return this.firstName + " " + this.surName + " id: " + id + " phone: " + phone;
    }

    public String getData(boolean e) {
        return this.firstName + " " + this.surName;
    }

    public Bank getBank() {
        return this.bank;
    }

    public ArrayList<BankAccount> getAccs() {
        return accountsClient;
    }
}
