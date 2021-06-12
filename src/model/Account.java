package model;

import model.utility.Address;
import model.utility.Money;
import model.utility.Person;

import java.io.Serial;
import java.io.Serializable;

public class Account  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234565L;

    private Integer accountId;
    private Person owner;
    private Address address;
    private Money balance;

    public Account(Person owner, Address address, Money balance){
        this.setBalance(balance);
        this.setOwner(owner);
        this.setAddress(address);
    }

    public Account() {

    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance)  {
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

}

