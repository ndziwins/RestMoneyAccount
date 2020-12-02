package pl.dziwins.model;

import org.javamoney.moneta.Money;

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
    private Money money;
    private boolean isTreasury;

    public Account() {
    }

    public int getId() {
        return id;
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

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public boolean isTreasury() {
        return isTreasury;
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

    public void transferTo(Money transfer){
        this.setMoney(this.getMoney().add(transfer));
    }

    public boolean transferFrom(Money transfer){
        if (transfer.isLessThan(this.getMoney())){
            this.setMoney(this.getMoney().subtract(transfer));
            return true;
        } else if ((transfer.isGreaterThan(this.getMoney())) && (this.isTreasury())){
            this.setMoney(this.getMoney().subtract(transfer));
            return true;
        } else {
            return false;
        }
    }
}
