package tmelo.recipeproject.domain;

public enum Difficulty {

	EASY("Easy"),
	MODERATE("Moderate"),
	KIND_OF_HARD("Kind of Hard"),
	HARD("Hard");

	private String description;
	
	Difficulty(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
