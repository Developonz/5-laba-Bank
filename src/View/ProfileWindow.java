package View;

import Data.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileWindow extends Window {
    private JLabel label;
    private JButton addAccs;
    private JButton actions;
    private JButton exitButton;
    private JPanel panel;

    public ProfileWindow(Client client) {
        setTitle("Личный кабинет");

        label = new JLabel(client.getData(true));
        addAccs = new JButton("Добавить счёт");
        actions = new JButton("Действия");
        exitButton = new JButton("Назад");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainMenuWindow(client.getBank());
            }
        });

        addAccs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAccWindow(client);
                setVisible(false);
            }
        });

        actions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChoseAccWindow(client);
                setVisible(false);
            }
        });

        actions.setPreferredSize(addAccs.getPreferredSize());

        panel = new JPanel();
        panel.add(addAccs);
        panel.add(actions);

        add(label, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(exitButton, BorderLayout.SOUTH);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        pack();
        setLocation(630, 300);
        setVisible(true);
    }
}
