package yoda;

import java.util.Locale;

/**
 * Parser object to make sense of user command.
 * @author Tonishka Singh
 */
public class Parser {
    /**
     * Tokenizes and cleans the input by removing extraneous spaces.
     * @param input Input string from user to clean.
     * @return Array of cleaned String arguments.
     */
    public String[] clean(String input) {
        input = input.toLowerCase(Locale.ROOT);
        input = input.trim();
        String[] arr = input.split("\\s+");
        return arr;
    }

    public String getCommand(String[] arr) {
        return arr[0];
    }

    /**
     * Extracts the description of a todo type Quest.
     * @param arr Striing array of input tokens.
     * @return Description of the todo.
     * @throws YodaException When description is missing.
     */
    public String getDescriptionToDo(String[] arr) throws YodaException {
        String desc = "";
        if (arr.length <= 1) {
            throw new YodaException("Missing description.");
        }
        for (int i = 1; i < arr.length; i++) {
            desc += arr[i] + " ";
        }
        return desc;
    }

    /**
     * Parses an event command to extract the necessary arguments.
     * @param input String input from the user.
     * @return String array of all arguments.
     */
    public String[] parseEvent(String input) throws YodaException, ArrayIndexOutOfBoundsException {
        String[] res = new String[2];
        String[] period = input.split("/");
        String[] desc = period[0].split(" ");
        String s = "";
        if (desc.length <= 1) throw new YodaException("No event description.");
        for (int i = 1; i < desc.length; i++) {
            s += desc[i] + " ";
        }
        res[0] = s;
        res[1] = period[1];

        return res;
    }

    /**
     * Parses a deadline command to extract the necessary arguments.
     * @param input String input from the user.
     * @return String array of all arguments.
     */
    public String[] parseDeadline(String input) throws YodaException, ArrayIndexOutOfBoundsException {
        String[] res = new String[3];
        String[] period = input.split("/");
        String[] desc = period[0].split(" ");
        String[] dateTime = period[1].split(" ");
        String s = "";
        if (desc.length <= 1) {
            throw new YodaException("No deadline description.");
        }
        for (int i = 1; i < desc.length; i++) {
            s += desc[i] + " ";
        }
        res[0] = s;
        res[1] = dateTime[0];
        res[2] = dateTime[1];
        return res;
    }

    /**
     * Parses a mark/unmark/delete command to extract the quest to be updated.
     * @param arr String array of user input.
     * @return The index of the quest to be updated.
     */
    public int parseMark(String[] arr) throws NumberFormatException, IndexOutOfBoundsException {
        int questID = -1;
        questID = Integer.parseInt(arr[1]) - 1;
        return questID;
    }

    public String parseFind(String[] args) {
        return args[1];
    }
}
