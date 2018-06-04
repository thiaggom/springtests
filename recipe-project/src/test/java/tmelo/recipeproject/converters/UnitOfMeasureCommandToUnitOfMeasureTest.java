package tmelo.recipeproject.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import tmelo.recipeproject.commands.UnitOfMeasureCommand;
import tmelo.recipeproject.domain.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	private static final Long LONG_VALUE = 1L;
	private static final String DESCRIPTION = "description";
	
	
	private UnitOfMeasureCommandToUnitOfMeasure converter;
	
	@Before
	public void init() throws Exception {
		this.converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}
	
	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}
	
	@Test
	public void testConvertion() throws Exception {
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(LONG_VALUE);
		uomCommand.setDescription(DESCRIPTION);
		
		//convert...
		UnitOfMeasure uom = converter.convert(uomCommand);
		
		assertNotNull(uom);
		assertEquals(LONG_VALUE, uom.getId());
		assertEquals(DESCRIPTION, uom.getDescription());
	}
	
}
