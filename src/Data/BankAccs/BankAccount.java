package Data.BankAccs;


import Data.Client;
import java.io.Serializable;
import Exceptions.ErrorMoneyException;

public abstract class BankAccount implements Serializable {
    private double money;
    private String title;
    private Client owner;
    private String bank;

    public BankAccount(Client client) {
        this.owner = client;
        money = 0;
        this.bank = client.getBank();
    }

    public abstract void transactMoney(BankAccount account, double money) throws ErrorMoneyException;

    public void topUpAcc(double money) throws ErrorMoneyException {
        if (money > 0) {
            this.money += money;
        } else {
            throw new ErrorMoneyException("Сумма должна быть больше 0.");
        }
    }

    public void topDownAcc(double money) throws ErrorMoneyException {
        if (money > 0) {
            if (this instanceof CreditAcc) {
                if (this.money - money >= -10000) {
                    this.money -= money;
                } else {
                    throw new ErrorMoneyException("У вас недостаточно средств");
                }
            } else {
                if (this.money >= money) {
                    this.money -= money;
                } else {
                    throw new ErrorMoneyException("У вас недостаточно средств");
                }
            }
        } else {
            throw new ErrorMoneyException("Сумма должна быть больше 0.");
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void renameTitle(String title) throws ErrorMoneyException {
        boolean coincid = false;
        for (BankAccount acc : owner.getAccs()) {
            if (acc.getTitle().equals(title) && acc != this) {
                coincid = true;
                break;
            }
        }
        if (coincid) {
            throw new ErrorMoneyException("У вас уже есть счёт с таким названием");
        } else {
            this.title = title;
        }
    }

    public void removeAccount() throws ErrorMoneyException {
        if (this.getMoney() != 0) {
            throw new ErrorMoneyException("Вы не можете закрыть счёт");
        }
        owner.getAccs().remove(this);
    }

    public String getBank() {
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
}