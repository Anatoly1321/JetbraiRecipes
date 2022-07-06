package myExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongInputDataException extends RuntimeException {

    public WrongInputDataException() {
        super("Incorrect input data");
    }
}
