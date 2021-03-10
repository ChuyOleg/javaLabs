package lab1.validator;

public class Validator {

    public static boolean isNotInteger(String string) {
        try {
            Integer.parseInt(string);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
    
    public static boolean isNotCorrectHour(String string) {
        return (Integer.parseInt(string) < 0 || Integer.parseInt(string) > 23);
    }

    public static boolean isNotCorrectMinute(String string) {
        return (Integer.parseInt(string) < 0 || Integer.parseInt(string) > 59);
    }

}
