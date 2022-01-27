package yoda;

/**
 * Represents a quest of type todo.
 * @author Tonishka Singh
 */
public class ToDo extends Quest {
    public ToDo(String description, int status) {
        super(description, status, "T");
    }

    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String toString() {
        String res = "[T]" + super.statusToString();
        return res;
    }
}
