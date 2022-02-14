package yoda;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javafx.fxml.FXML;

/**
 * Represents the chatbot.
 * @author Tonishka Singh
 */
public class Yoda {

    private Ui ui;
    private Storage storage;
    private QuestList questList;
    private Parser parser;
    private int updateStatus; // Tracks what kind of update is under progress
    private String[] prevArgs;

    public Yoda() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.questList = new QuestList(this.storage.load());
        this.parser = new Parser();
        this.updateStatus = 0;
    }

    private String log() {
        String msg = "";
        msg += "To defeat the dark lord, " +
                    "following quests must you finish, Jedi:\n";
        msg += questList.toString();
        return msg;
    }

    private String todo(String[] args) {
        String msg = "";
        try {
            String desc = parser.getDescriptionToDo(args);
            ToDo toDo = new ToDo(desc, 0);
            questList.addQuest(toDo);
            msg += "New Quest added:" + toDo.toString() +"\n";
            msg += questList.numQuests() + " Quests have you now, Jedi.";
            storage.save(questList);
        } catch (YodaException ye) {
            msg = "Description of a todo must not be empty, Jedi.";
        }
        return msg;
    }

    private String event(String input) {
        String msg = "";
        try {
            String[] argsEvent = parser.parseEvent(input);
            Event e = new Event(argsEvent[0], argsEvent[1], 0);
            questList.addQuest(e);
            msg += "New Quest added:" + e.toString() + "\n";
            msg += questList.numQuests() + " Quests have you now, Jedi.";
            storage.save(questList);
        } catch (YodaException ye) {
            msg = "Please enter a description for your quest.";
        } catch (ArrayIndexOutOfBoundsException ex) {
            msg = "Please enter the command in the correct format.";
        }
        return msg;
    }

    private String deadline(String input) {
        String msg = "";
        try {
            String[] argsDeadline = parser.parseDeadline(input);
            Deadline d = new Deadline(argsDeadline[0], argsDeadline[1], argsDeadline[2]);
            questList.addQuest(d);
            msg += "New Quest added:" + d.toString() + "\n";
            msg += questList.numQuests() + " Quests have you now, Jedi.";
            storage.save(questList);
        } catch (YodaException ye) {
            msg += "Please enter a description for your quest.";
        } catch (ArrayIndexOutOfBoundsException ex) {
            msg += "Please enter the command in the correct format.";
        }
        return msg;
    }

    private String markDone(String[] args) {
        String msg = "";
        try {
            int questID = this.parser.parseMark(args);
            questList.getQuest(questID).completeQuest();
            msg  += "Done: " + questList.getQuest(questID).toString() + "\n";
            msg += "For a quest accomplished you I congratulate.";
            storage.save(questList);
        } catch (NumberFormatException nfe) {
            msg = "Please enter a valid quest number.";
        } catch (IndexOutOfBoundsException ibe) {
            msg = "Please enter a valid quest number.";
        }
        return msg;
    }

    private String markUndone(String[] args) {
        String msg = "";
        try {
            int questID = parser.parseMark(args);
            questList.getQuest(questID).incompleteQuest();
            msg += "Not done: " + questList.getQuest(questID).toString() + "\n";
            msg += "Soldier on brave Jedi.";
            storage.save(questList);
        } catch (NumberFormatException nfe) {
            msg += "Please enter a valid quest number.";
        } catch (IndexOutOfBoundsException ibe) {
            msg += "Please enter a valid quest number.";
        }
        return msg;
    }

    private String delete(String[] args) {
        String msg = "";
        try {
            int questID = parser.parseMark(args);
            Quest q = questList.deleteQuest(questID);
            msg += "Quest removed: " + q.toString() + "\n";
            msg += questList.numQuests() + " Quests have you now, Jedi.";
            storage.save(questList);
        } catch (NumberFormatException nfe) {
            msg += "Please enter a valid quest number.";
        } catch (IndexOutOfBoundsException ibe) {
            msg += "Please enter a valid quest number.";
        }
        return msg;
    }

    private String find(String keyword) {
        ArrayList<Quest> result = questList.find(keyword);
        String msg = "";
        if (result.size() == 0) {
            return "No task match your search, Jedi.";
        }
        QuestList q = new QuestList(result);
        msg += "These are quests you requested, Jedi:\n";
        msg += q.toString();
        return msg;
    }

    private String updateDescription(String[] args, String updated) {
        String msg = "";
        try {
            int questID = parser.parseMark(args);
            Quest quest = questList.getQuest(questID);
            quest.setDescription(updated);
            msg += "Quest updated: " + quest.toString();
            storage.save(questList);
        } catch (NumberFormatException nfe) {
            msg += "Please retry. Your quest number was not valid.";
        } catch (IndexOutOfBoundsException ibe) {
            msg += "Please retry. Your quest number was not valid.";
        }
        return msg;
    }

    private String updatePeriod(String[] args, String updated) {
        String msg = "";
        try {
            int questID = parser.parseMark(args);
            Quest quest = questList.getQuest(questID);
            if (!quest.getType().equals("E")) {
                throw new YodaException("This is not an event.");
            }
            quest.setPeriod(updated);
            msg += "Quest updated: " + quest.toString();
            storage.save(questList);
        } catch (NumberFormatException nfe) {
            msg += "Please retry. Your quest number was not valid.";
        } catch (IndexOutOfBoundsException ibe) {
            msg += "Please retry. Your quest number was not valid.";
        } catch (YodaException yodaException) {
            msg += "Please retry. Your quest was not an event.";
        }
        return msg;
    }

    private String updateDeadline(String[] args, String updated) {
        String msg = "";
        try {
            int questID = parser.parseMark(args);
            Quest quest = questList.getQuest(questID);
            if (!quest.getType().equals("D")) {
                throw new YodaException("This is not a deadline bound quest.");
            }
            String[] s = updated.split("\\s+");
            quest.setDeadline(s[0], s[1]);
            msg += "Quest updated: " + quest.toString();
            storage.save(questList);
        } catch (NumberFormatException nfe) {
            msg += "Please retry. Your quest number was not valid.";
        } catch (IndexOutOfBoundsException ibe) {
            msg += "Please retry. Either your quest number was not valid or the deadline " +
                    "was entered incorrectly.";
        } catch (YodaException yodaException) {
            msg += "Please retry. Your quest number was not valid.";
        } catch (DateTimeParseException exception) {
            msg += "Please retry updating by entering the deadline correctly.";
        }
        return msg;
    }

    @FXML
    protected String getResponse(String input) {
        String[] args = this.parser.clean(input);
        String command = this.parser.getCommand(args);
        if (command.equals("log")) {
            return log();
        } else if (command.equals("todo")) {
            return todo(args);
        } else if (command.equals("event")) {
            return event(input);
        } else if (command.equals("deadline")) {
            return deadline(input);
        } else if (command.equals("mark")) {
            return markDone(args);
        } else if (command.equals("unmark")) {
            return markUndone(args);
        } else if (command.equals("delete")) {
            return delete(args);
        } else if (command.equals("help")) {
            return this.ui.help();
        } else if (command.equals("find")) {
            return find(parser.parseFind(args));
        } else if (command.equals("update_desc")) {
            this.updateStatus = 1;
            prevArgs = this.parser.clean(input);
            return "Enter new description for your quest.";
        } else if (command.equals("update_period")) {
            this.updateStatus = 2;
            prevArgs = this.parser.clean(input);
            return "Enter new period for your event.";
        } else if (this.updateStatus == 1) {
            this.updateStatus = 0;
            return updateDescription(prevArgs, input);
        } else if (this.updateStatus == 2) {
            this.updateStatus = 0;
            return updatePeriod(prevArgs, input);
        } else if (command.equals("update_deadline")) {
            this.updateStatus = 3;
            prevArgs = this.parser.clean(input);
            return "Enter new deadline for your quest.";
        } else if (this.updateStatus == 3) {
            this.updateStatus = 0;
            return updateDeadline(prevArgs, input);
        } else {
            return "Yoda knows not what this means.";
        }
    }
}
