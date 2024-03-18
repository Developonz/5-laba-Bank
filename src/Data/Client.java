package Data;


import Data.BankAccs.BankAccount;
import Data.BankAccs.CreditAcc;
import Data.BankAccs.DebitAcc;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable {
    private String firstName;
    private String surName;
    private long id;
    private String bank;
    private ArrayList<BankAccount> accountsClient;

    public Client(String firstName, String surName, long id, String bank, String typeAcc) {
        this.firstName = firstName;
        this.surName = surName;
        this.id = id;
        this.bank = bank;
        accountsClient = new ArrayList<>();
        if (typeAcc.equals("дебетовый")) {
            this.addAccount(new DebitAcc(this));
        } else if (typeAcc.equals("кредитный")){
            this.addAccount(new CreditAcc(this));
        } else {

        }
    }

    public void addAccount(BankAccount account)  {
        this.accountsClient.add(account);
    }

    public void printAccs() {
        System.out.println("Ваши счета:");
        int i = 1;
        for (BankAccount acc : accountsClient) {
            System.out.println((i++) + ". id: " + acc.getId() + " title: " + acc.getTitle() + "\n   Сумма: " + acc.getMoney());
        }
    }

//    private static String genRandPhone() {
//        Random random = new Random();
//        StringBuilder phoneNumberBuilder = new StringBuilder("8");
//        for (int i = 0; i < 10; i++) {
//            int digit = random.nextInt(10);
//            phoneNumberBuilder.append(digit);
//        }
//        return phoneNumberBuilder.toString();
//    }
//
//    public String getData() {
//        return this.firstName + " " + this.surName + " id: " + id + " phone: " + phone;
//    }
//
    public String getData(boolean e) {
        return this.firstName + " " + this.surName;
    }

    public String getBank() {
        return this.bank;
    }

    public ArrayList<BankAccount> getAccs() {
        return accountsClient;
    }
}
