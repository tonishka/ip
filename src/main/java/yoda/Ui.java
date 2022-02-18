package yoda;

import java.awt.*;
import java.net.*;

/**
 * Encapsulates the user interface for the chatbot.
 * @author Tonishka Singh
 */
public class Ui {

    public Ui() {} // Empty constructor

    /**
     * Redirects user to the user guide.
     */
    public String help() {
        URL userGuideURL;
        try {
            userGuideURL = new URL("https://tonishka.github.io/ip/");
            openUserGuide(userGuideURL);
            return "Help is on its way, Jedi.";
        } catch (MalformedURLException e) {
            String msg = "";
            e.printStackTrace();
            msg += "Uh-oh! Something went wrong :/\n";
            msg += "Click on this link to manually access the User Guide:\n";
            msg += "https://tonishka.github.io/ip/";
            return msg;
        }
    }

    /**
     * Opens the User Guide.
     * @param url The URL of the User Guide.
     */
    public void openUserGuide(URL url) {
        URI uri = null;
        Desktop desktop = null;

        try {
            uri = url.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Displays all Quests in the User's list.
     * @param questList User's list of Quests.
     * @return String representation of all Quests in the list.
     */
    public String log(QuestList questList) {
        String msg = "";
        if (questList.getQuestSize() == 0) {
            return "No quests have you, Jedi.";
        }
        msg += "To defeat the dark lord, " +
                "following quests must you finish, Jedi:\n";
        msg += questList.toString();
        return msg;
    }

    public String newQuestMessage(Quest quest, QuestList questList) {
        String s = "New Quest added:" + quest.toString() +"\n";
        s += questList.numQuests() + " Quests have you now, Jedi.";
        return s;
    }

    public String emptyDescriptionMessage() {
        String s = "Description of a Quest must not be empty, Jedi.";
        return s;
    }

    public String incorrectFormatMessage() {
        String s = "Please enter the command in the correct format.";
        return s;
    }

    public String invalidQuestMessage() {
        String s = "Please enter a valid quest number, Jedi.";
        return s;
    }

    public String completeQuestMessage(QuestList questList, int questID) {
        String s = "Done: " + questList.getQuest(questID).toString() + "\n";
        s += "For a quest accomplished you I congratulate.";
        return s;
    }

    public String incompleteQuestMessage(Quest q, QuestList questList) {
        String s = "Quest removed: " + q.toString() + "\n";
        s += questList.numQuests() + " Quests have you now, Jedi.";
        return s;
    }

    public String noSearchResultsMessage() {
        String s = "No task match your search, Jedi.";
        return s;
    }

    public String searchResultsMessage(QuestList q) {
        String s = "These are quests you requested, Jedi:\n";
        s += q.toString();;
        return s;
    }

    public String unsupportedCommandMessage() {
        String s = "Yoda knows not what this means.";
        return s;
    }

    public String updatePrompt(int type) {
        if (type == 1) {
            return "Enter new description for your quest.";
        } else if (type == 2) {
            return "Enter new period for your event.";
        } else if (type == 3) {
            return "Enter new deadline for your quest.";
        } else {
            return "";
        }
    }
}
