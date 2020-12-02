package pl.dziwins.RestAccounting.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Currency;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotBlank(message = "Account name must not be empty")
    private String name;
    @NotBlank(message = "Currency must be setted up")
    private Currency currency;
    @NotBlank(message = "Balance must be setted up")
    private float money;
    private boolean isTreasury;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public boolean isTreasury() {
        return isTreasury;
    }

    public void setTreasury(boolean treasury) {
        isTreasury = treasury;
    }

    @Override
    public String toString(){
        return "Id: " +getId()+", Name: "+ getName()+", Currency: "+getCurrency()+", Balance: "+getMoney()+" , is treasury: "+isTreasury()+".";
    }

    public void updateFrom(Account toUpdate){
        this.setName(toUpdate.getName());
        this.setCurrency(toUpdate.getCurrency());
        this.setMoney(toUpdate.getMoney());
    }

    public void transferTo(float transfer){
        this.setMoney(this.getMoney() + transfer);
    }

    public boolean transferFrom(float transfer){
        if (transfer < this.getMoney()){
            this.setMoney(this.getMoney() - transfer);
            return true;
        } else if ((transfer > this.getMoney()) && (this.isTreasury())){
            this.setMoney(this.getMoney() - transfer);
            return true;
        } else {
            return false;
        }
    }
}
