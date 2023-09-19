package Week05;

public class Week05_OOP_MenuApp {
	
	public static void main(String[] args) {
		
		Player p1 = new Player("Rocket", "Full Back", "Running Fast");
		Player p2 = new Player("Drax", "Line Man", "Like a bolder");
		Player p3 = new Player("Gamora", "Nose Tackle", "Agile and Sneaky");
		
		Team team = new Team("The Gaurdians");
		
		team.addPlayer(p1);
		team.addPlayer(p2);
		team.addPlayer(p3);		
		team.describe();
		
		System.out.println("------------------------------------------------");
		
		team.removePlayer("Drax");
		team.describe();
		
	}

}
