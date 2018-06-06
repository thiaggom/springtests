package tmelo.recipeproject.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude= {"recipes"})
@ToString(exclude= {"recipes"})
@Entity
@Table(name="TB_CATEGORIES")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String categoryName;
	
	@ManyToMany(mappedBy="categories")
	private Set<Recipe> recipes;

}
