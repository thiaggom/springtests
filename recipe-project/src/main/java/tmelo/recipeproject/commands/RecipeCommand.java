package tmelo.recipeproject.commands;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tmelo.recipeproject.domain.Difficulty;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RecipeCommand {
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Difficulty difficulty;
	private NotesCommand notesCommand;
	private Set<CategoryCommand> categoriesCommand = new HashSet<>();
	private Set<IngredientCommand> ingredientsCommand = new HashSet<>();

}
