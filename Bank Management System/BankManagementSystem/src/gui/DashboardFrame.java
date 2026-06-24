package gui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends BaseFrame {
    public DashboardFrame() {
        // Dashboard background image
        super("BOP Dashboard", "https://images.unsplash.com/photo-1460925895917-afdab827c52f?q=80&w=1000&auto=format&fit=crop");

        JLabel welcome = new JLabel("WELCOME TO BANK OF PUNJAB", SwingConstants.CENTER);
        welcome.setBounds(0, 250, 1000, 60);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 42));
        welcome.setForeground(Color.WHITE);
        add(welcome);

        JLabel tag = new JLabel("Your Trust, Our Commitment.", SwingConstants.CENTER);
        tag.setBounds(0, 310, 1000, 30);
        tag.setFont(new Font("Segoe UI", Font.ITALIC, 20));
        tag.setForeground(new Color(255, 100, 0));
        add(tag);

        setVisible(true);
    }
}