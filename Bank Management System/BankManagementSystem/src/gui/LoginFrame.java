package gui;

import dao.UserDAO;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.net.URL;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Bank of Punjab - Login");
        setSize(900, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // --- Left Side: Full Image (No White Space) ---
        try {
            URL url = new URL("https://images.unsplash.com/photo-1563986768609-322da13575f3?q=80&w=450&auto=format&fit=crop");
            ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(450, 500, Image.SCALE_SMOOTH));
            JLabel imgLabel = new JLabel(icon);
            imgLabel.setBounds(0, 0, 450, 500);
            add(imgLabel);
        } catch (Exception e) { e.printStackTrace(); }

        // --- Right Side: Dark Form Panel ---
        JPanel p = new JPanel();
        p.setBounds(450, 0, 450, 500);
        p.setBackground(new Color(20, 20, 20)); // Stylish Dark Background
        p.setLayout(null);
        add(p);

        // Heading - Space significantly reduced
        JLabel h = new JLabel("BANK OF PUNJAB", SwingConstants.CENTER);
        h.setBounds(0, 80, 450, 40);
        h.setFont(new Font("Segoe UI", Font.BOLD, 30));
        h.setForeground(new Color(255, 100, 0));
        p.add(h);

        // Inputs - Compactly placed
        JTextField u = new JTextField();
        u.setBounds(75, 160, 300, 45); // Gap minimized
        u.setBackground(new Color(45, 45, 45));
        u.setForeground(Color.WHITE);
        u.setCaretColor(Color.WHITE);
        u.setBorder(new LineBorder(new Color(255, 100, 0), 2));
        p.add(new JLabel("Username") {{ setBounds(75, 140, 100, 20); setForeground(Color.LIGHT_GRAY); }});
        p.add(u);

        JPasswordField ps = new JPasswordField();
        ps.setBounds(75, 240, 300, 45);
        ps.setBackground(new Color(45, 45, 45));
        ps.setForeground(Color.WHITE);
        ps.setCaretColor(Color.WHITE);
        ps.setBorder(new LineBorder(new Color(255, 100, 0), 2));
        p.add(new JLabel("Password") {{ setBounds(75, 220, 100, 20); setForeground(Color.LIGHT_GRAY); }});
        p.add(ps);

        JButton login = new JButton("SECURE LOGIN");
        login.setBounds(75, 320, 300, 45);
        login.setBackground(new Color(255, 100, 0));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Segoe UI", Font.BOLD, 14));
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setFocusPainted(false);
        p.add(login);

        login.addActionListener(e -> {
            if(UserDAO.login(u.getText(), new String(ps.getPassword()))) {
                new DashboardFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login Failed! Try again.");
            }
        });

        setVisible(true);
    }
}