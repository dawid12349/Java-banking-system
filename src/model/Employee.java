package model;

import exception.ModelSetterException;
import model.utility.Email;
import model.utility.Name;
import utils.RegexHandler;

import java.io.*;

public class Employee implements Serializable{

    @Serial
    private static final long serialVersionUID = 1234577L;

    private Integer employeeID;
    private Name firstName;
    private Name secondName;
    private Email email;
    private String password;

    public Employee(Name name, Name surname, Email email, String password) throws ModelSetterException {
        this.setFirstName(name);
        this.setSecondName(surname);
        this.setEmail(email);
        this.setPassword(password);
    }

    public Employee(){}

    public Integer getEmployeeID() {
        return employeeID;
    }

    public Name getFirstName() {
        return firstName;
    }

    public Name getSecondName() {
        return secondName;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public void setFirstName(Name firstName) throws ModelSetterException {
        this.firstName = firstName;
    }

    public void setSecondName(Name secondName) throws ModelSetterException {
        this.secondName = secondName;
    }

    public void setEmail(Email email) throws ModelSetterException {
        this.email = email;
    }

    public void setPassword(String password) throws ModelSetterException {
        if(RegexHandler.validate(RegexHandler.PASSWORD_PATTERN, password))
            throw new ModelSetterException("Password should contain:\n- 8 letters\n- at least 1 letter and character", password);
        this.password = password;
    }

    public String getCredientials(){
        return "" + this.getFirstName() + " " + this.getSecondName();
    }


}
