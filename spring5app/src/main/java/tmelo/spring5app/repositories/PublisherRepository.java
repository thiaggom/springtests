package tmelo.spring5app.repositories;

import org.springframework.data.repository.CrudRepository;

import tmelo.spring5app.model.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

}
