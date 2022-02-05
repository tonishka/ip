package yoda;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the chatbot.
 * @author Tonishka Singh
 */
public class Yoda {
    private Ui ui;
    private Storage storage;
    private QuestList questList;

    public Yoda() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.questList = new QuestList(this.storage.load());
    }

    public static void main(String[] args) {
        new Yoda().run();
    }

     private void run() {
        ui.welcome();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        while (true) {
            System.out.print(">> ");
            String input = sc.nextLine();
            String[] args = parser.clean(input);
            String command = parser.getCommand(args);

            if (command.equals("bye")) {
                sc.close();
                ui.bye(storage, questList);
                break;
            } else if (command.equals("log")) {
                log();
            } else if (command.equals("mark")) {
                try {
                    int questID = parser.parseMark(args);
                    markDone(questID);
                    storage.save(questList);
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid quest number.");
                } catch (IndexOutOfBoundsException ibe) {
                    System.out.println("Please enter a valid quest number.");
                }
            } else if (command.equals("unmark")) {
                try {
                    int questID = parser.parseMark(args);
                    markUndone(questID);
                    storage.save(questList);
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid quest number.");
                } catch (IndexOutOfBoundsException ibe) {
                    System.out.println("Please enter a valid quest number.");
                }
            } else if (command.equals("todo")){
                try {
                    String desc = parser.getDescriptionToDo(args);
                    todo(desc);
                    storage.save(questList);
                } catch (YodaException ye) {
                    System.out.println("Description of a todo must not be empty, Jedi.");
                }

            } else if (command.equals("event")) {
                try {
                    String[] argsEvent = parser.parseEvent(input);
                    event(argsEvent);
                    storage.save(questList);
                } catch (YodaException ye) {
                    System.out.println("Please enter a description for your quest.");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Please enter the command in the correct format.");
                }
            } else if (command.equals("deadline")) {
                try {
                    String[] argsDeadline = parser.parseDeadline(input);
                    deadline(argsDeadline);
                    storage.save(questList);
                } catch (YodaException ye) {
                    System.out.println("Please enter a description for your quest.");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("Please enter the command in the correct format.");
                }
            } else if (command.equals("delete")) {
                try {
                    int questID = parser.parseMark(args);
                    delete(questID);
                    storage.save(questList);
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid quest number.");
                } catch (IndexOutOfBoundsException ibe) {
                    System.out.println("Please enter a valid quest number.");
                }
            } else if (command.equals("help")) {
                ui.help();
            } else if (command.equals("find")) {
                find(parser.parseFind(args));
            } else {
                System.out.println("Yoda knows not what this means.");
            }
        }
    }

    private void log() {
        System.out.println("To defeat the dark lord, " +
                "following quests must you finish, Jedi:");
        System.out.print(questList.toString());
    }

    private void todo(String desc) {
        ToDo toDo = new ToDo(desc, 0);
        questList.addQuest(toDo);
        System.out.println("New Quest added:" + toDo.toString());
        System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
    }

    private void event(String[] arr) {
        Event e = new Event(arr[0], arr[1], 0);
        questList.addQuest(e);
        System.out.println("New Quest added:" + e.toString());
        System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
    }

    private void deadline(String[] arr) {
        Deadline d = new Deadline(arr[0], arr[1]);
        questList.addQuest(d);
        System.out.println("New Quest added:" + d.toString());
        System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
    }

    private void markDone(int questID) {
        questList.getQuest(questID).completeQuest();
        System.out.println("Done: " + questList.getQuest(questID).toString());
        System.out.println("For a quest accomplished you I congratulate.");
    }

    private void markUndone(int questID) {
        questList.getQuest(questID).incompleteQuest();
        System.out.println("Not done: " + questList.getQuest(questID).toString());
        System.out.println("Soldier on brave Jedi.");
    }

    private void delete(int questID) {
        Quest q = questList.deleteQuest(questID);
        System.out.println("Quest removed: " + q.toString());
        System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
    }

    private void find(String keyword) {
        ArrayList<Quest> result = questList.find(keyword);
        if (result.size() == 0) {
            System.out.println("No task match your search, Jedi.");
            return;
        }
        QuestList q = new QuestList(result);
        System.out.println("These are quests you requested, Jedi:");
        System.out.print(q);
    }

}
