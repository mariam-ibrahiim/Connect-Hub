package connecthub.backend;

import java.util.ArrayList;

public class Blocked {
    private ArrayList<String> blockedIds;
    public Blocked() {
        blockedIds = new ArrayList<>();
    }

    public ArrayList<String> getBlockedIds() {
        return blockedIds;
    }

    public void addBlockedId(String id) {
        blockedIds.add(id);
    }
    public void removeBlockedId(String  id) {
        blockedIds.remove(id);

    }
    public boolean isBlocked(String id) {
        return blockedIds.contains(id);
    }

    public void setBlockedIds(ArrayList<String> blockedIds) {
        this.blockedIds = blockedIds;
    }
}
