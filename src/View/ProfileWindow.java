package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileWindow extends Window {
    private JLabel label;
    private JButton addAccs;
    private JButton printAccs;
    private JButton actions;
    private JButton exitButton;
    private JPanel panel;

    public ProfileWindow() {
        setTitle("Личный кабинет");

        label = new JLabel("Имя Фамилия");
        addAccs = new JButton("Добавить счёт");
        printAccs = new JButton("Вывести счета");
        actions = new JButton("Действия");
        exitButton = new JButton("Назад");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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
