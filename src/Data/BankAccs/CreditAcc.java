package Data.BankAccs;

import Data.Client;

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
        this.renameTitle(title);
    }
//
//    @Override
//    public void transactMoney(BankAccount account) {
//        double money = Input.inpNumber();
//        account.changeMoney(money);
//        money = money <= this.getMoney()? money: money * (1 + this.getBank().getComission());
//        money = this.getBank() == account.getBank() ? money : money * (1 + this.getBank().getComission());
//        this.changeMoney(-money);
//        System.out.println("Перевод успешно совершён.");
//    }
}