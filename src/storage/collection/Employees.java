package storage.collection;

import model.Employee;

import java.io.*;
import java.util.LinkedList;

public class Employees extends LinkedList<Employee> implements StorageCollection {

        @Override
        public void loadFromFile(File file) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStreamStream = new ObjectInputStream(fileInputStream);
            while(fileInputStream.available() > 0) {
                try {
                    Employee transaction = (Employee) objectInputStreamStream.readObject();
                    transaction.setEmployeeID(getFreeID());
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
            for (Employee employee : this)
                objectOutputStream.writeObject(employee);
            objectOutputStream.flush();
            objectOutputStream.close();
        }

        @Override
        public Integer getFreeID(){
            try {
                Employee employee = this.get(this.size() - 1);
                return employee.getEmployeeID() + 1;
            } catch (IndexOutOfBoundsException e){
                return  0;
            }
        }

        public Employee findByEmail(String email){
            return this.stream()
                        .filter(employee -> employee.getEmail().toString().equals(email))
                        .findFirst()
                        .orElse(null);
        }
}



