package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow extends Window{
    private JButton autoriz;
    private JButton register;
    private JButton exitButton;
    private JPanel panel;


    public MainMenuWindow(String bank) {
        setTitle("Главное меню");

        autoriz = new JButton("Авторизация");
        register = new JButton("Регистрация");
        exitButton = new JButton("Назад");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChoseBankWindow();
                dispose();
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterWindow(bank);
                dispose();
            }
        });

        autoriz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AutorizWindow(bank);
                dispose();
            }
        });

        panel = new JPanel();
        panel.add(autoriz);
        panel.add(register);

        add(panel);
        add(exitButton, BorderLayout.SOUTH);
        setSize(300, 100);
        setLocation(630, 300);
        setVisible(true);
    }
}
