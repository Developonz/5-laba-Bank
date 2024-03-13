package View;

import javax.swing.*;
import java.awt.*;

public class ActionsWindow extends Window {
    private JButton moneyTransfer;
    private JButton moneyDismount;
    private JButton moneyReplenish;
    private JButton accInfo;
    private JButton accRename;
    private JButton accRemove;
    private JButton exitButton;
    private JPanel mainPanel;

    public ActionsWindow() {
        setTitle("Действия");

        moneyTransfer = new JButton("Перевести");
        moneyDismount = new JButton("Снять");
        moneyReplenish = new JButton("Пополнить");
        accInfo = new JButton("О счёте");
        accRename = new JButton("Переименовать счёт");
        accRemove = new JButton("Удалить счёт");
        exitButton = new JButton("Назад");

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(moneyTransfer);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(moneyDismount);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(moneyReplenish);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(accInfo);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(accRename);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(accRemove);
        mainPanel.add(Box.createVerticalGlue());

        add(exitButton, BorderLayout.SOUTH);

        moneyTransfer.setAlignmentX(Component.CENTER_ALIGNMENT);
        moneyDismount.setAlignmentX(Component.CENTER_ALIGNMENT);
        moneyReplenish.setAlignmentX(Component.CENTER_ALIGNMENT);
        accInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        accRename.setAlignmentX(Component.CENTER_ALIGNMENT);
        accRemove.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(mainPanel);

        setSize(250, 300);
        setLocation(660, 300);
        setVisible(true);
    }
}
