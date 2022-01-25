import java.io.IOException;
import java.util.Scanner;

public class Yoda {
    public static void main(String[] args) throws IOException {
        System.out.println(">> Greetings Earthling.");
        System.out.println();
        System.out.println(">> Yoda, I am.");
        System.out.println();
        System.out.println(">> Defeat the Dark lord of Sith, you must.");
        System.out.println();
        System.out.println(">> On this journey today must you embark.");
        System.out.println();
        System.out.println(">> May the Force be with you, brave Jedi.");
        System.out.println();
        //pause();
        System.out.println("******************************************");
        System.out.println(">> Type help to view commands.");
        System.out.println("******************************************");

        Storage storage = new Storage();
        QuestList questList = new QuestList(storage.load());
        run(storage, questList);
    }

     private static void run(Storage storage, QuestList questList) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            String input = sc.nextLine();
            String[] command = input.split(" ");

            if (command[0].equals("bye")) {
                sc.close();
                bye(storage, questList);
                break;
            } else if (command[0].equals("log")) {
                log(questList);
            } else if (command[0].equals("mark")) {
                markDone(questList,command);
            } else if (command[0].equals("unmark")) {
                markUndone(questList, command);
            } else if (command[0].equals("todo")){
                todo(command, questList);
            } else if (command[0].equals("event")) {
                event(input, questList);
            } else if (command[0].equals("deadline")) {
                deadline(input, questList);
            } else if (command[0].equals("delete")) {
                delete(command, questList);
            } else if (command[0].equals("help")) {
                help();
            } else {
                System.out.println("Yoda knows not what this means.");
            }
        }
    }

    private static void pause() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private static void help() {
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

    private static void log(QuestList questList) {
        System.out.println("To defeat the dark lord, " +
                "following quests must you finish, Jedi:");
        System.out.print(questList.toString());
    }

    private static void todo(String[] command, QuestList questList) {
        try {
            String argument = "";
            if (command.length <= 1) {
                throw new YodaException("Missing description.");
            }
            for (int i = 1; i < command.length; i++) {
                argument += command[i] + " ";
            }
            ToDo toDo = new ToDo(argument, 0);
            questList.addQuest(toDo);
            System.out.println("New Quest added:" + toDo.toString());
            System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
        } catch (YodaException ye) {
            System.out.println("Description of a todo must not be empty, Jedi.");
        }
    }

    private static void event(String input, QuestList questList) {
        try {
            String[] period = input.split("/");
            String[] desc = period[0].split(" ");
            String s = "";
            if (desc.length <= 1) throw new YodaException("No event description.");
            for (int i = 1; i < desc.length; i++) {
                s += desc[i] + " ";
            }
            Event e = new Event(s, period[1], 0);
            questList.addQuest(e);
            System.out.println("New Quest added:" + e.toString());
            System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please enter the command in the correct format.");
        } catch (YodaException ye) {
            System.out.println("Please enter a description for your quest.");
        }
    }

    private static void deadline(String input, QuestList questList) {
        try {
            String[] period = input.split("/");
            String[] desc = period[0].split(" ");
            String s = "";
            if (desc.length <= 1) throw new YodaException("No deadline description.");
            for (int i = 1; i < desc.length; i++) {
                s += desc[i] + " ";
            }
            Deadline d = new Deadline(s, period[1], 0);
            questList.addQuest(d);
            System.out.println("New Quest added:" + d.toString());
            System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please enter the command in the correct format.");
        } catch (YodaException ye) {
            System.out.println("Please enter a description for your quest.");
        }
    }

    private static void markDone(QuestList questList, String[] command) {
        int questID;
        try {
            questID = Integer.parseInt(command[1]) - 1;
            questList.getQuest(questID).completeQuest();
            System.out.println("Done: " + questList.getQuest(questID).toString());
            System.out.println("For a quest accomplished you I congratulate.");
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid quest number.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid quest number.");
        }
    }

    private static void markUndone(QuestList questList, String[] command) {
        int questID;
        try {
            questID = Integer.parseInt(command[1]) - 1;
            questList.getQuest(questID).incompleteQuest();
            System.out.println("Not done: " + questList.getQuest(questID).toString());
            System.out.println("Soldier on brave Jedi.");
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid quest number.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid quest number.");
        }
    }

    private static void delete(String[] command, QuestList questList) {
        try {
            int questID = Integer.parseInt(command[1]) - 1;
            Quest q = questList.deleteQuest(questID);
            System.out.println("Quest removed: " + q.toString());
            System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid quest number.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid quest number.");
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

    private static void bye(Storage storage, QuestList questList) {
        storage.save(questList);
        type("Hrrmmm. You, Jedi, farewell I bid.");
        System.out.println();
        System.out.println("******************************************");
    }
}
