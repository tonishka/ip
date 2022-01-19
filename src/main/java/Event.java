public class Event extends Quest {
    String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        String res = "[E]" + super.statusToString()
                + "(" + period + ")";
        return res;
    }
}
