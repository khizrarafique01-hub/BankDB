package model;

public class Account {
    private int id;
    private double balance;
    private int clientId;

    public Account(int id, double balance, int clientId) {
        this.id = id;
        this.balance = balance;
        this.clientId = clientId;
    }

    public int getId() { return id; }
    public double getBalance() { return balance; }
    public int getClientId() { return clientId; }
}
