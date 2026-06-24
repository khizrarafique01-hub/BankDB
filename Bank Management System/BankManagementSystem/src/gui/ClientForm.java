package gui;

import dao.DBConnection;
import javax.swing.*;
import java.sql.*;

public class ClientForm extends BaseFrame {
    JTextField id, name, phone;

    public ClientForm() {
        super("Client Info", "https://images.unsplash.com/photo-1556740738-b6a63e27c4df?q=80&w=1000&auto=format&fit=crop");
        addStyledField("Client ID:", 180, id = new JTextField());
        addStyledField("Name:", 260, name = new JTextField());
        addStyledField("Phone:", 340, phone = new JTextField());

        JButton s = new JButton("SAVE");
        s.setBounds(350, 450, 140, 45); styleActionBtn(s); add(s);
        s.addActionListener(e -> saveClient());

        JButton u = new JButton("UPDATE");
        u.setBounds(510, 450, 140, 45); styleActionBtn(u); add(u);
        u.addActionListener(e -> updateClient());
        setVisible(true);
    }

    private void saveClient() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO client VALUES (?,?,?)");
            ps.setInt(1, Integer.parseInt(id.getText()));
            ps.setString(2, name.getText());
            ps.setString(3, phone.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Client Saved!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void updateClient() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE client SET name=?, phone=? WHERE id=?");
            ps.setString(1, name.getText()); ps.setString(2, phone.getText());
            ps.setInt(3, Integer.parseInt(id.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Updated!");
        } catch (Exception e) { e.printStackTrace(); }
    }
}