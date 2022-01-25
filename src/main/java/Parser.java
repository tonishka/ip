public class Parser {
    String rawCommand;
    public Parser(String rawCommand) {
        this.rawCommand = rawCommand;
    }
    public void parse() {
        String[] command = rawCommand.split(" ");
    }
}
