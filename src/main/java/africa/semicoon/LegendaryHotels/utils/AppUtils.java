package africa.semicoon.LegendaryHotels.utils;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {
    public static int ONE = BigInteger.valueOf(1).intValue();
    public static int TWO = BigInteger.valueOf(2).intValue();
    public static int THREE = BigInteger.valueOf(3).intValue();
    public static int FOUR = BigInteger.valueOf(4).intValue();
    public static int FIFTY_DOLLARS = BigInteger.valueOf(50).intValue();
    public static int TWENTY_DOLLARS = BigInteger.valueOf(20).intValue();

    public static boolean emailIsCorrect(String email){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
