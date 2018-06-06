package tmelo.recipeproject.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import tmelo.recipeproject.domain.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMesureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository uomRepo;

	@Test
	public void findByDescription() throws Exception {
		Optional<UnitOfMeasure> optUom = uomRepo.findByDescription("Ounce");
		assertEquals(true, optUom.isPresent());
		assertThat(optUom.get()).hasFieldOrPropertyWithValue("description", "Ounce");
	}
	
}
