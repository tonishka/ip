import java.util.Scanner;

public class Yoda {
    public static void main(String[] args) {

        System.out.println("Greetings Earthling.\n");
        pause();
        System.out.println("Yoda, I am.\n");
        pause();
        System.out.println("Defeat the Dark lord of Sith, we must.\n");
        pause();
        System.out.println("On this journey today must you embark.\n");
        pause();
        System.out.println("May the Force be with you, brave Jedi.\n");

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
    }
}
