package lab1.controller.validator;

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

    // days of the week

}
