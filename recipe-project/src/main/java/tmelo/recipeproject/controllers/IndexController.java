package tmelo.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({"","/","/index","/home"})
	public String getIndexPage() {
		System.out.println("Some message to say 2...");
		return "index";
	}
	
}
