package yoda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a quest.
 * @author Tonishka Singh
 */

public class Quest {
    private String description;
    private int status;
    private static final String DONE = "[X] ";
    private static final String UNDO = "[ ] ";
    private String type;
    private String period;
    private LocalDateTime deadline;

    public Quest(String description) {
        this.description = description;
        this.status = 0;
    }

    public Quest(String description, int status) {
        this.description = description;
        this.status = status;
    }

    public Quest(String description, int status, String type) {
        this.description = description;
        this.status = status;
        this.type = type;
    }

    public Quest(String description, int status, String type, String period) {
        this.description = description;
        this.status = status;
        this.type = type;
        this.period = period;
    }

    public Quest() {
        this.description = " ";
    }

    public Quest(String description, String date) {
        this.description = description;
        this.deadline = LocalDateTime.parse(date);
        this.status = 0;
        this.type = "D";
    }

    public void completeQuest() {
        this.status = 1;
    }

    public void incompleteQuest() {
        this.status = 0;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public int getStatus() {
        return this.status;
    }

    public String getPeriod() {
        return this.period;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String statusToString() {
        if (this.status == 0) {
            return UNDO + this.description;
        }
        return DONE + this.description;
    }

    public String dateToString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return deadline.format(dtf);
    }
}
