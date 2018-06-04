package tmelo.recipeproject.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import tmelo.recipeproject.commands.NotesCommand;
import tmelo.recipeproject.domain.Notes;

public class NotesCommandToNotesTest {

	private static final Long ID = 1L;
	private static final String RECIPE_NOTES = "recipe notes";

	private NotesCommandToNotes converter;
	
	@Before
	public void setUp() throws Exception {
		this.converter = new NotesCommandToNotes();
	}
	
	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyParameter() throws Exception {
		assertNotNull(converter.convert(new NotesCommand()));
	}
	
	@Test
	public void testConvertion() throws Exception {
		
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(ID);
		notesCommand.setRecipeNotes(RECIPE_NOTES);
		
		Notes notes = converter.convert(notesCommand);
		
		assertNotNull(notes);
		assertEquals(ID, notes.getId());
		assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
		
	}
	
}
