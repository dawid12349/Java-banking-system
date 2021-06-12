package model.utility;

import exception.ModelSetterException;
import exception.NotEnoughMoneyException;

import java.io.Serial;
import java.io.Serializable;

public class Money implements Comparable<Money>, Serializable {
    @Serial
    private static final long serialVersionUID = 1234553L;
    private double money;

    public Money(double money) throws ModelSetterException {
        this.setMoney(money);
    }

    public Money(String string) throws ModelSetterException {
        this.setMoney(string);
    }

    public Money(){}

    public void setMoney(double money) throws ModelSetterException {
        if(money < 0)
            throw new ModelSetterException("Money should be positive number", "" + money);
        this.money = money;
    }

    public void setMoney(String money) throws ModelSetterException {
        try {
            double parseDouble = Double.parseDouble(money);
            if (parseDouble < 0)
                throw new ModelSetterException("Money should be positive number", "" + parseDouble);
            this.money = parseDouble;
        } catch (NumberFormatException e){
            throw new ModelSetterException("Money should be positive number", "x<0");
        }
    }

    @Override
    public int compareTo(Money o) {
        if(this.getMoney() > o.getMoney())
            return 1;
        else if(this.getMoney() < o.getMoney())
            return -1;
        return 0;
    }

    public void withDraw(Money draw) throws NotEnoughMoneyException, ModelSetterException {
        if(this.compareTo(draw) < 0)
            throw new NotEnoughMoneyException("Insufficient amount of money: ", draw, this);
        this.setMoney(this.getMoney() - draw.getMoney());
    }

    public void insert(Money insert) throws ModelSetterException {
        this.setMoney(this.getMoney() + insert.getMoney());
    }

    public double getMoney() {
        return money;
    }
    public String toString(){
        return String.format("$ %,.2f", money);
    }

}
