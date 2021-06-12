package model.utility;

import exception.ModelSetterException;
import utils.RegexHandler;

import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234566L;

    private Name firstName;
    private Name secondName;
    private String pesel;
    private Email email;
    private String telephoneNumber;

    
    public Person(Name name, Name surname, String pesel, Email email, String telephoneNumber){
        this.firstName = name;
        this.secondName = surname;
        this.pesel = pesel;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public Person() { }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) throws ModelSetterException {
        if(RegexHandler.validate(RegexHandler.PESEL_PATTERN, pesel))
            throw new ModelSetterException("PESEL should be 11 digit long", pesel);
        this.pesel = pesel;
    }

    public Name getFirstName() {
        return firstName;
    }

    public void setFirstName(Name firstName) throws ModelSetterException {
        this.firstName = firstName;
    }

    public Name getSecondName() {
        return secondName;
    }

    public void setSecondName(Name secondName) throws ModelSetterException {
        this.secondName = secondName;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) throws ModelSetterException {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) throws ModelSetterException {
        if(RegexHandler.validate(RegexHandler.TELEPHONENUMBER_PATTERN, telephoneNumber))
            throw new ModelSetterException("telephoneNumber should be 9 digit long", telephoneNumber);
        this.telephoneNumber = telephoneNumber;
    }

    public String toCSVFormat(){
        return  "" + this.firstName + ',' +
                     this.secondName + ',' +
                     this.email + ',' +
                     this.pesel + ',' +
                     this.telephoneNumber;
    }

}
