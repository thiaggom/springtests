package tmelo.recipeproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import tmelo.recipeproject.exceptions.InvalidParametersException;
import tmelo.recipeproject.exceptions.NotFoundException;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {
		log.error("Handling not found exception");
		log.error(exception.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", exception);
		modelAndView.setViewName("404error");
		
		return modelAndView;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidParametersException.class)
	public ModelAndView handleBadRequest(Exception e) {
		log.error(e.getMessage());
		
		ModelAndView modView = new ModelAndView();
		modView.addObject("exception", e);
		
		modView.setViewName("400error");
		
		return modView;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleNumberFormatExceptionRequest(Exception e) {
		log.error(e.getMessage());
		
		ModelAndView modView = new ModelAndView();
		modView.addObject("exception", e);
		
		modView.setViewName("400error");
		
		return modView;
	}
	
}
