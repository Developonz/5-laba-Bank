package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoseBankWindow extends Window {
    private JButton alfa;
    private JButton tink;
    private JButton sber;
    private JButton exitButton;
    private JPanel panel;

    public ChoseBankWindow() {
        setTitle("Банк");

        alfa = new JButton("Alfa");
        alfa.addActionListener(new MyListener(alfa.getText()));

        tink = new JButton("Tinkoff");
        tink.addActionListener(new MyListener(tink.getText()));

        sber = new JButton("Sber");
        sber.addActionListener(new MyListener(sber.getText()));

        exitButton = new JButton("Выход");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Действительно ли вы хотите выйти из приложения?",
                        "Подтверждение", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        panel = new JPanel();
        panel.add(alfa);
        panel.add(tink);
        panel.add(sber);

        add(panel);
        add(exitButton, BorderLayout.SOUTH);
        setSize(300, 100);
        setLocation(630, 300);
        setVisible(true);
    }

    public class MyListener implements ActionListener {
        private String str;
        public MyListener(String str) {
            this.str = str;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new MainMenuWindow(this.str);
            setVisible(false);
        }
    }
}
