package service;

import dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BankService {

    public static void deposit(int accId, double amount) {

        try {
            Connection con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // 1️⃣ Update balance
            PreparedStatement ps1 = con.prepareStatement(
                    "UPDATE account SET balance = balance + ? WHERE id = ?"
            );
            ps1.setDouble(1, amount);
            ps1.setInt(2, accId);

            int r1 = ps1.executeUpdate();

            // 2️⃣ Insert transaction
            PreparedStatement ps2 = con.prepareStatement(
                    "INSERT INTO transactions(acc_id, amount, type) VALUES (?, ?, ?)"
            );
            ps2.setInt(1, accId);
            ps2.setDouble(2, amount);
            ps2.setString(3, "Deposit");

            int r2 = ps2.executeUpdate();

            if (r1 > 0 && r2 > 0) {
                con.commit();
                System.out.println("Deposit + Transaction Saved");
            } else {
                con.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void withdraw(int accId, double amount) {

        try {
            Connection con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // 1️⃣ Update balance
            PreparedStatement ps1 = con.prepareStatement(
                    "UPDATE account SET balance = balance - ? WHERE id = ?"
            );
            ps1.setDouble(1, amount);
            ps1.setInt(2, accId);

            int r1 = ps1.executeUpdate();

            // 2️⃣ Insert transaction
            PreparedStatement ps2 = con.prepareStatement(
                    "INSERT INTO transactions(acc_id, amount, type) VALUES (?, ?, ?)"
            );
            ps2.setInt(1, accId);
            ps2.setDouble(2, amount);
            ps2.setString(3, "Withdraw");

            int r2 = ps2.executeUpdate();

            if (r1 > 0 && r2 > 0) {
                con.commit();
                System.out.println("Withdraw + Transaction Saved");
            } else {
                con.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}