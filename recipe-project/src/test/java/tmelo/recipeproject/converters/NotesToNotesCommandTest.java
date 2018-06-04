package tmelo.recipeproject.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import tmelo.recipeproject.commands.NotesCommand;
import tmelo.recipeproject.domain.Notes;

public class NotesToNotesCommandTest {

	private static final Long ID = 1L;
	private static final String RECIPE_NOTES = "recipe notes";
	
	private NotesToNotesCommand converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new NotesToNotesCommand();
	}
	
	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyParameter() throws Exception {
		assertNotNull(converter.convert(new Notes()));
	}
	
	@Test
	public void testConvertion() throws Exception {
		
		Notes notes = new Notes();
		notes.setId(ID);
		notes.setRecipeNotes(RECIPE_NOTES);
		
		NotesCommand notesCommand = converter.convert(notes);
		
		assertNotNull(notesCommand);
		assertEquals(ID, notesCommand.getId());
		assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
		
	}
}
