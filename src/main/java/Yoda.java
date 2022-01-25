import java.io.IOException;
import java.util.Scanner;

public class Yoda {
    private Ui ui;
    private Storage storage;
    private QuestList questList;

    public Yoda() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.questList = new QuestList(this.storage.load());
    }

    public static void main(String[] args) throws IOException {
        new Yoda().run();
    }

     private void run() {
        ui.welcome();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            String input = sc.nextLine();
            String[] command = input.split(" ");

            if (command[0].equals("bye")) {
                sc.close();
                ui.bye(storage, questList);
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
                ui.help();
            } else {
                System.out.println("Yoda knows not what this means.");
            }
        }
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
}
