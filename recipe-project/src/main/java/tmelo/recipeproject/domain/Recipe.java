package tmelo.recipeproject.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="TB_RECIPES")
public class Recipe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	
	@CreationTimestamp()
	private Date creationTime;
	
	@UpdateTimestamp
	private Date lasUpdate;
	
	@Lob
	private String directions;
	
	@Enumerated(value=EnumType.STRING)
	private Difficulty difficulty;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Notes notes;

	@ManyToMany()
	@JoinTable(name="TB_RECIPE_CATEGORY", 
		joinColumns = @JoinColumn(name="recipe_id"),
		inverseJoinColumns = @JoinColumn(name="category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="recipe")
	private Set<Ingredient> ingredients = new HashSet<>();
	
	@Lob
	private Byte[] image;
	
	public void addNotes(Notes notes) {
		this.notes = notes;
		notes.setRecipe(this);
	}

	public void addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
	}
	
}
