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
        System.out.println("******************************************");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                sc.close();
                bye();
                break;
            } else if (input.equals("log")) {
                System.out.print(jedi.toString());
            } else {
                Quest q = new Quest(input);
                jedi.addQuest(q);
                System.out.println("Added new Quest: " + q.toString());
            }
        }
    }

    private static void pause() {
        try {
            Thread.sleep(2000);
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
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

    private static void echo(String msg) {
        System.out.println(msg);
    }

    private static void bye() {
        System.out.println("Hrrmmm. You, Jedi, farewell I bid.");
        System.out.println("******************************************");
    }
}
