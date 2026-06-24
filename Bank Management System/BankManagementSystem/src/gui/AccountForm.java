package gui;

import dao.DBConnection;
import javax.swing.*;
import java.sql.*;

public class AccountForm extends BaseFrame {
    JTextField accId, balance, clientId;

    public AccountForm() {
        super("Account", "https://images.unsplash.com/photo-1454165205744-3b78555e5572?q=80&w=1000&auto=format&fit=crop");
        addStyledField("Acc ID:", 180, accId = new JTextField());
        addStyledField("Balance:", 260, balance = new JTextField());
        addStyledField("Client ID:", 340, clientId = new JTextField());

        JButton save = new JButton("SAVE ACCOUNT");
        save.setBounds(400, 450, 200, 45); styleActionBtn(save); add(save);
        save.addActionListener(e -> {
            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO account VALUES (?,?,?)");
                ps.setInt(1, Integer.parseInt(accId.getText()));
                ps.setDouble(2, Double.parseDouble(balance.getText()));
                ps.setInt(3, Integer.parseInt(clientId.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Account Created!");
            } catch (Exception ex) { ex.printStackTrace(); }
        });
        setVisible(true);
    }
}