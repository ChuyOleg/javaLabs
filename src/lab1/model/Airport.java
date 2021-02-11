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

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeNumber = planeNumber;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String[] getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(String[] weekDays) {
        this.weekDays = weekDays;
    }

    public int getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneNumber(int planeNumber) {
        this.planeNumber = planeNumber;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "destination = '" + destination + '\'' +
                ", flightNumber = '" + flightNumber + '\'' +
                ", planeType = '" + planeType + '\'' +
                ", startTime = " + startTime +
                ", weekDays = " + Arrays.toString(weekDays) +
                ", planeNumber = " + planeNumber +
                '}';
    }
}

