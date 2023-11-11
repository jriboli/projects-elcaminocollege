package recipes;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import recipes.entity.Ingredient;
import recipes.entity.Recipe;
import recipes.entity.Step;
import recipes.entity.Unit;
import recipes.exception.DbException;
import recipes.service.RecipeService;

public class Recipes {
	private Scanner sc = new Scanner(System.in);
	private RecipeService rs = new RecipeService();
	private Recipe curRecipe;

	private List<String> operations = List.of(
			"1) Create and populate all tables",
			"2) Add a recipe",
			"3) List recipes",
			"4) Select a recipe",
			"5) Add ingredient to current recipe",
			"6) Add step to current recipe"
	);

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub
		// Connection conn = DbConnection.getConnection();

		new Recipes().displayMenu();
	}

	private void displayMenu() {
		// TODO Auto-generated method stub
		boolean done = false;

		while (!done) {
			int operation = getOperation();
			try {
				switch (operation) {
				case -1:
					done = exitMenu();
					break;
				case 1:
					createTables();
					break;
				case 2:
					addRecipe();
					break;
				case 3:
					listRecipes();
					break;
				case 4:
					setCurrentRecipe();
					break;
				case 5:
					addIngredient();
					break;
				case 6:
					addStep();
					break;
				default:
					System.out.println("\n" + operation + " is not valid. Try again");
					break;
				}
			} catch (Exception e) {
				System.out.println("\nError: " + e.toString() + " Try again");
			}
		}
	}

	private void addStep() {
		// TODO Auto-generated method stub
		if(Objects.isNull(curRecipe)) {
			System.out.println("\nPlease select a recipe first. ");
			return;
		}
		
		String stepText = getStringInput("Enter the step instructions");
		
		if(Objects.nonNull(stepText)) {
			Step step = new Step();
			step.setRecipeId(curRecipe.getRecipeId());
			step.setStepText(stepText);
			
			rs.addStep(step);
			curRecipe = rs.fetchRecipeById(step.getRecipeId());
		}		
	}

	private void addIngredient() {
		// TODO Auto-generated method stub
		if(Objects.isNull(curRecipe)) {
			System.out.println("\nPlease select a recipe first. ");
			return;
		}
		
		String name = getStringInput("Enter the ingredient name");
		String instruction = getStringInput("Enter an instruction in any (like \finely chopped\"");
		Double inputAmount = getDoubleInput("Enter the ingredient amount (like 0.25)");
		List<Unit> units = rs.fetchUnits();
		
		BigDecimal amount = Objects.isNull(inputAmount) ? null : new BigDecimal(inputAmount).setScale(2);
		
		System.out.println("Units: ");
		units.forEach(unit -> System.out.println(" " + unit.getUnitId() + ": " 
				+ unit.getUnitNameSingular() + "(" + unit.getUnitNamePlural() + ")"));
		
		Integer unitId = getIntInput("Enter a unit ID (press Enter for none)");
		
		Unit unit = new Unit();
		unit.setUnitId(unitId);
		
		Ingredient ingredient = new Ingredient();
		ingredient.setRecipeId(curRecipe.getRecipeId());
		ingredient.setUnitId(unit);
		ingredient.setIngredientName(name);
		ingredient.setInstruction(instruction);
		ingredient.setAmount(amount);
		
		rs.addIngredient(ingredient);
		curRecipe = rs.fetchRecipeById(ingredient.getRecipeId());
	}

	private void setCurrentRecipe() {
		List<Recipe> recipes = listRecipes();
		
		Integer recipeId = getIntInput("Select a recipe ID");
		
		curRecipe = null;
		
		for(Recipe recipe : recipes) {
			if(recipe.getRecipeId().equals(recipeId)) {
				curRecipe = rs.fetchRecipeById(recipeId);
				break;
			}
		}
		
		if(Objects.isNull(curRecipe)) {
			System.out.println("Not FOUND.");
		}
	}

	private List<Recipe> listRecipes() {
		List<Recipe> recipes = rs.fetchRecipes();
		
		System.out.println("\nRecipes:");
		
		recipes.forEach(recipe -> System.out.println("    " + recipe.getRecipeId() + ": " + recipe.getRecipeName()));
		
		return recipes;
		
	}

	private void addRecipe() {
		// TODO Auto-generated method stub
		String name = getStringInput("Enter the recipe name");
		String notes = getStringInput("Enter the recipe notes");
		Integer numOfServings = getIntInput("Enter the number of servings");
		Integer	prepMinutes = getIntInput("Enter prep time in minutes");
		Integer	cookMinutes = getIntInput("Enter cook time in minutes");
		
		LocalTime prepTime = minutesToLocalTime(prepMinutes);
		LocalTime cookTime = minutesToLocalTime(cookMinutes);
		
		Recipe recipe = new Recipe();
		
		recipe.setRecipeName(name);
		recipe.setNotes(notes);
		recipe.setNumOfServings(numOfServings);
		recipe.setPrepTime(prepTime);
		recipe.setCookTime(cookTime);
		
		Recipe dbRecipe = rs.addRecipe(recipe);
		System.out.println("YOu added this recipe: \n" + dbRecipe);
		
		curRecipe = rs.fetchRecipeById(dbRecipe.getRecipeId());
	}

	private LocalTime minutesToLocalTime(Integer numMinutes) {
		// TODO Auto-generated method stub
		int min = Objects.isNull(numMinutes) ? 0 : numMinutes;
		int hours = min / 60;
		int minutes = min % 60;
		
		return LocalTime.of(hours, minutes);
	}

	private void createTables() {
		// TODO Auto-generated method stub
		rs.createAndPopulateTables();
		System.out.println("\nTables created and populated.");
	}

	private boolean exitMenu() {
		// TODO Auto-generated method stub
		System.out.println("\nExiting the menu. TTFN!");
		return true;
	}

	private int getOperation() {
		printOperations();
		Integer op = getIntInput("\nEnter an operation number (press Enter to quit)");

		return Objects.isNull(op) ? -1 : op;
	}

	private Integer getIntInput(String prompt) {
		// TODO Auto-generated method stub
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}

		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private Double getDoubleInput(String prompt) {
		// TODO Auto-generated method stub
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}

		try {
			return Double.parseDouble(input);
		} catch (Exception e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private String getStringInput(String prompt) {
		// TODO Auto-generated method stub
		System.out.print(prompt + ": ");
		String line = sc.nextLine();

		return line.isBlank() ? null : line.trim();
	}

	private void printOperations() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Here is what you can do:");
//		for(String operation : operations) {
//			System.out.println(operation);
//		}
		operations.forEach(operation -> System.out.println("\t" + operation));
	}

}
