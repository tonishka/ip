package yoda;

public class Event extends Quest {
    public Event(String description, String period, int status) {
        super(description, status, "E", period);
    }

    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String toString() {
        String res = "[E]" + super.statusToString()
                + " (" + super.getPeriod() + ")";
        return res;
    }
}
