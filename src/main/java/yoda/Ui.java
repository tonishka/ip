package yoda;

/**
 * Encapsulates the user interface for the chatbot.
 * @author Tonishka Singh
 */
public class Ui {
    public Ui() {}

    /**
     * Prints the welcome message when user launches the application.
     */
    public void welcome() {
        System.out.println(">> Greetings Earthling.");
        System.out.println(">> Yoda, I am.");
        System.out.println(">> Defeat the Dark lord of Sith, you must.");
        System.out.println(">> On this journey today must you embark.");
        System.out.println(">> May the Force be with you, brave Jedi.");
        System.out.println("******************************************");
        System.out.println(">> Type help to view commands.");
        System.out.println("******************************************");
    }

    /**private void pause() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private static void type(String text) {
        int i;
        for(i = 0; i < text.length(); i++) {
            System.out.print("" + text.charAt(i));
            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }**/

    /**
     * Prints out the help message for user.
     */
    public void help() {
        System.out.println("bye: Exit.");
        System.out.println("deadline: Create a quest with a deadline.\n" +
                "Example: deadline CS2103T Quiz /2022-12-31");
        System.out.println("delete: Delete a quest.");
        System.out.println("event: Create an event-style quest.\n" +
                "Example: event CS2101 Oral Presentation /4-6PM, 8 Feb");
        System.out.println("help: View commands.");
        System.out.println("log: View all quests.");
        System.out.println("mark: Mark quest as complete.");
        System.out.println("todo: Create a simple quest.");
        System.out.println("unmark: Mark a complete quest as incomplete.");
    }

    /**
     * Terminates the program and writes back data to hard disk.
     * @param storage Storage where the data is located.
     * @param questList Data to write back to file.
     */
    public void bye(Storage storage, QuestList questList) {
        storage.save(questList);
        System.out.println("Hrrmmm. You, Jedi, farewell I bid.");
        System.out.println();
        System.out.println("******************************************");
    }
}
