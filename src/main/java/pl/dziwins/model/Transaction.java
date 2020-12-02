package pl.dziwins.model;

import org.javamoney.moneta.Money;

public class Transaction {

    private int idFrom;
    private int idTo;
    private Money money;

    public Transaction() {
    }

    public Transaction(int idFrom, int idTo, Money money) {
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

    public Money getMoney() {
        return money;
    }

}
