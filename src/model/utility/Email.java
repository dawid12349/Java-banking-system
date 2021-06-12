package model.utility;

import exception.ModelSetterException;
import utils.RegexHandler;

import java.io.Serial;
import java.io.Serializable;

public class Email implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234598L;

    private String email;

    public Email(String email) throws ModelSetterException {
        this.setEmail(email);
    }

    public void setEmail(String email) throws ModelSetterException {
        if(RegexHandler.validate(RegexHandler.EMAIL_PATTERN, email))
            throw new ModelSetterException("EMAIL SHOULD CONTAIN @, mail and domain name", email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return email;
    }
}
