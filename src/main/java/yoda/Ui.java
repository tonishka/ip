package yoda;

import yoda.QuestList;
import yoda.Storage;

public class Ui {
    public Ui() {}

    public void welcome() {
        type(">> Greetings Earthling.");
        System.out.println();
        type(">> Yoda, I am.");
        System.out.println();
        type(">> Defeat the Dark lord of Sith, you must.");
        System.out.println();
        type(">> On this journey today must you embark.");
        System.out.println();
        type(">> May the Force be with you, brave Jedi.");
        System.out.println();
        pause();
        System.out.println("******************************************");
        System.out.println(">> Type help to view commands.");
        System.out.println("******************************************");
    }

    private void pause() {
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
    }

    public void help() {
        System.out.println("bye: Exit.");
        System.out.println("deadline: Create a quest with a deadline.\n" +
                "Example: deadline CS2103T Quiz /Monday 2359hrs");
        System.out.println("delete: Delete a quest.");
        System.out.println("event: Create an event-style quest.\n" +
                "Example: event CS2101 Oral Presentation /4-6PM, 8 Feb");
        System.out.println("help: View commands.");
        System.out.println("log: View all quests.");
        System.out.println("mark: Mark quest as complete.");
        System.out.println("todo: Create a simple quest.");
        System.out.println("unmark: Mark a complete quest as incomplete.");
    }

    public void bye(Storage storage, QuestList questList) {
        storage.save(questList);
        type("Hrrmmm. You, Jedi, farewell I bid.");
        System.out.println();
        System.out.println("******************************************");
    }
}
