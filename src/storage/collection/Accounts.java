package storage.collection;

import exception.AccountNotFoundException;
import exception.ModelSetterException;
import exception.NotEnoughMoneyException;
import model.Account;
import model.Transaction;
import model.utility.Money;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Accounts extends LinkedList<Account> implements StorageCollection {

    @Override
    public void loadFromFile(File file) throws IOException, EOFException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStreamStream = new ObjectInputStream(fileInputStream);

        while(fileInputStream.available() > 0) {
            try {
                Account acc = (Account) objectInputStreamStream.readObject();
                acc.setAccountId(getFreeID());
                this.add(acc);
            } catch (EOFException | ClassNotFoundException e){
                break;
            }
        }

        objectInputStreamStream.close();
    }

    @Override
    public void saveToFile(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (Account account : this)
            objectOutputStream.writeObject(account);

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    @Override
    public Integer getFreeID(){
        try {
            Account account = this.get(this.size() - 1);
            return account.getAccountId() + 1;
        } catch (IndexOutOfBoundsException e){
            return  0;
        }

    }

    private List<Account> filterByProperty(Predicate<Account> filter) {
        return this
                .stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    private Account findByProperty(Predicate<Account> filter){
        return this
                .stream()
                .filter(filter)
                .findFirst()
                .orElse(null);

    }

    public List<Account> findByName(String name) {
        return filterByProperty(account -> account.getOwner().getFirstName().toString().equals(name));
    }

    public List<Account> findBySurname(String surname) {
        return filterByProperty(account -> account.getOwner().getSecondName().toString().equals(surname));
    }

    public List<Account> findByIDs(String ID) {
        return filterByProperty((account -> account.getAccountId().toString().equals(ID)));
    }

    public Account findByID(int ID){
        return findByProperty((account -> account.getAccountId().equals(ID)));
    }

    public List<Account> findByPesel(String pesel) {
        return filterByProperty(account -> account.getOwner().getPesel().equals(pesel));
    }

    public List<Account> findByCity(String city) {
        return filterByProperty(account -> account.getAddress().getCity().equals(city));
    }

    public List<Account> findByStreetAddress(String streetAddress) {
        return filterByProperty(account -> account.getAddress().getStreetAddress().equals(streetAddress));
    }

    public List<Account> findByCountry(String country) {
        return filterByProperty(account -> account.getAddress().getState().equals(country));
    }

    public List<Account> SearchByKeyAndValue(String fieldName, String value) {
        List<Account> result = new LinkedList<>();

        switch (fieldName) {
            case "ALL" -> result = new LinkedList<>(this);
            case "ID" -> result = findByIDs(value);
            case "NAME" -> result = findByName(value);
            case "SURNAME" -> result = findBySurname(value);
            case "PESEL" -> result = findByPesel(value);
            case "CITY" -> result = findByCity(value);
            case "STREETADDRESS" -> result = findByStreetAddress(value);
            case "COUNTRY" -> result = findByCountry(value);
            default -> {
                return result;
            }
        }
        return result;
    }


    public void applyTransaction(Transaction transaction) throws AccountNotFoundException, NotEnoughMoneyException, ModelSetterException {
        Account receiver = this.findByID(transaction.getReceiverID());
        Account sender = this.findByID(transaction.getSenderID());
        if(receiver == null)
            throw new AccountNotFoundException("COULD NOT FIND ACCOUNT OF ID:", transaction.getReceiverID());
        if(sender == null)
            throw new AccountNotFoundException("COULD NOT FIND ACCOUNT OF ID:", transaction.getSenderID());

        Money senderBalance = sender.getBalance();
        Money transactionMoney = transaction.getMoney();
        Money receiverBalance = receiver.getBalance();

        senderBalance.withDraw(transactionMoney);
        receiverBalance.insert(transactionMoney);
    }


}
