package projects;

public class DisplayService {
    private static final List<String> OPTIONS = List.of{
        "1) Create and populate tables.",
        "2) Create a project",
        "3) Something Something"
    };

    private Scanner scanner = new Scanner(System.in);

    public void showOptions() {
        OPTIONS.forEach(op -> System.out.println(op));
    }

    public Integer getIntegerInput(String content) {
        String userInput = getStringInput(content);

        try{
            return Integer.
        } catch (Exception e) {
            throw new DbException(e);
        }
    }

    public String getStringInput(String content) {
        System.out.println(content + ": ");
        String userInput = scanner.nextLine();

        return userInput;
    }

}