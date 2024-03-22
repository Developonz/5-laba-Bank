package Data.BankAccs;

import Data.Client;
import Exceptions.ErrorMoneyException;
import java.util.ArrayList;

public class DepositAcc extends BankAccount {
    public DepositAcc(Client client) {
        super(client);
        ArrayList<BankAccount> accounts = client.getAccs();
        int g = 0;
        int c = 0;
        for (BankAccount acc : accounts) {
            if (acc instanceof DepositAcc) {
                ++c;
                String[] strs = acc.getTitle().split(" ");
                if (!strs[strs.length - 1].equals("счёт")) {
                    int tmp = Integer.parseInt(strs[strs.length - 1]);
                    g = Math.max(g, tmp);
                }
            }
        }
        String title = c == 0 ? "Сберегательный счёт" : "Сберегательный счёт" + " " + ++g;
        this.setTitle(title);
    }

    @Override
    public void transactMoney(BankAccount account, double money) throws ErrorMoneyException {
        if (money > 0) {
            if (this.getMoney() >= money && this.getMoney() != 0) {
                account.topUpAcc(money);
                this.topDownAcc(money);
            } else {
                throw new ErrorMoneyException("На этом счёте недостаточно средств!!!");
            }
        } else {
            throw new ErrorMoneyException("Сумма должна быть больше 0.");
        }
    }
}
