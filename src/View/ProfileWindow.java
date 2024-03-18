package View;

import Data.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileWindow extends Window {
    private Client client;
    private JLabel label;
    private JButton addAccs;
    private JButton printAccs;
    private JButton actions;
    private JButton exitButton;
    private JPanel panel;

    public ProfileWindow(Client client) {
        this.client = client;
        setTitle("Личный кабинет");

        label = new JLabel(client.getData(true));
        addAccs = new JButton("Добавить счёт");
        printAccs = new JButton("Вывести счета");
        actions = new JButton("Действия");
        exitButton = new JButton("Назад");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainMenuWindow(client.getBank());
            }
        });
        addAccs.setPreferredSize(printAccs.getPreferredSize());
        actions.setPreferredSize(printAccs.getPreferredSize());

        panel = new JPanel();
        panel.add(addAccs);
        panel.add(printAccs);
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
