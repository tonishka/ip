package yoda;

/**
 * Represents a deadline bound quest.
 * @author Tonishka Singh
 */
public class Deadline extends Quest {
    public Deadline(String description, String date, String time, int status) {
        super(description, date, time, status);
    }

    public String getDescription() {
        return super.getDescription();
    }

    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public String toString() {
        String res = "[D]" + super.statusToString() +
                "(" + super.dateToString() + " " + super.timeToStore() + ")";
        return res;
    }
}
