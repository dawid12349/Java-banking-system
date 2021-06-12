package exception;


import java.io.IOException;

public class StorageIOException  extends Exception {
    public enum StatusCode{
        CRITICAL,
        CONTINUE,
    }
    private final StatusCode statusCode;
    public StorageIOException(String message, StatusCode statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
