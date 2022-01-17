public class Quest {
    private String description;
    private int status;

    public Quest(String description) {
        this.description = description;
        this.status = 0;
    }

    public void completeQuest() {
        this.status = 1;
    }

    public void incompleteQuest() {
        this.status = 0;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String statusToString() {
        String done = "[X] ";
        String undo = "[ ] ";
        if (this.status == 0) {
            return undo + this.description;
        }
        return done + this.description;
    }
}
