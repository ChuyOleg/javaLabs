package lab1.view;

import lab1.model.Airport;

public class CalculateView {

    public static final String INPUT_DATA = "Select an action:  getAll | getByDestination | getByWeekDay | GetByWeekDayAndTime | Exit => ";

    public static final String END_DATA = "Thanks for the interaction. It's over!";

    public static final String WRONG_INPUT_DATA = "Wrong input! Repeat please! ";

    public static final String RESULT = "Filtered data => ";

    public static final String COLUMNS = String.format("%20s%20s%20s%20s%30s%20s",
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

    public void printMessageAndResult(String message, Airport[] airports) {
        System.out.println(System.lineSeparator() + message);
        for (Airport airport : airports) {
            System.out.println(airport);
        }
    }
}
