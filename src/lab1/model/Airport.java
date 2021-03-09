package lab1.model;

import java.time.LocalTime;
import java.util.Arrays;

public class Airport {

    private String destination;
    private String flightNumber;
    private String planeType;
    private LocalTime startTime;
    private String[] weekDays;
    private int planeNumber;

    public Airport(String destination, String flightNumber, String planeType, LocalTime startTime, String[] weekDays, int planeNumber) {
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.planeType = planeType;
        this.startTime = startTime;
        this.weekDays = weekDays;
        this.planeNumber = planeNumber;
    }

    public String getDestination() {
        return destination;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public String[] getWeekDays() {
        return weekDays;
    }

    @Override
    public String toString() {
        return String.format("%20s%20s%20s%17tH:%2tM%30s%20d",
                destination,
                flightNumber,
                planeType,
                startTime,
                startTime,
                Arrays.toString(weekDays),
                planeNumber
        );
    }
}

