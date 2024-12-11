package connecthub.backend;

import java.util.regex.Pattern;


public class Validation {
    
    public static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(Constants.REGEX);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPasswordLength(String password){
        return password.length()>=8;
    }
}