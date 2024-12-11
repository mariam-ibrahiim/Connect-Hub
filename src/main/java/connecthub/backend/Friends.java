package connecthub.backend;

import java.util.ArrayList;

public class Friends {
    private ArrayList<String> friendsIds;

    public Friends() {
        friendsIds = new ArrayList<>();
    }


    public ArrayList<String> getFriendsIds() {
        return friendsIds;
    }
    public void setFriendsIds(ArrayList<String> friendsIds) {
        this.friendsIds = friendsIds;
    }
    public void addFriendsId(String friendsId) {
        friendsIds.add(friendsId);
    }
    public void removeFriendsId(String friendsId) {
        friendsIds.remove(friendsId);
    }
}
