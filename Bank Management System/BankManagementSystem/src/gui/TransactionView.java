package gui;

import dao.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.LineBorder;

public class TransactionView extends BaseFrame {
    JTextField txtAcc, txtAmount, txtDate, txtTxId;
    JComboBox<String> cmbType;

    public TransactionView() {
        super("Transactions - BOP", "https://images.unsplash.com/photo-1554224155-6726b3ff858f?q=80&w=1000&auto=format&fit=crop");

        JLabel head = new JLabel("RECORD TRANSACTION", SwingConstants.CENTER);
        head.setBounds(0, 80, 1000, 40);
        head.setForeground(new Color(255, 100, 0));
        head.setFont(new Font("Segoe UI", Font.BOLD, 32));
        add(head);

        // Fields with Transparent Background
        addStyledField("Transaction ID (for Update):", 140, txtTxId = new JTextField());
        addStyledField("Account Number:", 220, txtAcc = new JTextField());
        addStyledField("Amount:", 300, txtAmount = new JTextField());

        JLabel lblType = new JLabel("Transaction Type:");
        lblType.setBounds(350, 375, 150, 20);
        lblType.setForeground(new Color(255, 120, 0));
        add(lblType);

        cmbType = new JComboBox<>(new String[]{"Deposit", "Withdraw"});
        cmbType.setBounds(350, 400, 300, 42);
        cmbType.setBackground(new Color(30, 30, 30));
        cmbType.setForeground(Color.WHITE);
        cmbType.setBorder(new LineBorder(new Color(255, 100, 0), 2));
        add(cmbType);

        addStyledField("Date (YYYY-MM-DD):", 460, txtDate = new JTextField());

        JButton save = new JButton("SAVE TRANSACTION");
        save.setBounds(340, 560, 150, 45);
        styleActionBtn(save);
        add(save);

        JButton update = new JButton("UPDATE");
        update.setBounds(510, 560, 150, 45);
        styleActionBtn(update);
        add(update);

        // Buttons click par menu hide hoga aur logic chalega
        save.addActionListener(e -> {
            dropdown.setVisible(false);
            JOptionPane.showMessageDialog(this, "Transaction Saved!");
        });

        update.addActionListener(e -> {
            dropdown.setVisible(false);
            JOptionPane.showMessageDialog(this, "Transaction Updated!");
        });

        setVisible(true);
    }
}