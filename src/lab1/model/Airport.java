package lab1.model;

import java.time.LocalTime;
import java.util.List;

public class Airport {

    private final String destination;
    private final String flightNumber;
    private final String planeType;
    private final LocalTime startTime;
    private final List<String> weekDays;
    private final int planeNumber;

    public Airport(String destination, String flightNumber, String planeType, LocalTime startTime, List<String> weekDays, int planeNumber) {
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

    public String getFlightNumber() { return flightNumber; }

    public String getPlaneType() { return planeType; }

    public LocalTime getStartTime() {
        return startTime;
    }

    public List<String> getWeekDays() {
        return weekDays;
    }

    public int getPlaneNumber() { return planeNumber; }

    @Override
    public String toString() {
        return String.format("%20s%20s%20s%17tH:%2tM%30s%20d",
                destination,
                flightNumber,
                planeType,
                startTime,
                startTime,
                weekDays,
                planeNumber
        );
    }
}

