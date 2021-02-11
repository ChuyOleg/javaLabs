package lab1.model;

import java.time.LocalTime;

public class DataSource {

    public final String[] destinations = {"Abu Dhabi", "Amsterdam", "Berlin", "Bern", "Brussels"};
    public final String[] flightNumbers = {"AC579", "AC8534", "AC1222", "AC12", "AC777", "AC984"};
    public final String[] planeTypes = {"Boeing 737", "Boeing 747", "Boeing 777", "Airbus A310", "Airbus 319"};
    public final LocalTime[] startTimes = {
            LocalTime.of(14, 59),
            LocalTime.of(21, 12),
            LocalTime.of(18, 37),
            LocalTime.of(9, 22),
            LocalTime.of(13, 13),
            LocalTime.of(23, 47),
            LocalTime.of(20, 5),
    };
    public final String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public final int[] planeNumbers = {1, 2, 3, 7, 5, 4, 8};

}
