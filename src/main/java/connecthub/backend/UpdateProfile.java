package connecthub.backend;

import org.mindrot.jbcrypt.BCrypt;

import connecthub.frontend.App;

public class UpdateProfile {
    //user management
    Profile profile;
    

    public UpdateProfile(Profile profile) {
        this.profile = profile;
    }

    public void updateProfilePhoto(String photo) {
        profile.setProfilePhoto(photo);
        App.userAccountManager.save();
    }

    public void updateCoverPhoto(String photo) {
        profile.setCoverPhoto(photo);
        App.userAccountManager.save();
    }

    public void updateBio(String bio) {
        profile.setBio(bio);
        App.userAccountManager.save();
    }

  
    public void updatePassword(User user,String oldPassword, String newPassword) {
/*         if(!(BCrypt.hashpw(oldPassword,BCrypt.gensalt(6)).equals(user.getPassword()))){
            System.out.println("Old password is incorrect");
            return;
        } */
        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
            System.out.println("Old password is incorrect");
            return;
        }
        if(!BCrypt.checkpw(newPassword, user.getPassword())){
            user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt(6)));
            App.userAccountManager.save();
        }
        else{
            System.out.println("Please enter a different password");
        }
    }

/*     public void saveToDatabase(){
        userDatabase.saveToFile();
    } */

}
