package Data.BankAccs;

import Data.Client;
import Exceptions.MyException;
import java.util.ArrayList;

public class CreditAcc extends BankAccount {
    public CreditAcc(Client client) {
        super(client);
        ArrayList<BankAccount> accounts = client.getAccs();
        int g = 0;
        int c = 0;
        for (BankAccount acc : accounts) {
            if (acc instanceof CreditAcc) {
                ++c;
                String[] strs = acc.getTitle().split(" ");
                if (!strs[strs.length - 1].equals("счёт")) {
                    int tmp = Integer.parseInt(strs[strs.length - 1]);
                    g = Math.max(g, tmp);
                }
            }
        }
        String title = c == 0 ? "Кредитный счёт" : "Кредитный счёт" + " " + ++g;
        this.setTitle(title);
    }

    @Override
    public void transactMoney(BankAccount account, double money) throws MyException {
        if (money > 0) {
            if (this.getMoney() - money >= -10000) {
                account.topUpAcc(money);
                this.topDownAcc(money);
            }
            else {
                throw new MyException("У вас недостаточно средств.");
            }
        } else {
            throw new MyException("Сумма должна быть больше 0.");
        }
    }
}