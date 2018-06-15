package tmelo.recipeproject.commands;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class IngredientCommand {
	private Long id;
	private Long recipeId;
	
	@NotBlank
	@Size(min=3, max=255)
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand uomCommand;
}
