package tmelo.spring5app.repositories;

import org.springframework.data.repository.CrudRepository;

import tmelo.spring5app.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long>{

}
