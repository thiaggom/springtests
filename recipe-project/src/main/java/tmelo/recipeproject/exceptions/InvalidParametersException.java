package tmelo.recipeproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParametersException extends RuntimeException {

	public InvalidParametersException() {
		super();
	}

	public InvalidParametersException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidParametersException(String message) {
		super(message);
	}

	
	
}
