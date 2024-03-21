package View;

import Data.BankAccs.BankAccount;
import Data.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChoseAccWindow  extends Window {

    public ChoseAccWindow(Client client) {
        setTitle("Счета");
        JLabel label = new JLabel("Выберите счёт");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JButton exitButton = new JButton("Назад");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileWindow(client);
                setVisible(false);
            }
        });

        ArrayList<BankAccount> list = client.getAccs();
        JButton[] buttons = new JButton[list.size()];

        for (int i = 0; i < list.size(); ++i) {
            buttons[i] = new JButton(list.get(i).getTitle());
            panel.add(buttons[i]);
            BankAccount acc = list.get(i);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ActionsWindow(acc);
                    setVisible(false);
                }
            });
        }

        add(label, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(panel);
        add(scroll);
        add(exitButton, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(250, 70));
        setMaximumSize(new Dimension(250, 350));
        pack();
        setLocation(630, 300);
        setVisible(true);
    }
}
