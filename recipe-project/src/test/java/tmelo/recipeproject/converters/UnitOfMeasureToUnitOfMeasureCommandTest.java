package tmelo.recipeproject.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import tmelo.recipeproject.commands.UnitOfMeasureCommand;
import tmelo.recipeproject.domain.UnitOfMeasure;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

	private static final Long LONG_VALUE = 1L;
	private static final String DESCRIPTION = "description";

	private static UnitOfMeasureToUnitOfMeasureCommand converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyParameter() throws Exception {
		assertNotNull(converter.convert(new UnitOfMeasure()));
	}
	
	@Test
	public void testConvertion() throws Exception{
		
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(LONG_VALUE);
		uom.setDescription(DESCRIPTION);
		
		//convert
		UnitOfMeasureCommand uomCommand = converter.convert(uom);
		
		assertNotNull(uomCommand);
		assertEquals(LONG_VALUE, uomCommand.getId());
		assertEquals(DESCRIPTION, uomCommand.getDescription());
		
	}
	
}
