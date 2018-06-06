package tmelo.recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CategoryCommand {
	private Long id;
	private String categoryName;
}
