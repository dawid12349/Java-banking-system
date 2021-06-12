package model.utility;

import exception.ModelSetterException;
import utils.RegexHandler;

import java.io.Serial;
import java.io.Serializable;

public class Name implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234511L;

    private String name;

    public Name(String nameInput) throws ModelSetterException {
        this.setName(nameInput);
    }

    public void setName(String name) throws ModelSetterException {
        if(RegexHandler.validate(RegexHandler.NAME_PATTERN, name))
            throw new ModelSetterException("NAME SHOULD CONTAIN AT LEAST 2 CHARACTERS", name);
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
