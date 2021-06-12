package exception;

public class EmployeeNotFoundException extends Exception {
    private final String email;
    public EmployeeNotFoundException(String message, String email){
        super(message);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPresetErrorMessage(){
        return "Employee of email: " + getEmail() + " not found";
    }

}
