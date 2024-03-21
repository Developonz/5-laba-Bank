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


    public String getData(boolean e) {
        return this.surName + " " + this.firstName;
    }

    public String getBank() {
        return this.bank;
    }

    public ArrayList<BankAccount> getAccs() {
        return accountsClient;
    }

    public long getId() { return this.id; }

    public BankAccount getAccInTitle(String title) {
        for (int i = 0; i < this.getAccs().size(); ++i) {
            if (this.getAccs().get(i).getTitle().equals(title)) {
                return this.getAccs().get(i);
            }
        }
        return null;
    }
}
