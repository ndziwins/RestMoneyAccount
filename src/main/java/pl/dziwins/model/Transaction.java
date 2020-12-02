package pl.dziwins.model;

public class Transaction {

    private int idFrom;
    private int idTo;
    private float money;

    public Transaction() {
    }

    public Transaction(int idFrom, int idTo, float money) {
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.money = money;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public float getMoney() {
        return money;
    }

}
