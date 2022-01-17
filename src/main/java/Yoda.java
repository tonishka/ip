import java.util.Scanner;

public class Yoda {
    public static void main(String[] args) {
        Jedi jedi = new Jedi();

        type(">> Greetings Earthling.");
        System.out.println();
        type(">> Yoda, I am.");
        System.out.println();
        type(">> Defeat the Dark lord of Sith, we must.");
        System.out.println();
        type(">> On this journey today must you embark.");
        System.out.println();
        type(">> May the Force be with you, brave Jedi.");
        System.out.println();
        pause();
        System.out.println("******************************************");
        System.out.println(">> Add quests by typing them in.");
        System.out.println(">> To view your quests type log.");
        System.out.println(">> Type mark followed by the quest number to mark it as done.");
        System.out.println("******************************************");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            String input = sc.nextLine();
            String[] command = input.split(" ");

            if (command[0].equals("bye")) {
                sc.close();
                bye();
                break;
            }
            else if (command[0].equals("log")) {
                System.out.print(jedi.toString());
            }
            else if (command[0].equals("mark")) {
                int questID = Integer.parseInt(command[1]) - 1;
                markDone(jedi, questID);
            }
            else if (command[0].equals("unmark")) {
                int questID = Integer.parseInt(command[1]) - 1;
                markUndone(jedi, questID);
            }
            else if (command[0].equals("todo")){
                String argument = "";
                for (int i = 1; i < command.length; i++) {
                    argument += command[i] + " ";
                }
                ToDo toDo = new ToDo(argument);
                jedi.addQuest(toDo);
                System.out.println("New Quest added:" + toDo.toString());
            }
            else if (command[0].equals("event")) {
                String[] period = input.split("/");
                String[] desc = period[0].split(" ");
                String s = "";
                for (int i = 1; i < desc.length; i++) {
                     s += desc[i] + " ";
                }
                Event e = new Event(s, period[1]);
                jedi.addQuest(e);
                System.out.println("New Quest added:" + e.toString());
            }
            else if (command[0].equals("deadline")) {
                String[] period = input.split("/");
                String[] desc = period[0].split(" ");
                String s = "";
                for (int i = 1; i < desc.length; i++) {
                    s += desc[i] + " ";
                }
                Deadline d = new Deadline(s, period[1]);
                jedi.addQuest(d);
                System.out.println("New Quest added:" + d.toString());
            }
            else {
                Quest q = new Quest(input);
                jedi.addQuest(q);
                System.out.println("Added new Quest: " + q.toString());
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

    private static void markDone(Jedi j, int questID) {
        j.getQuest(questID).completeQuest();
        System.out.println("Done: " + j.getQuest(questID).statusToString());
        System.out.println("For a quest accomplished you I congratulate.");
    }

    private static void markUndone(Jedi j, int questID) {
        j.getQuest(questID).incompleteQuest();
        System.out.println("Not done: " + j.getQuest(questID).statusToString());
        System.out.println("Soldier on brave Jedi.");
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

    private static void bye() {
        type("Hrrmmm. You, Jedi, farewell I bid.");
        System.out.println();
        System.out.println("******************************************");
    }
}
