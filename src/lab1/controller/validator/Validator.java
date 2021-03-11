package lab1.controller.validator;

import lab1.controller.exceptions.NonExistentDayException;
import lab1.controller.exceptions.TimeOutOfBoundaryException;
import lab1.view.CalculateView;

public class Validator {

    public static void checkForCorrectHour(int hour, CalculateView view) throws TimeOutOfBoundaryException {
        if (hour < 0 || hour > 23) {
            throw new TimeOutOfBoundaryException(view.HOUR_OUT_OF_BOUNDARY);
        }
    }

    public static void checkForCorrectMinute(int minute, CalculateView view) throws TimeOutOfBoundaryException {
        if (minute < 0 || minute > 59) {
           throw new TimeOutOfBoundaryException(view.MINUTE_OUT_OF_BOUNDARY);
        }
    }

    public static void checkForCorrectDay(String dayForCheck, CalculateView view) throws NonExistentDayException {
        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (String day : weekDays) {
            if (dayForCheck.equalsIgnoreCase(day)) {
                return;
            }
        }
        throw new NonExistentDayException(view.NON_EXISTENT_DAY);
    }

}
