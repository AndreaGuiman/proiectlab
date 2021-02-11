package exception;

import java.io.IOException;

public class IncorrectFileNameException extends IOException {
    public IncorrectFileNameException(){
        super("Incorrect FXML file name or path!");
    }

    public IncorrectFileNameException(String exceptionMessage){
        super(exceptionMessage);
    }
}
