package tmelo.recipeproject.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import tmelo.recipeproject.commands.IngredientCommand;
import tmelo.recipeproject.domain.Ingredient;
import tmelo.recipeproject.domain.UnitOfMeasure;

public class IngredientToIngredientCommandTest {

	//ingredient properties...
	private static final Long ID = 1L;
	private static final String DESCRIPTION = "description";
	private static final BigDecimal AMOUNT = new BigDecimal(2.5); 
	
	//Unit of Measure properties...
	private static final Long UOM_ID = 2L;
	private static final String UOM_DESCRIPTION = "uom description";
	
	private IngredientToIngredientCommand converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}
	
	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyParameter() throws Exception {
		assertNotNull(converter.convert(new Ingredient()));
	}
	
	@Test
	public void testConvertionWithoutUOM() throws Exception {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID);
		ingredient.setDescription(DESCRIPTION);
		ingredient.setAmount(AMOUNT);
		
		IngredientCommand command = converter.convert(ingredient);
		
		assertNotNull(command);
		assertEquals(ID, command.getId());
		assertEquals(DESCRIPTION, command.getDescription());
		assertEquals(AMOUNT, command.getAmount());
		assertNull(command.getUomCommand());
	}
	
	@Test
	public void testConvertionWithUOM() throws Exception {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID);
		ingredient.setDescription(DESCRIPTION);
		ingredient.setAmount(AMOUNT);
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(UOM_ID);
		uom.setDescription(UOM_DESCRIPTION);
		ingredient.setUom(uom);
		
		IngredientCommand command = converter.convert(ingredient);
		
		assertNotNull(command);
		assertEquals(ID, command.getId());
		assertEquals(DESCRIPTION, command.getDescription());
		assertEquals(AMOUNT, command.getAmount());
		assertNotNull(command.getUomCommand());
		assertEquals(UOM_ID, command.getUomCommand().getId());
		assertEquals(UOM_DESCRIPTION, command.getUomCommand().getDescription());
	}
	
}
