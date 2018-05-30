package tmelo.recipeproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude= {"recipe"})
@ToString(exclude= {"recipe"})
@Entity
@Table(name="TB_NOTES")
public class Notes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String recipeNotes;

	@OneToOne
	private Recipe recipe;
	
}
