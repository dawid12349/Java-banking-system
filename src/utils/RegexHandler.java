package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHandler {

    public static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PESEL_PATTERN =
            Pattern.compile("[0-9]{11}");
    public static final Pattern NAME_PATTERN =
            Pattern.compile("[a-zA-Z]{2,}");
    public static final Pattern TELEPHONENUMBER_PATTERN =
            Pattern.compile("(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)");
    public static final Pattern STREETADRESS_PATTERN =
            Pattern.compile("[a-zA-z]+ [0-9]+");
    public static final Pattern COUNTY_PATTERN =
            Pattern.compile("[A-Z][a-z]+");
    public static final Pattern CITY_PATTERN =
            Pattern.compile("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

    public static Boolean validate(Pattern  pattern, String str){
        Matcher matcher = pattern.matcher(str);
        return !matcher.matches();
    }
}
