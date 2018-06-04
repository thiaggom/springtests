package tmelo.recipeproject.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import tmelo.recipeproject.commands.CategoryCommand;
import tmelo.recipeproject.domain.Category;

public class CategoryToCategoryCommandTest {

	private final Long ID = 1L;
	private final String CATEGORY_NAME = "category";
	
	private CategoryToCategoryCommand converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new CategoryToCategoryCommand();
	}
	
	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyParameter() throws Exception {
		assertNotNull(converter.convert(new Category()));
	}
	
	@Test
	public void testConvertion() throws Exception {
		Category category = new Category();
		category.setId(ID);
		category.setCategoryName(CATEGORY_NAME);
		
		CategoryCommand command = converter.convert(category);
		
		assertNotNull(command);
		assertEquals(ID, command.getId());
		assertEquals(CATEGORY_NAME, command.getCategoryName());
	}
	
}
