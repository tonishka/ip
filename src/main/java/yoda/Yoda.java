package yoda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Represents the chatbot.
 * @author Tonishka Singh
 */
public class Yoda {
    private Ui ui;
    private Storage storage;
    private QuestList questList;
    private Scanner sc;
    private Parser parser;

    public Yoda() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.questList = new QuestList(this.storage.load());
        this.sc = new Scanner(System.in);
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        new Yoda().run();
    }

    private void run() {
        this.ui.welcome();
        while (true) {
            System.out.print(">> ");
            String input = this.sc.nextLine();
            String[] args = this.parser.clean(input);
            String command = this.parser.getCommand(args);

            if (command.equals("bye")) {
                bye();
                break;
            } else if (command.equals("log")) {
                log();
            } else if (command.equals("mark")) {
                markDone(args);
            } else if (command.equals("unmark")) {
                markUndone(args);
            } else if (command.equals("todo")) {
                todo(args);
            } else if (command.equals("event")) {
                event(input);
            } else if (command.equals("deadline")) {
                deadline(input);
            } else if (command.equals("delete")) {
                delete(args);
            } else if (command.equals("help")) {
                this.ui.help();
            } else if (command.equals("find")) {
                find(parser.parseFind(args));
            } else if (command.equals("update_desc")) {

            } else if (command.equals("update_period")) {

            } else if (command.equals("update_deadline")) {

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

    private void todo(String[] args) {
        try {
            String desc = parser.getDescriptionToDo(args);
            ToDo toDo = new ToDo(desc, 0);
            questList.addQuest(toDo);
            System.out.println("New Quest added:" + toDo.toString());
            System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
            storage.save(questList);
        } catch (YodaException ye) {
            System.out.println("Description of a todo must not be empty, Jedi.");
        }
    }

    private void event(String input) {
        try {
            String[] argsEvent = parser.parseEvent(input);
            Event e = new Event(argsEvent[0], argsEvent[1], 0);
            questList.addQuest(e);
            System.out.println("New Quest added:" + e.toString());
            System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
            storage.save(questList);
        } catch (YodaException ye) {
            System.out.println("Please enter a description for your quest.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please enter the command in the correct format.");
        }
    }

    private void deadline(String input) {
        try {
            String[] argsDeadline = parser.parseDeadline(input);
            Deadline d = new Deadline(argsDeadline[0], argsDeadline[1], argsDeadline[2]);
            questList.addQuest(d);
            System.out.println("New Quest added:" + d.toString());
            System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
            storage.save(questList);
        } catch (YodaException ye) {
            System.out.println("Please enter a description for your quest.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please enter the command in the correct format.");
        }
    }

    private void markDone(String[] args) {
        try {
            int questID = this.parser.parseMark(args);
            questList.getQuest(questID).completeQuest();
            System.out.println("Done: " + questList.getQuest(questID).toString());
            System.out.println("For a quest accomplished you I congratulate.");
            storage.save(questList);
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid quest number.");
        } catch (IndexOutOfBoundsException ibe) {
            System.out.println("Please enter a valid quest number.");
        }
    }

    private void markUndone(String[] args) {
        try {
            int questID = parser.parseMark(args);
            questList.getQuest(questID).incompleteQuest();
            System.out.println("Not done: " + questList.getQuest(questID).toString());
            System.out.println("Soldier on brave Jedi.");
            storage.save(questList);
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid quest number.");
        } catch (IndexOutOfBoundsException ibe) {
            System.out.println("Please enter a valid quest number.");
        }
    }

    private void delete(String[] args) {
        try {
            int questID = parser.parseMark(args);
            Quest q = questList.deleteQuest(questID);
            System.out.println("Quest removed: " + q.toString());
            System.out.println(questList.numQuests() + " Quests have you now, Jedi.");
            storage.save(questList);
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid quest number.");
        } catch (IndexOutOfBoundsException ibe) {
            System.out.println("Please enter a valid quest number.");
        }
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

    private void updateDescription (Quest q) {
        System.out.println("Enter new description for your quest.");
        String updated = this.sc.nextLine();
        q.setDescription(updated);
        System.out.println("Quest updated:" + q.toString());
        storage.save(questList);
    }

    private void bye() {
        this.sc.close();
        this.ui.bye(storage, questList);
    }

    /**public void start(Stage stage) throws IOException {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }**/
}
