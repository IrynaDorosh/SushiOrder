package home.sushiorder.libs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    public static String getURLfromLetter(String letterBody){

        Pattern pattern = Pattern.compile("https://foodmiles\\S+");
        Matcher myMatcher = pattern.matcher(letterBody);
        if (myMatcher.find()) {
            return  myMatcher.group();
        }
        return null;
        }


}
