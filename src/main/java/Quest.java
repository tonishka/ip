import java.util.ArrayList;

public class Quest {
    private String description;
    private int status;
    private static final String done = "[X] ";
    private static final String undo = "[ ] ";

    public Quest(String description) {
        this.description = description;
        this.status = 0;
    }

    public Quest() {
        this.description = " ";
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
}
