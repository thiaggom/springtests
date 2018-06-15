package tmelo.recipeproject.commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	
	@NotBlank
	@Size(min=3, max=255)
	private String categoryName;
}
