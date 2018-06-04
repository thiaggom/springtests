package tmelo.recipeproject.config;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import tmelo.recipeproject.domain.Category;
import tmelo.recipeproject.domain.Difficulty;
import tmelo.recipeproject.domain.Ingredient;
import tmelo.recipeproject.domain.Notes;
import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.domain.UnitOfMeasure;
import tmelo.recipeproject.repositories.CategoryRepository;
import tmelo.recipeproject.repositories.RecipeRepository;
import tmelo.recipeproject.repositories.UnitOfMesureRepository;

@Component
public class DatabasePush implements ApplicationListener<ContextRefreshedEvent>{

	private RecipeRepository recipeRepo;
	private CategoryRepository categoryRepo;
	private UnitOfMesureRepository unitMesureRepo;

	public DatabasePush(RecipeRepository recipeRepo, CategoryRepository categoryRepo,
			UnitOfMesureRepository unitMesureRepo) {
		super();
		this.recipeRepo = recipeRepo;
		this.categoryRepo = categoryRepo;
		this.unitMesureRepo = unitMesureRepo;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		// populating database...
		
		// carregando Unidade de Medidas
		
		//Teaspoon
		Optional<UnitOfMeasure> optTeaspoon = unitMesureRepo.findByDescription("Teaspoon");
		if (!optTeaspoon.isPresent()) {
			throw new RuntimeException("Unit of Measure 'Teaspoon' is not found!");
		}
		UnitOfMeasure uomTeaspoon = optTeaspoon.get();
		
		//Tablespoon
		Optional<UnitOfMeasure> optTablespoon = unitMesureRepo.findByDescription("Tablespoon");
		if (!optTablespoon.isPresent()) {
			throw new RuntimeException("Unit of Measure 'Tablespoon' is not found!");
		}
		UnitOfMeasure uomTablespoon = optTablespoon.get();

		//Cup
		Optional<UnitOfMeasure> optCup = unitMesureRepo.findByDescription("Cup");
		if (!optCup.isPresent()) {
			throw new RuntimeException("Unit of Measure 'Cup' is not found!");
		}
		UnitOfMeasure uomCup = optCup.get();
		
		//Pinch
//		Optional<UnitOfMeasure> optPinch = unitMesureRepo.findByDescription("Pinch");
//		if (!optPinch.isPresent()) {
//			throw new RuntimeException("Unit of Measure 'Pinch' is not found!");
//		}
//		UnitOfMeasure uomPinch = optPinch.get();
		
		//Ounce
		Optional<UnitOfMeasure> optOunce = unitMesureRepo.findByDescription("Ounce");
		if (!optOunce.isPresent()) {
			throw new RuntimeException("Unit of Measure 'Ounce' is not found!");
		}
		UnitOfMeasure uomOunce = optOunce.get();
		
		//Each
		Optional<UnitOfMeasure> optEach = unitMesureRepo.findByDescription("Each");
		if (!optEach.isPresent()) {
			throw new RuntimeException("Unit of Measure 'Each' is not found!");
		}
		UnitOfMeasure uomEach = optEach.get();
		
		//Dash
		Optional<UnitOfMeasure> optDash = unitMesureRepo.findByDescription("Dash");
		if (!optDash.isPresent()) {
			throw new RuntimeException("Unit of Measure 'Dash' is not found!");
		}
		UnitOfMeasure uomDash = optDash.get();
		
		//Pint
		Optional<UnitOfMeasure> optPint = unitMesureRepo.findByDescription("Pint");
		if (!optPint.isPresent()) {
			throw new RuntimeException("Unit of Measure 'Pint' is not found!");
		}
		UnitOfMeasure uomPint = optPint.get();
		

		// Carregando Categories
		
		//American
		Optional<Category> optCatAmerican = categoryRepo.findByCategoryName("American");
		if (!optCatAmerican.isPresent()) {
			throw new RuntimeException("Category 'American' is not present!");
		}
		Category catAmerican = optCatAmerican.get();
		
		//Italian
//		Optional<Category> optCatItalian = categoryRepo.findByCategoryName("Italian");
//		if (!optCatItalian.isPresent()) {
//			throw new RuntimeException("Category 'Italian' is not present!");
//		}
//		Category catItalian = optCatItalian.get();
		
		//Mexican
		Optional<Category> optCatMexican = categoryRepo.findByCategoryName("Mexican");
		if (!optCatMexican.isPresent()) {
			throw new RuntimeException("Category 'Mexican' is not present!");
		}
		Category catMexican = optCatMexican.get();
		
		//Fast Food
//		Optional<Category> optCatFastFood = categoryRepo.findByCategoryName("Fast Food");
//		if (!optCatFastFood.isPresent()) {
//			throw new RuntimeException("Category 'Fast Food' is not present!");
//		}
//		Category catFastFood = optCatFastFood.get();
		

		// Creating Guacamole Recipe...
		Recipe guacamole = new Recipe();
		
		guacamole.setDescription("Perfect Guacamole");
		guacamole.setPrepTime(10);
		guacamole.setCookTime(0);
		guacamole.setDifficulty(Difficulty.EASY);
		
		guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        
        guacamole.addNotes(guacNotes);
        
        guacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), uomOunce));
        guacamole.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), uomTeaspoon));
        guacamole.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), uomTablespoon));
        guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), uomTablespoon));
        guacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), uomEach));
        guacamole.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), uomTablespoon));
        guacamole.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), uomDash));
        guacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), uomEach));
        
        guacamole.getCategories().add(catAmerican);
        guacamole.getCategories().add(catMexican);

        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setServings(4);
        guacamole.setSource("Simply Recipes");
        
        //saving guacamole recipe in Database...
        recipeRepo.save(guacamole);
        
        
        //Adding Tacos Recipe...
        
        Recipe tacos = new Recipe();
        
        tacos.setDescription("Spicy Grilled Chicken Taco");
        tacos.setCookTime(9);
        tacos.setPrepTime(20);
        tacos.setDifficulty(Difficulty.MODERATE);

        tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacos.addNotes(tacoNotes);
        
        tacos.setIngredients(new HashSet<>());
        tacos.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2),uomTablespoon));
        tacos.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), uomTeaspoon));
        tacos.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), uomTeaspoon));
        tacos.addIngredient(new Ingredient("Sugar", new BigDecimal(1), uomTeaspoon));
        tacos.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), uomTeaspoon));
        tacos.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), uomEach));
        tacos.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), uomTablespoon));
        tacos.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), uomTablespoon));
        tacos.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), uomTablespoon));
        tacos.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), uomTablespoon));
        tacos.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), uomEach));
        tacos.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3),uomCup));
        tacos.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), uomEach));
        tacos.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), uomEach));
        tacos.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), uomPint));
        tacos.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), uomEach));
        tacos.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), uomEach));
        tacos.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), uomCup));
        tacos.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), uomEach));

        tacos.setCategories(new HashSet<>());
        tacos.getCategories().add(catAmerican);
        tacos.getCategories().add(catMexican);

        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setServings(6);
        tacos.setSource("Simply Recipes");

        recipeRepo.save(tacos);
	}
	
	
	
}
