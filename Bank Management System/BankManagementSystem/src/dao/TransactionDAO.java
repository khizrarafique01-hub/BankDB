package dao;

import java.sql.*;

public class TransactionDAO {

    public static void addTransaction(int accId, double amount, String type) {
        try {
            Connection con = DBConnection.getConnection();
            CallableStatement cs = con.prepareCall(
                    "{CALL addTransaction(?,?,?)}"
            );
            cs.setInt(1, accId);
            cs.setDouble(2, amount);
            cs.setString(3, type);
            cs.execute();
        } catch (Exception e) {}
    }
}
