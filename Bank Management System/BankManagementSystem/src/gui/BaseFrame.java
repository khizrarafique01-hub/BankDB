package gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.swing.border.LineBorder;
import java.awt.event.*;

public class BaseFrame extends JFrame {
    protected JPopupMenu dropdown;

    public BaseFrame(String title, String imgUrl) {
        setTitle(title);
        setSize(1000, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // --- Navbar Section ---
        JPanel navbar = new JPanel();
        navbar.setBounds(0, 0, 1000, 75);
        navbar.setBackground(new Color(15, 15, 15));
        navbar.setLayout(null);

        // Stylish BOP Logo/Text in Navbar
        JLabel logo = new JLabel("BOP");
        logo.setBounds(25, 12, 100, 50);
        logo.setForeground(new Color(255, 100, 0));
        logo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        navbar.add(logo);

        JButton logout = new JButton("Logout");
        logout.setBounds(880, 22, 90, 32);
        styleNavBtn(logout);
        logout.addActionListener(e -> { dispose(); new LoginFrame(); }); //
        navbar.add(logout);

        JButton menuBtn = new JButton("Menu ▼");
        menuBtn.setBounds(760, 22, 100, 32);
        styleNavBtn(menuBtn);
        navbar.add(menuBtn);

        dropdown = new JPopupMenu();
        String[] items = {"Client Info", "Account", "Deposit", "Withdraw", "Transaction"};
        for (String item : items) {
            JMenuItem mItem = new JMenuItem(item);
            mItem.addActionListener(e -> { dropdown.setVisible(false); openForm(item); }); //
            dropdown.add(mItem);
        }
        menuBtn.addActionListener(e -> dropdown.show(menuBtn, 0, menuBtn.getHeight()));
        add(navbar);

        // Background with dark overlay
        try {
            JLabel bg = new JLabel(new ImageIcon(new URL(imgUrl))) {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(new Color(0, 0, 0, 200));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            };
            bg.setBounds(0, 0, 1000, 700);
            setContentPane(bg);
            add(navbar);
        } catch (Exception e) {}
    }

    protected void addStyledField(String label, int y, JTextField field) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(350, y, 300, 25);
        lbl.setForeground(new Color(255, 100, 0));
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(lbl);

        field.setBounds(350, y + 25, 300, 42);
        field.setOpaque(false); // Transparent background
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(new LineBorder(new Color(255, 100, 0), 2));
        add(field);
    }

    protected void styleActionBtn(JButton btn) {
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.WHITE);
        btn.setBorder(new LineBorder(new Color(255, 100, 0), 2));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> { if(dropdown != null) dropdown.setVisible(false); }); // Overlap fix
    }

    private void styleNavBtn(JButton btn) {
        btn.setBackground(new Color(255, 100, 0));
        btn.setForeground(Color.WHITE);
        btn.setBorder(null);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void openForm(String name) {
        dispose();
        if(name.equals("Client Info")) new ClientForm();
        else if(name.equals("Account")) new AccountForm();
        else if(name.equals("Deposit")) new DepositForm();
        else if(name.equals("Withdraw")) new WithdrawForm();
        else if(name.equals("Transaction")) new TransactionView();
    }
}