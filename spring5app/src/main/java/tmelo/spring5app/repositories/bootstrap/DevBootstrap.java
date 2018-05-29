package tmelo.spring5app.repositories.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import tmelo.spring5app.model.Author;
import tmelo.spring5app.model.Book;
import tmelo.spring5app.model.Publisher;
import tmelo.spring5app.repositories.AuthorRepository;
import tmelo.spring5app.repositories.BookRepository;
import tmelo.spring5app.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PublisherRepository pubRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		initData();
	}

	private void initData() {
		
		// populating database...
		
		// creating publishers...
		Publisher pubEua = new Publisher("Harper Collins", "Somewhere in EUA");
		pubRepository.save(pubEua);
		
		Publisher pubCad = new Publisher("Workx", "Somewhere in Canada");
		pubRepository.save(pubCad);
		
		// Eric and gordon
		Author eric = new Author("Eric", "Evans");
		Book ericAndGordonBook = new Book("Domain Driven Desing", "1234", pubEua);
		eric.getBooks().add(ericAndGordonBook);
		ericAndGordonBook.getAuthors().add(eric);
		authorRepository.save(eric);
		bookRepository.save(ericAndGordonBook);

		// saving gordons data
		
		Author gordon = new Author("Gordon", "Madson");
		gordon.getBooks().add(ericAndGordonBook);
		ericAndGordonBook.getAuthors().add(gordon);
		authorRepository.save(gordon);
		bookRepository.save(ericAndGordonBook);

		//Rod
		Author rod = new Author("Rod", "Johnson");
		Book rodBook = new Book("J2EE Development wihtout EJB", "23444", pubCad);
		rod.getBooks().add(rodBook);
		rodBook.getAuthors().add(rod);
		authorRepository.save(rod);
		bookRepository.save(rodBook);
		
		//listing books of the publisher...
//		pubEua = pubRepository.findById(pubEua.getId());
	}

	
}
