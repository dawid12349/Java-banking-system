package model;

import exception.ModelSetterException;
import model.utility.Money;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234561L;

    private Integer transactionID;
    private Integer senderID;
    private Integer receiverID;
    private Money money;
    private String creationDate;

    public Transaction() {
        setCreationDate(LocalDateTime.now());
    }

    public Transaction(Integer senderID, Integer receiverID, Money money) throws ModelSetterException {
        this.setSenderID(senderID);
        this.setReceiverID(receiverID);
        this.setMoney(money);
        this.setCreationDate(LocalDateTime.now());
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public void setSenderID(Integer senderID) throws ModelSetterException {
        if(this.receiverID != null && this.receiverID.equals(senderID))
            throw new ModelSetterException("SELF-REFERENCING TRANSACTION IS NOT ALLOWED!", senderID + "-to-" + this.receiverID);
        this.senderID = senderID;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Integer receiverID) throws ModelSetterException {
        if(this.senderID != null && this.senderID.equals(receiverID))
            throw new ModelSetterException("SELF-REFERENCING TRANSACTION IS NOT ALLOWED!", senderID + "-to-" + this.receiverID);
        this.receiverID = receiverID;
    }

    public void setCreationDate(LocalDateTime now) {
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.creationDate =  simpleDateFormat.format(now);
    }

    public String getCreationDate() {
        return creationDate;
    }
}

