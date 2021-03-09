package lab1.view;

import lab1.model.Airport;

public class CalculateView {

    public final String INPUT_DATA = "Select an action:  getAll(1) | getByDestination(2) | getByWeekDay(3) | GetByWeekDayAndTime(4) | Exit(5) => ";

    public final String END_DATA = "Thanks for the interaction. It's over!";

    public final String FILTER_DESTINATION = "Select a destination => ";

    public final String FILTER_WEEKDAY = "Select a weekDay => ";

    public final String FILTER_HOUR = "Select an hour => ";

    public final String FILTER_MINUTE = "Select a minute => ";

    public final String WRONG_INPUT_DATA = "Wrong input! ";

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
