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

    public Quest deleteQuest(int questID) {
        Quest q = this.getQuest(questID);
        questList.remove(questID);
        return q;
    }

    public int numQuests() {
        return questList.size();
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