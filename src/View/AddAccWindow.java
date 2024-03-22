package View;

import Data.BankAccs.CreditAcc;
import Data.BankAccs.DebitAcc;
import Data.BankAccs.DepositAcc;
import Data.Client;
import Data.ClientsRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAccWindow extends Window {
    private JButton debit;
    private JButton credit;
    private JButton deposit;
    private JButton exitButton;
    private JPanel panel;
    
    public AddAccWindow(Client client) {
        setTitle("Создание счёта");

        debit = new JButton("Дебетовый");
        debit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientsRepository repo = new ClientsRepository(client.getBank());
                client.addAccount(new DebitAcc(client));
                repo.update(client);
                JOptionPane.showMessageDialog(getContentPane(), "Успешно добавлено", "Уведомление", JOptionPane.INFORMATION_MESSAGE);
                new ProfileWindow(client);
                dispose();
            }
        });

        credit = new JButton("Кредитный");
        credit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientsRepository repo = new ClientsRepository(client.getBank());
                client.addAccount(new CreditAcc(client));
                repo.update(client);
                JOptionPane.showMessageDialog(getContentPane(), "Успешно добавлено", "Уведомление", JOptionPane.INFORMATION_MESSAGE);
                new ProfileWindow(client);
                dispose();
            }
        });

        deposit = new JButton("Сберегательный");
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientsRepository repo = new ClientsRepository(client.getBank());
                client.addAccount(new DepositAcc(client));
                repo.update(client);
                JOptionPane.showMessageDialog(getContentPane(), "Успешно добавлено", "Уведомление", JOptionPane.INFORMATION_MESSAGE);
                new ProfileWindow(client);
                dispose();
            }
        });

        exitButton = new JButton("Назад");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileWindow(client);
                dispose();
            }
        });

        debit.setPreferredSize(deposit.getPreferredSize());
        credit.setPreferredSize(deposit.getPreferredSize());

        panel = new JPanel();
        panel.add(debit);
        panel.add(credit);
        panel.add(deposit);

        add(panel);
        add(exitButton, BorderLayout.SOUTH);
        pack();
        setLocation(630, 300);
        setVisible(true);
    }
}
