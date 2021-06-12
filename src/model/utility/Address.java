package model.utility;

import exception.ModelSetterException;
import utils.RegexHandler;

import java.io.*;

public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    private String state;
    private String city;
    private String streetAddress;


    public Address(String state, String city, String streetAddress){
        this.state = state;
        this.city = city;
        this.streetAddress = streetAddress;
    }

    public Address() {}

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setCity(String city) throws ModelSetterException {
        if(RegexHandler.validate(RegexHandler.CITY_PATTERN, city))
            throw new ModelSetterException("Invalid city format", city);
        this.city = city;
    }

    public void setState(String state) throws ModelSetterException {
        if(RegexHandler.validate(RegexHandler.COUNTY_PATTERN, state)){
            throw new ModelSetterException("Invalid Country format", state);
        }
        this.state = state;
    }

    public void setStreetAddress(String streetAddress) throws ModelSetterException {
        if(RegexHandler.validate(RegexHandler.STREETADRESS_PATTERN, streetAddress))
            throw new ModelSetterException("Invalid streetAddress format (name number)", streetAddress);
        this.streetAddress = streetAddress;
    }

    @Override
    public String toString(){
        return  ""  +
                this.state + ", " +
                this.city + ", " +
                this.streetAddress;
    }


}
