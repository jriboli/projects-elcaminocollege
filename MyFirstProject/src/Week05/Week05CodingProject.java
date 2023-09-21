package Week05;

public class Week05CodingProject{

	public static void main(String[] args) {
		Logger aLog = new AstriskLogger();
		Logger bLog = new SpacedLogger();
		
		testOutLog(aLog);
		testOutLog(bLog);
		
	}
	
	public static void testOutLog(Logger log) {
		log.log("Welcome Rocket");
		log.error("Everything is going to blow!");
	}
}
