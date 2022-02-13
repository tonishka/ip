package yoda;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private LocalDate date;
    private LocalTime time;

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

    public Quest(String description, String date, String time) {
        this.description = description;
        this.date = LocalDate.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(time, formatter);
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setDeadline(String date, String time) {
        this.date = LocalDate.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(time, formatter);
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
        String str = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return str;
    }

    public String dateToStore() {
        String str = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return str;
    }

    public String timeToStore() {
        String str = time.format(DateTimeFormatter.ofPattern("HH:mm"));
        return str;
    }
}
