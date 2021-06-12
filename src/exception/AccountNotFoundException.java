package exception;

public class AccountNotFoundException extends Exception {
    private final Integer userID;
    public AccountNotFoundException(String message, Integer userID){
        super(message);
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

}
