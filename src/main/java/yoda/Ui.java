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
        return "Description of a Quest must not be empty, Jedi.";
    }

    public String incorrectFormatMessage() {
        return "Please enter the command in the correct format.";
    }

}
