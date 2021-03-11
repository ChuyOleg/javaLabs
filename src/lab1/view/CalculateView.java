package lab1.view;

import lab1.model.Airport;

public class CalculateView {

    public final String INPUT_DATA = "Select an action:  getAll(1) | getByDestination(2) | getByWeekDay(3) | GetByWeekDayAndTime(4) | Exit(5) => ";

    public final String END_DATA = "Thanks for the interaction. It's over!";

    public final String FILTER_DESTINATION = "Select a destination => ";

    public final String FILTER_WEEKDAY = "Select a weekDay => ";

    public final String FILTER_HOUR = "Select an hour (0 - 23) => ";

    public final String FILTER_MINUTE = "Select a minute (0 - 59) => ";

    public final String WRONG_INPUT_DATA = "Wrong input! ";

    public final String HOUR_MUST_BE_INTEGER = "Incorrect hour. The hour must be an integer! Try again, please.";

    public final String MINUTE_MUST_BE_INTEGER = "Incorrect minute.The minute must be an integer! Try again, please.";

    public final String HOUR_OUT_OF_BOUNDARY = "Incorrect hour. You have gone beyond (0 - 23). Try again, please.";

    public final String MINUTE_OUT_OF_BOUNDARY = "Incorrect minute. You have gone beyond (0 - 59). Try again, please.";

    public final String NON_EXISTENT_DAY = "Non-existent day. Try again, please.";

    public final String NO_DATA = "Nothing have been found, please try again.";

    public final String RESULT = String.format("%70s", "Filtered data");

    public final String COLUMNS = String.format("%20s%20s%20s%20s%30s%20s",
            "Destination",
            "FlightNumber",
            "PlaneType",
            "StartTime",
            "WeekDays",
            "PlaneNumber"
    );

    public void printMessage(String message) {
        System.out.print(message);
    }

    public void printMessage(String message1, String message2) {
        System.out.print(message1 + System.lineSeparator() + message2);
    }

    public void printMessageAndResult(Airport[] airports) {
        if (airports.length > 0) {
            System.out.println(RESULT + System.lineSeparator() + COLUMNS);
            for (Airport airport : airports) {
                System.out.println(airport);
            }
            System.out.println(System.lineSeparator());
        } else {
            printMessage(NO_DATA + System.lineSeparator());
        }
    }
}
