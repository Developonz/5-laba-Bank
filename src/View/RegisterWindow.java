package View;


import DataTransmission.MyIOFile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegisterWindow extends Window{
    private JPanel mainPanel;
    private JPanel firstCol;
    private JPanel secondCol;
    private JPanel firstRow;
    private JPanel secondRow;
    private JLabel name;
    private JLabel surname;
    private JLabel typeAcc;
    private JComboBox<String> typesAccs;
    private JTextField nameField;
    private JTextField surnameField;
    private JButton register;
    private JButton exitButton;

    public RegisterWindow(String bank) {
        setTitle("Регистрация");

        mainPanel = new JPanel();
        firstCol = new JPanel();
        secondCol = new JPanel();
        firstRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        secondRow = new JPanel();
        name = new JLabel("Имя:");
        surname = new JLabel("Фамилия:");
        typeAcc = new JLabel("Тип счёта:");
        String[] options = {"дебетовый", "кредитный"};
        typesAccs = new JComboBox<>(options);
        nameField = new JTextField(10);
        surnameField = new JTextField(10);
        register = new JButton("Подтвердить");
        exitButton = new JButton("Назад");
        exitButton.setPreferredSize(register.getPreferredSize());

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileWindow(MyIOFile.clientSerialization(nameField.getText(), surnameField.getText(), bank, (String) typesAccs.getSelectedItem()));
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuWindow(bank);
                dispose();
            }
        });

        firstCol.setLayout(new BoxLayout(firstCol, BoxLayout.Y_AXIS));
        secondCol.setLayout(new BoxLayout(secondCol, BoxLayout.Y_AXIS));

        firstCol.add(surname);
        firstCol.add(Box.createVerticalStrut(20));
        firstCol.add(name);
        firstCol.add(Box.createVerticalStrut(20));
        firstCol.add(typeAcc);
        secondCol.add(surnameField);
        secondCol.add(Box.createVerticalStrut(20));
        secondCol.add(nameField);
        secondCol.add(Box.createVerticalStrut(20));
        secondCol.add(typesAccs);

        firstRow.add(firstCol);
        firstRow.add(secondCol);
        secondRow.add(register);
        secondRow.add(exitButton);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(firstRow);
        mainPanel.add(secondRow);
        add(mainPanel);

        setLocation(630, 300);
        setSize(255, 235);
        setVisible(true);
    }
}
