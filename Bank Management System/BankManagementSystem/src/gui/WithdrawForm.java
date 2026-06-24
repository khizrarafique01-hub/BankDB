package gui;

import service.BankService;
import javax.swing.*;

public class WithdrawForm extends BaseFrame {
    JTextField accId, amount;
    public WithdrawForm() {
        super("Withdraw", "https://images.unsplash.com/photo-1563986768609-322da13575f3?q=80&w=1000&auto=format&fit=crop");
        addStyledField("Account ID:", 200, accId = new JTextField());
        addStyledField("Amount:", 290, amount = new JTextField());
        JButton btn = new JButton("WITHDRAW");
        btn.setBounds(425, 420, 150, 45); styleActionBtn(btn); add(btn);
        btn.addActionListener(e -> {
            BankService.withdraw(Integer.parseInt(accId.getText()), Double.parseDouble(amount.getText()));
            JOptionPane.showMessageDialog(this, "Success!");
        });
        setVisible(true);
    }
}