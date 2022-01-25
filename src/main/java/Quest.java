import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Quest {
    private String description;
    private int status;
    private static final String done = "[X] ";
    private static final String undo = "[ ] ";
    private LocalDate deadline;

    public Quest(String description) {
        this.description = description;
        this.status = 0;
    }

    public Quest() {
        this.description = " ";
    }

    public Quest(String description, String date) {
        this.description = description;
        this.deadline = LocalDate.parse(date);
    }

    public void completeQuest() {
        this.status = 1;
    }

    public void incompleteQuest() {
        this.status = 0;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String statusToString() {
        if (this.status == 0) {
            return undo + this.description;
        }
        return done + this.description;
    }

    public String dateToString() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
