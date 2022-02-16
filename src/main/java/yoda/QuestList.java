package yoda;

/**
 * Represents a list of all existing quests.
 * @author Tonishka Singh
 */
import java.util.ArrayList;

public class QuestList {
    private ArrayList<Quest> questList;

    public QuestList(ArrayList<Quest> questList) {
        this.questList = questList;
    }

    public void addQuest(Quest q) {
        questList.add(q);
    }

    public Quest getQuest(int questID) {
        return questList.get(questID);
    }

    public int getQuestSize() {
        return this.questList.size();
    }

    public Quest deleteQuest(int questID) {
        Quest q = this.getQuest(questID);
        questList.remove(questID);
        return q;
    }

    public int numQuests() {
        return questList.size();
    }

    public ArrayList<Quest> find(String keyword) {
        ArrayList<Quest> result = new ArrayList<>();
        Parser parser = new Parser();
        for (int i = 0; i < this.questList.size(); i++) {
            String[] arr = parser.clean(questList.get(i).getDescription());
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].equals(keyword)) {
                    result.add(questList.get(i));
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        int len = questList.size();
        String res = "";
        if (len == 0) return "";
        for (int i = 0; i < len; i++) {
            int index = i + 1;
            res += index + ". " +
                    questList.get(i).toString() + "\n";
        }
        return res;
    }
}