package dao;

import model.Client;
import java.sql.*;
import java.util.ArrayList;

public class ClientDAO {

    public static ArrayList<Client> getAllClients() {
        ArrayList<Client> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM client");

            while (rs.next()) {
                list.add(new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone")
                ));
            }
        } catch (Exception e) {}

        return list;
    }
}
