package yoda;

public class Parser {
    public Parser() {}

    public String[] clean(String input) {
        String[] arr = input.split(" ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }

    public String getCommand(String[] arr) {
        return arr[0];
    }

    public String getDescriptionToDo(String[] arr) {
        String desc = "";
        try {
            if (arr.length <= 1) {
                throw new YodaException("Missing description.");
            }
            for (int i = 1; i < arr.length; i++) {
                desc += arr[i] + " ";
            }
        } catch (YodaException ye) {
            System.out.println("Description of a todo must not be empty, Jedi.");
        }
        return desc;
    }

    public String[] parseEvent(String input) {
        String[] res = new String[2];
        try {
            String[] period = input.split("/");
            String[] desc = period[0].split(" ");
            String s = "";
            if (desc.length <= 1) throw new YodaException("No event description.");
            for (int i = 1; i < desc.length; i++) {
                s += desc[i] + " ";
            }
            res[0] = s;
            res[1] = period[1];

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please enter the command in the correct format.");
        } catch (YodaException ye) {
            System.out.println("Please enter a description for your quest.");
        }

        return res;
    }

    public String[] parseDeadline(String input) {
        String[] res = new String[2];
        try {
            String[] period = input.split("/");
            String[] desc = period[0].split(" ");
            String s = "";
            if (desc.length <= 1) throw new YodaException("No deadline description.");
            for (int i = 1; i < desc.length; i++) {
                s += desc[i] + " ";
            }
            res[0] = s;
            res[1] = period[1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please enter the command in the correct format.");
        } catch (YodaException ye) {
            System.out.println("Please enter a description for your quest.");
        }
        return res;
    }

    public int parseMark(String[] arr) {
        int questID = -1;
        try {
            questID = Integer.parseInt(arr[1]) - 1;
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid quest number.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid quest number.");
        }
        return questID;
    }

    public String parseFind(String[] args) {
        return args[1];
    }
}
