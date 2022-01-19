public class ToDo extends Quest {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String res = "[T]" + super.statusToString();
        return res;
    }
}
