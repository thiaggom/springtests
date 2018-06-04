package tmelo.recipeproject.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import tmelo.recipeproject.commands.IngredientCommand;
import tmelo.recipeproject.commands.UnitOfMeasureCommand;
import tmelo.recipeproject.domain.Ingredient;

public class IngredientCommandToIngredientTest {
	//ingredient properties...
	private static final Long ID = 1L;
	private static final String DESCRIPTION = "description";
	private static final BigDecimal AMOUNT = new BigDecimal(2.5); 
	
	//Unit of Measure properties...
	private static final Long UOM_ID = 2L;
	private static final String UOM_DESCRIPTION = "uom description";

	private IngredientCommandToIngredient converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}
	
	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyParameter() throws Exception {
		assertNotNull(converter.convert(new IngredientCommand()));
	}
	
	@Test
	public void testConvertionWithoutUOM() throws Exception {
		IngredientCommand command = new IngredientCommand();
		command.setId(ID);
		command.setDescription(DESCRIPTION);
		command.setAmount(AMOUNT);
		command.setUomCommand(null);
		
		Ingredient ingredient = converter.convert(command);
		
		assertNotNull(ingredient);
		assertEquals(ID, ingredient.getId());
		assertEquals(DESCRIPTION, ingredient.getDescription());
		assertEquals(AMOUNT, ingredient.getAmount());
		assertNull(ingredient.getUom());
	}

	@Test
	public void testConvertionWithUOM() throws Exception {
		IngredientCommand command = new IngredientCommand();
		command.setId(ID);
		command.setDescription(DESCRIPTION);
		command.setAmount(AMOUNT);
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(UOM_ID);
		uomCommand.setDescription(UOM_DESCRIPTION);
		command.setUomCommand(uomCommand);
		
		Ingredient ingredient = converter.convert(command);
		
		assertNotNull(ingredient);
		assertEquals(ID, ingredient.getId());
		assertEquals(DESCRIPTION, ingredient.getDescription());
		assertEquals(AMOUNT, ingredient.getAmount());
		
		assertNotNull(ingredient.getUom());
		assertEquals(UOM_ID, ingredient.getUom().getId());
		assertEquals(UOM_DESCRIPTION, ingredient.getUom().getDescription());
	}
}
