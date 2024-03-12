package BankAccs;

import Bank.Bank;
import Bank.Client;
import MainsCl.Input;
import MainsCl.MyExeption;

import java.util.Scanner;

public abstract class BankAccount {
    private double money;
    private long id;
    private String title;
    private Client owner;
    private Bank bank;

    public BankAccount(Client client) {
        this.owner = client;
        money = 0;
        this.bank = client.getBank();
        bank.getAccountsBank().add(this);
        id = bank.getCountAccs() + 1;
    }

    public abstract void transactMoney(BankAccount account) throws MyExeption.MoneyException;

    public void topUpAcc() {
        double money = Input.inpNum();
        if (money % 50 == 0) {
            this.money += money;
        } else {
            System.out.println("Введите сумму кратную 50");
            topUpAcc();
        }
    }

    public void topDownAcc() {
        if (this.money >= 50) {
            double money = Input.inpNum();
            if (money % 50 == 0) {
                this.money -= money;
            } else {
                System.out.println("Введите сумму кратную 50");
                topDownAcc();
            }
        } else {
            System.out.println("Недосаточно средств");
        }
    }

    public void renameTitle(String title) {
        this.title = title;
    }

    public void renameTitle() {
        boolean coincid = false;
        System.out.print("Введите название: ");
        String title = new Scanner(System.in).nextLine();
        for (BankAccount acc : owner.getAccs()) {
            if (acc.getTitle().equals(title) && acc != this) {
                coincid = true;
                break;
            }
        }
        if (coincid) {
            System.out.println("Счёт с таким названием уже есть.");
            renameTitle();
        } else {
            this.title = title;
            System.out.println("Название успешно сохранено.");
        }
    }

    public void removeAccount() throws MyExeption.RemoveException {
        if (this.getOwner().getAccs().size() == 1 && this.getMoney() != 0) {
            throw new MyExeption.RemoveException("Вы не можете закрыть счёт, так как на этом счёте лежат средства.");
        }
        bank.getAccountsBank().remove(this);
        owner.getAccs().remove(this);
        System.out.println("Счёт успешно удалён.");
    }

    public void changeMoney(double money) {
        this.money += money;
    }

    public Bank getBank() {
        return this.bank;
    }

    public double getMoney() {
        return money;
    }

    public String getTitle() {
        return title;
    }

    public Client getOwner() {
        return owner;
    }

    public long getId() {
        return this.id;
    }
}