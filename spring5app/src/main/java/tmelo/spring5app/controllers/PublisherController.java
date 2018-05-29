package tmelo.spring5app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tmelo.spring5app.repositories.PublisherRepository;

@Controller
public class PublisherController {

	@Autowired
	private PublisherRepository pubRepository;
	
	@RequestMapping("/publishers")
	public String getBooks(Model model) {
		model.addAttribute("publishers", pubRepository.findAll());
		return "publishers";
	}
}
