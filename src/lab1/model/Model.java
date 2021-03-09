package lab1.model;

import java.time.LocalTime;

public class Model {

    private final Airport[] airports;

    public Model() {
        airports = new Factory().createAirports(10);
    }

    public Airport[] getAllAirports() {
        return airports;
    }

    public Airport[] getAirportsByDestination(String destination) {
        Airport[] wantedAirports = new Airport[airports.length];
        int index = 0;
        for (Airport airport : airports) {
            if (airport.getDestination().equalsIgnoreCase(destination)) {
                wantedAirports[index++] = airport;
            }
        }
        Airport[] airportsByDestination = new Airport[index];
        System.arraycopy(wantedAirports, 0, airportsByDestination, 0, index);
        return airportsByDestination;
    }

    public Airport[] getAirportsByWeekDay(String weekDay) {
        Airport[] wantedAirports = new Airport[airports.length];
        int index = 0;
        outer:
        for (Airport airport : airports) {
            String[] weekDays = airport.getWeekDays();
            for (String day : weekDays) {
                if (day.equalsIgnoreCase(weekDay)) {
                    wantedAirports[index++] = airport;
                    continue outer;
                }
            }
        }
        Airport[] airportsByWeekDay = new Airport[index];
        System.arraycopy(wantedAirports, 0, airportsByWeekDay, 0, index);
        return airportsByWeekDay;
    }

    public Airport[] getAirportsByWeekDayAndTime(String weekDay, LocalTime startTime) {
        Airport[] airportsByWeekDay = getAirportsByWeekDay(weekDay);
        Airport[] wantedAirports = new Airport[airports.length];
        int index = 0;
        for (Airport airport : airportsByWeekDay) {
            if (airport.getStartTime().compareTo(startTime) > 0) {
                wantedAirports[index++] = airport;
            }
        }
        Airport[] airportsByWeekDayAndTime = new Airport[index];
        System.arraycopy(wantedAirports, 0, airportsByWeekDayAndTime, 0, index);
        return airportsByWeekDayAndTime;
    }

}
