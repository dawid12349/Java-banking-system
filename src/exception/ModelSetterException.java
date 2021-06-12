package exception;

public class ModelSetterException extends Exception {
    private final String wrongValue;

    public ModelSetterException(String message, String wrongValue){
        super(message);
        this.wrongValue = wrongValue;
    }

    public String getText(){
        return "WRONG INPUT VALUE:";
    }

    public String getWrongValue() {
        return wrongValue;
    }


    public String getPresetErrorMessage(){
        return getText() + getWrongValue() + "\n" + getMessage();
    }

}
