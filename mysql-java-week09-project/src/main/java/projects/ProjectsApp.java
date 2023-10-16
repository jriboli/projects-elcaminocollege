package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.DbService;

public class ProjectsApp {
	private Scanner scanner = new Scanner(System.in);
	private DbService dbService = new DbService();

	private List<String> operations = List.of(
			"1) Create and populate all tables",
			"2) Add a project"
	);

	public static void main(String[] args) {
		new ProjectsApp().processUserSelections();
	}

	private void processUserSelections() {
		boolean done = false;

		while (!done) {
			int operation = getUserSelection();
			try {
				switch (operation) {
				case -1:
					done = exitMenu();
					break;
				case 1:
					createTables();
					break;
				case 2:
					addProject();
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

	private void addProject() {
		// TODO Auto-generated method stub
		String name = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getBigDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getBigDecimalInput("Enter the actual hours");
		Integer	difficulty = getIntInput("Enter the poject difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
		
		project.setProjectName(name);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbRecipe = dbService.addProject(project);
		System.out.println("YOu added this recipe: \n" + dbRecipe);
	}

	private void createTables() {
		dbService.createAndPopulateTables();
		System.out.println("\nTables created and populated.");
	}

	private boolean exitMenu() {
		System.out.println("\nExiting the menu. TTFN!");
		return true;
	}

	private int getUserSelection() {
		printOperations();
		Integer op = getIntInput("\nEnter an operation number (press Enter to quit)");

		return Objects.isNull(op) ? -1 : op;
	}

	private Integer getIntInput(String prompt) {
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

	private BigDecimal getBigDecimalInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}

		try {
			//double placeHolder = Double.parseDouble(input);
			//return BigDecimal.valueOf(placeHolder);
			
			return new BigDecimal(input).setScale(2);
		} catch (Exception e) {
			throw new DbException(input + " is not a valid decimal number.");
		}
	}

	private String getStringInput(String prompt) {
		System.out.print(prompt + ": ");
		String line = scanner.nextLine();

		return line.isBlank() ? null : line.trim();
	}

	private void printOperations() {
		System.out.println();
		System.out.println("Here is what you can do:");
//		for(String operation : operations) {
//			System.out.println(operation);
//		}
		operations.forEach(operation -> System.out.println("\t" + operation));
	}

}
