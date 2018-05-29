package tmelo.spring5app.repositories;

import org.springframework.data.repository.CrudRepository;

import tmelo.spring5app.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
