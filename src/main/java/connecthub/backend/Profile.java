package connecthub.backend;

import org.mindrot.jbcrypt.BCrypt;

public class Profile {
    private String profilePhoto="resources\\"+Constants.DEFAULT_PROFILE+".jpg",coverPhoto="resources\\"+Constants.DEFAULT_PROFILE+".jpg";
    private String bio="No bio";

    
/*     public Profile() {
        profilePhoto = coverPhoto = "resources\\defaultprofile.jpg";
        bio = "No bio";
    } */
   public Profile(){

   }
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public String getBio() {
        return bio;
    }

    public void saveToDatabase(User user){
    }

}
