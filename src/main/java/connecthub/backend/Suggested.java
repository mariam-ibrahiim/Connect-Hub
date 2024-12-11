package connecthub.backend;

import java.util.ArrayList;

public class Suggested {
    private ArrayList<String> suggestedIds;
    public Suggested() {
        suggestedIds = new ArrayList<>();
    }


    public ArrayList<String> getSuggestedIds() {
        return suggestedIds;
    }

    public void setSuggestedIds(ArrayList<String> suggestedIds) {
        this.suggestedIds = suggestedIds;
    }

    public void addSuggestedId(String suggestedId) {
        suggestedIds.add(suggestedId);
        System.out.println("here");
    }
    public void removeSuggestedId(String suggestedId) {
        suggestedIds.remove(suggestedId);
    }
}
