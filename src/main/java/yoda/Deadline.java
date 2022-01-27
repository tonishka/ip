package yoda;

public class Deadline extends Quest {
    public Deadline(String description, String date) {
        super(description, date);
    }

    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String toString() {
        String res = "[D]" + super.statusToString() +
                " (" + super.dateToString() + ")";
        return res;
    }
}
