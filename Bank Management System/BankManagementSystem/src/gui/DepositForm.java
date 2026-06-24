package gui;

import service.BankService;
import javax.swing.*;

public class DepositForm extends BaseFrame {
    JTextField accId, amount;
    public DepositForm() {
        super("Deposit", "https://images.unsplash.com/photo-1579621970563-ebec7560ff3e?q=80&w=1000&auto=format&fit=crop");
        addStyledField("Account ID:", 200, accId = new JTextField());
        addStyledField("Amount:", 290, amount = new JTextField());
        JButton btn = new JButton("DEPOSIT");
        btn.setBounds(425, 420, 150, 45); styleActionBtn(btn); add(btn);
        btn.addActionListener(e -> {
            BankService.deposit(Integer.parseInt(accId.getText()), Double.parseDouble(amount.getText()));
            JOptionPane.showMessageDialog(this, "Success!");
        });
        setVisible(true);
    }
}