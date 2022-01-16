import java.util.Scanner;

public class Yoda {
    public static void main(String[] args) {

        System.out.println("Greetings Earthling.");
        pause();
        System.out.println("Yoda, I am.");
        pause();
        System.out.println("Defeat the Dark lord of Sith, we must.");
        pause();
        System.out.println("On this journey today you must embark.");
        pause();
        System.out.println("May the Force be with you, brave Jedi.");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
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
}
