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

    public Quest(String description, String date, String time, int status) {
        this.description = description;
        this.date = LocalDate.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(time, formatter);
        this.status = status;
        this.type = "D";
    }

    /**
     * Marks the Quest as complete.
     */
    public void completeQuest() {
        this.status = 1;
    }

    /**
     * Marks the Quest as incomplete.
     */
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

    /**
     * Converts the status of the Quest to an appropriate String representation.
     * @return String representation of the Quest and its completion status.
     */
    public String statusToString() {
        if (this.status == 0) {
            return UNDO + this.description;
        }
        return DONE + this.description;
    }

    /**
     * Formats the date of the Quest in a user-friendly manner.
     * @return The String representing the formatted date.
     */
    public String dateToString() {
        String str = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return str;
    }

    /**
     * Converts the date into a String suitable for storing in the hard drive.
     * @return The String representing the date to store.
     */
    public String dateToStore() {
        String str = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return str;
    }

    /**
     * Converts the time into a String suitable for storing in the hard drive.
     * @return The String representing the date to store.
     */
    public String timeToStore() {
        String str = time.format(DateTimeFormatter.ofPattern("HH:mm"));
        return str;
    }
}
