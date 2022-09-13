package uz.imirsaburov.telegramsms.utils;

public class CommonUtil {

    public static String getOnlyNumbers(String string) {
        return string.replaceAll("\\D+", "");
    }
}
