public class Deadline extends Quest {
    public Deadline(String description, String period, int status) {
        super(description, status, "D", period);
    }

    public Deadline(String description, String period) {
        super(description);
    }

    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String toString() {
        String res = "[D]" + super.statusToString()
                + " (" + super.getPeriod() + ")";
        return res;
    }
}
