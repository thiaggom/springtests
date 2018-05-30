package tmelo.recipeproject.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude= {"recipe"})
@ToString(exclude= {"recipe"})
@Entity
@Table(name="TB_INGREDIENTS")
public class Ingredient {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal amount;
	
	@OneToOne
	private UnitOfMeasure uom;
	
	@ManyToOne
	private Recipe recipe;
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
		this.description = description;
		this.amount = amount; 
		this.uom = uom;              
	}
	
}
