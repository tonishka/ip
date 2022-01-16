import java.util.Scanner;

public class Yoda {
    public static void main(String[] args) {

        System.out.println(">> Greetings Earthling.");
        pause();
        System.out.println(">> Yoda, I am.");
        pause();
        System.out.println(">> Defeat the Dark lord of Sith, we must.");
        pause();
        System.out.println(">> On this journey today must you embark.");
        pause();
        System.out.println(">> May the Force be with you, brave Jedi.");
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
            } else {
                echo(input);
            }
        }
    }

    private static void pause() {
        try {
            Thread.sleep(3000);
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
    private static void echo(String msg) {
        System.out.println(msg);
    }
    private static void bye() {
        System.out.println("Hrrmmm. You, Jedi, farewell I bid.");
        System.out.println("******************************************");
    }
    private static void add() {
        System.out.println("Hrrmmm. You, Jedi, farewell I bid.");
    }

}
