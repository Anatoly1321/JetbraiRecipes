package myExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserWithSpecifiedEmailAlreadyExistException extends RuntimeException {
    public UserWithSpecifiedEmailAlreadyExistException(String mes) {super(mes);}
}
