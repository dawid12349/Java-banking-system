package storage.collection;

import model.Transaction;

import java.io.*;
import java.util.LinkedList;

public class Transactions extends LinkedList<Transaction> implements StorageCollection {

    @Override
    public void loadFromFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStreamStream = new ObjectInputStream(fileInputStream);
        while(fileInputStream.available() > 0) {
            try {
               Transaction transaction = (Transaction) objectInputStreamStream.readObject();
               transaction.setTransactionID(getFreeID());
               this.add(transaction);
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

        for (Transaction transaction : this)
            objectOutputStream.writeObject(transaction);

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    @Override
    public Integer getFreeID(){
        try {
            Transaction transaction = this.get(this.size() - 1);
            return transaction.getTransactionID() + 1;
        } catch (IndexOutOfBoundsException e){
            return  0;
        }
    }



}
