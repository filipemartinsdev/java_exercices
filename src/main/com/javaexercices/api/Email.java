package api;

import java.util.regex.Matcher;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Email {
    public static boolean isEmail(String email){
        String regex = "^[a-zA-Z0-9]+[\\D\\d]+@[a-zA-Z]+.[a-zA-Z]+$";

        Pattern emailPattern = Pattern.compile(regex);
        Matcher matcher = emailPattern.matcher(email);

        return matcher.matches();
    }
}
