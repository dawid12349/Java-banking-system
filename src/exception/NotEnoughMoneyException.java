package exception;

import model.utility.Money;

public class NotEnoughMoneyException extends Exception  {
    private final Money wanted;
    private final Money contained;

    public NotEnoughMoneyException(String message, Money wanted, Money contained){
        super(message);
        this.wanted = wanted;
        this.contained = contained;
    }

    public Money getContained() {
        return contained;
    }

    public Money getWanted() {
        return wanted;
    }

    public String getPresetErrorMessage(){
        return "Insufficient amount of money \nWanted: " + getWanted() + "\ncurrently in possession: " + getContained();
    }


}
