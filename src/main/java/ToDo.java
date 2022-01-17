public class ToDo extends Quest {
    String description;
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String res = "[T]" + super.statusToString();
        return res;
    }
}
