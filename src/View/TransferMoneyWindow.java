package View;

import Data.BankAccs.BankAccount;
import Data.Client;
import Data.MyTableModel;
import Data.Repository;
import Exceptions.MyException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferMoneyWindow extends Window {

    public TransferMoneyWindow(BankAccount acc) {
        Repository[] repo = {new Repository("Alfa"), new Repository("Tinkoff"), new Repository("Sber")};
        JTable table = new JTable(new MyTableModel(repo, acc));
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);
        JLabel label = new JLabel(acc.getOwner().getData(true) + " - " + acc.getTitle() + ": " + acc.getMoney() + " руб");
        JButton exitButton = new JButton("Назад");
        JButton confirm  = new JButton("Выбрать");
        panel.add(confirm);
        panel.add(exitButton);


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ActionsWindow(acc);
                setVisible(false);
            }
        });

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    long id = (long) table.getValueAt(selectedRow, 1) - 1;
                    Client client = new Repository((String) table.getValueAt(selectedRow, 0)).getClient((int) id);
                    if (acc.getBank().equals(client.getBank()) && acc.getOwner().getId() == client.getId()) {
                        client = acc.getOwner();
                    }
                    BankAccount account = client.getAccInTitle((String) table.getValueAt(selectedRow, 4));
                    if (account != null) {
                        String result = JOptionPane.showInputDialog(getContentPane(), "Введите значение:", "Ввод", JOptionPane.PLAIN_MESSAGE);
                        double money;
                        try {
                            money = Double.parseDouble(result);
                            acc.transactMoney(account, money);
                            new Repository(acc.getBank()).update(acc.getOwner());
                            new Repository(account.getBank()).update(client);
                            new TransferMoneyWindow(acc).setLocation(getLocation());
                            setVisible(false);
                            JOptionPane.showMessageDialog(getContentPane(), "Перевод прошёл успешно", "Уведомление", JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(getContentPane(), "Неверный ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        } catch (MyException ex) {
                            JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(getContentPane(), "Ошибочка вышла", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(getContentPane(), "Ничего не выбрано", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        exitButton.setPreferredSize(confirm.getPreferredSize());

        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
        add(scrollPane);
        add(panel, BorderLayout.SOUTH);
        setLocation(630, 300);
        setSize(600, 350);
        setVisible(true);
    }
}
