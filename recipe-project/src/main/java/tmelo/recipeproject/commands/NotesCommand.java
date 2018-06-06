package tmelo.recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NotesCommand {
	private Long id;
	private String recipeNotes;
}
