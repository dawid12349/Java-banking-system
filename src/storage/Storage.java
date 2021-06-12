package storage;

import exception.StorageIOException;
import storage.collection.Accounts;
import storage.collection.Employees;
import storage.collection.Transactions;
import utils.Constants;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

public enum Storage {
    INSTANCE;

    private Accounts accounts = new Accounts();
    private Transactions transactions = new Transactions();
    private Employees employees = new Employees();

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }
    public Accounts getAccounts() {
        return accounts;
    }

    public Transactions getTransactions() { return transactions; }
    public void setTransactions(Transactions transactions) { this.transactions = transactions; }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public void save() throws StorageIOException {
        try {
            this.accounts.saveToFile(new File(Constants.storage.ACCOUNTS_FILE_PATH));
            this.transactions.saveToFile(new File(Constants.storage.TRANSACTIONS_FILE_PATH));
            this.employees.saveToFile(new File(Constants.storage.EMPLOYEES_FILE_PATH));
        } catch (IOException e){
            throw new StorageIOException("STORAGE FILE NOT FOUND: " + e.getMessage() + "APPLICATION SHUTDOWN", StorageIOException.StatusCode.CRITICAL);
        }
    }

    public void load() throws StorageIOException {
        try {
            this.accounts.loadFromFile(new File(Constants.storage.ACCOUNTS_FILE_PATH));
            this.transactions.loadFromFile(new File(Constants.storage.TRANSACTIONS_FILE_PATH));
            this.employees.loadFromFile(new File(Constants.storage.EMPLOYEES_FILE_PATH));
        } catch (EOFException e) {
            throw new StorageIOException("COULD NOT LOAD STORAGE (EMPTY STORAGE)", StorageIOException.StatusCode.CONTINUE);
        } catch (IOException e) {
            throw new StorageIOException("STORAGE FILE NOT FOUND: "  + e.getMessage() + "APPLICATION SHUTDOWN", StorageIOException.StatusCode.CRITICAL);
        }
    }
}
