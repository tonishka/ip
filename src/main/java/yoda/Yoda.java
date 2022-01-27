package yoda;

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
                int questID = parser.parseMark(args);
                markDone(questID);
                storage.save(questList);
            } else if (command.equals("unmark")) {
                int questID = parser.parseMark(args);
                markUndone(questID);
                storage.save(questList);
            } else if (command.equals("todo")){
                String desc = parser.getDescriptionToDo(args);
                todo(desc);
                storage.save(questList);
            } else if (command.equals("event")) {
                String[] argsEvent = parser.parseEvent(input);
                event(argsEvent);
                storage.save(questList);
            } else if (command.equals("deadline")) {
                String[] argsDeadline = parser.parseDeadline(input);
                deadline(argsDeadline);
                storage.save(questList);
            } else if (command.equals("delete")) {
                int questID = parser.parseMark(args);
                delete(questID);
                storage.save(questList);
            } else if (command.equals("help")) {
                ui.help();
            } else {
                System.out.println("yoda.Yoda knows not what this means.");
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
        System.out.println("New yoda.Quest added:" + toDo.toString());
        System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
    }

    private void event(String[] arr) {
        Event e = new Event(arr[0], arr[1], 0);
        questList.addQuest(e);
        System.out.println("New yoda.Quest added:" + e.toString());
        System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
    }

    private void deadline(String[] arr) {
        Deadline d = new Deadline(arr[0], arr[1]);
        questList.addQuest(d);
        System.out.println("New yoda.Quest added:" + d.toString());
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
        System.out.println("yoda.Quest removed: " + q.toString());
        System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
    }
}
