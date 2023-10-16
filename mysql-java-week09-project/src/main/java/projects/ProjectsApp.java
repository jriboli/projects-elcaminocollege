package projects;

import java.sql.Connection;

import projects.dao.DbConnection;

import projects.service.DisplayService; 

public class ProjectsApp {
	private DisplayService display = new DisplayService();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = DbConnection.getConnection();
	}

	public void processUserSelection(Integer operation) {

		boolean done = false;
		while(!done){
			display.showOptions();

			switch(operation) {
				case -1:
					System.out.println("\nInvalid selection, please try again");
					break;
				case 1:
					createAndPopulateTables();
					break;
				case 2:
					createNewProject();
					break;
				case 3:
					// Do something
					break;
				default:
					System.out.println("\Invalid selection, please try again");
					break;
			}
		}
	}

}
