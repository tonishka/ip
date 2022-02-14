package yoda;

import java.util.Scanner;

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

    /**
     * Prints out the help message for user.
     */
    public String help() {
        String msg = "";
        msg += "deadline: Create a quest with a deadline.\n" +
                "Example: deadline CS2103T Quiz /2022-12-31 22:34\n";
        msg += "delete [quest_number]: Delete a quest.\n";
        msg += "event: Create an event-style quest.\n" +
                "Example: event CS2101 Oral Presentation /4-6PM, 8 Feb\n";
        msg += "help: View commands.\n";
        msg += "log: View all quests.\n";
        msg += "mark [quest_number]: Mark quest as complete.\n";
        msg += "todo: Create a simple quest.\n";
        msg += "unmark [quest_number]: Mark a complete quest as incomplete.";

        return msg;
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
