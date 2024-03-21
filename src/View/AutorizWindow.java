package View;

import Data.Client;
import Data.MyTableModel;
import Data.Repository;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutorizWindow extends Window {

    public AutorizWindow(String bank) {
        Repository repo = new Repository(bank);
        JTable table = new JTable(new MyTableModel(repo));
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);
        JLabel label = new JLabel("Выберите аккаунт");
        JButton exitButton = new JButton("Назад");
        JButton confirm  = new JButton("Подтвердить");
        panel.add(confirm);
        panel.add(exitButton);


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuWindow(bank);
                setVisible(false);
            }
        });

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    long id = (long) table.getValueAt(selectedRow, 0) - 1;
                    Client client = repo.getClient((int) id);
                    if (client != null) {
                        new ProfileWindow(client);
                        setVisible(false);
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
        setSize(350, 250);
        setVisible(true);
    }
}
