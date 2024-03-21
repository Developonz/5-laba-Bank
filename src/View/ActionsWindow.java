package View;

import Data.BankAccs.BankAccount;
import Data.Client;
import Data.Repository;
import Exceptions.MyException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionsWindow extends Window {
    private JButton moneyTransfer;
    private JButton moneyDismount;
    private JButton moneyReplenish;
    private JButton accRename;
    private JButton accRemove;
    private JButton exitButton;
    private JPanel mainPanel;

    public ActionsWindow(BankAccount acc) {
        setTitle("Действия");

        moneyTransfer = new JButton("Перевести");
        moneyDismount = new JButton("Снять");
        moneyReplenish = new JButton("Пополнить");
        accRename = new JButton("Переименовать счёт");
        accRemove = new JButton("Удалить счёт");
        exitButton = new JButton("Назад");
        JLabel label = new JLabel(acc.getTitle() + ": " + acc.getMoney() + " руб");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileWindow(acc.getOwner());
                setVisible(false);
            }
        });

        moneyDismount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = JOptionPane.showInputDialog(null, "Введите значение:", "Ввод", JOptionPane.PLAIN_MESSAGE);
                double money;
                if (result != null && !result.isEmpty()) {
                    try {
                        try {
                            money = Double.parseDouble(result);
                            acc.topDownAcc(money);
                            Repository repo = new Repository(acc.getBank());
                            repo.update(acc.getOwner());
                            label.setText(acc.getTitle() + ": " + acc.getMoney() + " руб");
                            JOptionPane.showMessageDialog(getContentPane(), "Сумма успешно снята", "Уведомление", JOptionPane.INFORMATION_MESSAGE);
                        } catch (MyException ex) {
                            JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(getContentPane(), "Некорректный ввод числа", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        moneyReplenish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = JOptionPane.showInputDialog(null, "Введите значение:", "Ввод", JOptionPane.PLAIN_MESSAGE);
                if (result != null && !result.isEmpty()) {
                    try {
                        double money = Double.parseDouble(result);
                        acc.topUpAcc(money);
                        Repository repo = new Repository(acc.getBank());
                        repo.update(acc.getOwner());
                        label.setText(acc.getTitle() + ": " + acc.getMoney() + " руб");
                        JOptionPane.showMessageDialog(getContentPane(), "Счёт успешно пополнен", "Уведомление", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(getContentPane(), "Некорректный ввод числа", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    } catch (MyException ex) {
                        JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        accRename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = JOptionPane.showInputDialog(null, "Введите значение:", "Ввод", JOptionPane.PLAIN_MESSAGE);
                if (result != null && !result.isEmpty()) {
                    try {
                        acc.renameTitle(result);
                        Repository repo = new Repository(acc.getBank());
                        repo.update(acc.getOwner());
                        label.setText(acc.getTitle() + ": " + acc.getMoney() + " руб");
                        JOptionPane.showMessageDialog(getContentPane(), "Счёт успешно переименован", "Уведомление", JOptionPane.INFORMATION_MESSAGE);
                    } catch (MyException ex) {
                        JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        accRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Repository repo = new Repository(acc.getBank());
                if (acc.getOwner().getAccs().size() == 1) {
                    repo.removeClient(acc.getOwner());
                    new MainMenuWindow(acc.getBank());
                    setVisible(false);
                } else {
                    Client client = acc.getOwner();
                    try {
                        acc.removeAccount();
                        repo.update(client);
                        new ProfileWindow(client);
                        setVisible(false);
                    } catch (MyException ex) {
                        JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        moneyTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransferMoneyWindow(acc);
                setVisible(false);
            }
        });

        mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(moneyTransfer);
        mainPanel.add(moneyDismount);
        mainPanel.add(moneyReplenish);
        mainPanel.add(accRename);
        mainPanel.add(accRemove);


        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
        add(mainPanel);
        add(exitButton, BorderLayout.SOUTH);

        setSize(250, 300);
        setLocation(660, 300);
        setVisible(true);

    }
}
