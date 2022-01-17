public class Deadline extends Quest {
    String description;
    String period;

    public Deadline(String description, String period) {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        String res = "[D]" + super.statusToString()
                + "(" + period + ")";
        return res;
    }
}
