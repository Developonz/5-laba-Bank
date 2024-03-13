package Data.BankAccs;

import Data.Bank.Client;
import java.util.ArrayList;

public class DebitAcc extends BankAccount {
//    public DebitAcc(Client client) {
//        super(client);
//        ArrayList<BankAccount> accounts = client.getAccs();
//        int g = 0;
//        int c = 0;
//        for (BankAccount acc : accounts) {
//            if (acc instanceof DebitAcc) {
//                ++c;
//                String[] strs = acc.getTitle().split(" ");
//                if (!strs[strs.length - 1].equals("счёт")) {
//                    int tmp = Integer.parseInt(strs[strs.length - 1]);
//                    g = Math.max(g, tmp);
//                }
//            }
//        }
//        String title = c == 0 ? "Дебетовый счёт" : "Дебетовый счёт" + " " + ++g;
//        this.renameTitle(title);
//    }
//
//    @Override
//    public void transactMoney(BankAccount account) throws MyExeption.MoneyException {
//        double money = Input.inpNumber();
//        if (this.getMoney() >= money && this.getMoney() != 0) {
//            if (money <= this.getMoney()) {
//                account.changeMoney(money);
//                if (this.getBank() != account.getBank()) {
//                    money *= 1 + this.getBank().getComission();
//                }
//                this.changeMoney(-money);
//                System.out.println("Перевод успешно совершён.");
//            } else {
//                System.out.println("На вашем счёте недостаточно средств.");
//                transactMoney(account);
//            }
//        } else {
//            throw new MyExeption.MoneyException("На этом счёте недостаточно средств!!!");
//        }
//    }
}
