package tmelo.recipeproject.services;

import java.util.Set;

import tmelo.recipeproject.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	public Set<UnitOfMeasureCommand> listAllUoms();
	
}
