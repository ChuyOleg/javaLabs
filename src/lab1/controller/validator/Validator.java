package lab1.controller.validator;

import lab1.controller.exceptions.NonExistentAnswerException;
import lab1.controller.exceptions.NonExistentDayException;
import lab1.controller.exceptions.TimeOutOfBoundaryException;
import lab1.view.CalculateView;

public class Validator {

    enum weekDays {Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday}

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
        for (weekDays day : weekDays.values()) {
            if (dayForCheck.equalsIgnoreCase(day.name())) {
                return;
            }
        }
        throw new NonExistentDayException(view.NON_EXISTENT_DAY);
    }

    public static void checkForCorrectAnswer(String answer, CalculateView view) throws NonExistentAnswerException {

        if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
            return;
        }
        throw new NonExistentAnswerException(view.NON_EXISTENT_ANSWER);

    }

}
