package View;

import Data.Client;
import Data.MyTableModel;
import Data.ClientsRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutorizWindow extends Window {
    private ClientsRepository repo;
    private JTable table;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JLabel label;
    private JButton exitButton;
    private JButton confirmBut;

    public AutorizWindow(String bank) {
        repo = new ClientsRepository(bank);
        table = new JTable(new MyTableModel(repo));
        panel = new JPanel();
        scrollPane = new JScrollPane(table);
        label = new JLabel("Выберите аккаунт");
        exitButton = new JButton("Назад");
        confirmBut  = new JButton("Подтвердить");

        panel.add(confirmBut);
        panel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuWindow(bank);
                dispose();
            }
        });

        confirmBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    long id = (long) table.getValueAt(selectedRow, 0) - 1;
                    Client client = repo.getClient((int) id);
                    if (client != null) {
                        new ProfileWindow(client);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(getContentPane(), "Ошибочка вышла", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(getContentPane(), "Ничего не выбрано", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        exitButton.setPreferredSize(confirmBut.getPreferredSize());

        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
        add(scrollPane);
        add(panel, BorderLayout.SOUTH);
        setLocation(630, 300);
        setSize(350, 250);
        setVisible(true);
    }
}
