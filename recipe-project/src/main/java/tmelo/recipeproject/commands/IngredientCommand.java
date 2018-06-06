package tmelo.recipeproject.commands;

import java.math.BigDecimal;

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
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand uomCommand;
}
