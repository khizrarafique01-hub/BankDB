package model;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int accountId;
    private double amount;
    private String type;
    private Timestamp date;

    public Transaction(int id, int accountId, double amount, String type, Timestamp date) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }
}
