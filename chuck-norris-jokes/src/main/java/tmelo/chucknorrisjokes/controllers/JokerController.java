package tmelo.chucknorrisjokes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tmelo.chucknorrisjokes.services.JokeService;

@Controller
@RequestMapping("/")
public class JokerController {

	private JokeService JokeService;
	
	public JokerController(JokeService jokeService) {
		JokeService = jokeService;
	}

	@RequestMapping("todayjoke")
	public String getShowJoke(Model model) {
		
		model.addAttribute("joke", this.JokeService.getRandomJoke());
		
		return "chucknorris";
	}
	
}
