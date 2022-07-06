package myExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecipeDoesNotExistException extends RuntimeException {
    public RecipeDoesNotExistException(String message) {
        super("Recipe with id " + message + " does not exist");
    }
}
