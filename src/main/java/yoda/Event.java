package yoda;

/**
 * Represents a quest of type event.
 * @author Tonishka Singh
 */
public class Event extends Quest {
    public Event(String description, String period, int status) {
        super(description, status, "E", period);
    }

    public String getDescription() {
        return super.getDescription();
    }

    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public String toString() {
        String res = "[E]" + super.statusToString()
                + "(" + super.getPeriod() + ")";
        return res;
    }
}
