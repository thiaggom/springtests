package tmelo.recipeproject.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
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
	
	@OneToOne(fetch=FetchType.EAGER)
	private UnitOfMeasure uom;
	
	@ManyToOne
	private Recipe recipe;

	public Ingredient() {}
	
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
		this.description = description;
		this.amount = amount; 
		this.uom = uom;              
	}
	
    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }	
}
