import java.util.*;

public class Jedi {
    private ArrayList<Quest> questList;

    public Jedi() {
        this.questList = new ArrayList<>();
    }

    public void addQuest(Quest q) {
        questList.add(q);
    }

    public Quest getQuest(int questID) {
        return questList.get(questID);
    }

    @Override
    public String toString() {
        int len = questList.size();
        String res = "";
        if (len == 0) return "";
        for (int i = 0; i < len; i++) {
            int index = i + 1;
            res += index + ". " +
                    questList.get(i).statusToString() + "\n";
        }
        return res;
    }

}
