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
        Airport[] wantedAirports = new Airport[0];
        for (Airport airport : airports) {
            if (airport.getDestination().equalsIgnoreCase(destination)) {
                Airport[] newWantedAirports = new Airport[wantedAirports.length + 1];
                System.arraycopy(wantedAirports, 0, newWantedAirports, 0, wantedAirports.length);
                wantedAirports = newWantedAirports;
                wantedAirports[wantedAirports.length - 1] = airport;
            }
        }
        return wantedAirports;
    }

    public Airport[] getAirportsByWeekDay(String weekDay) {
        Airport[] wantedAirports = new Airport[0];
        outer:
        for (Airport airport : airports) {
            String[] weekDays = airport.getWeekDays();
            for (String day : weekDays) {
                if (day.equalsIgnoreCase(weekDay)) {
                    Airport[] newWantedAirports = new Airport[wantedAirports.length + 1];
                    System.arraycopy(wantedAirports, 0, newWantedAirports, 0, wantedAirports.length);
                    wantedAirports = newWantedAirports;
                    wantedAirports[wantedAirports.length - 1] = airport;
                    continue outer;
                }
            }
        }
        return wantedAirports;
    }

    public Airport[] getAirportsByWeekDayAndTime(String weekDay, LocalTime startTime) {
        Airport[] airportsByWeekDay = getAirportsByWeekDay(weekDay);
        Airport[] wantedAirports = new Airport[0];
        for (Airport airport : airportsByWeekDay) {
            if (airport.getStartTime().compareTo(startTime) > 0) {
                Airport[] newWantedAirports = new Airport[wantedAirports.length + 1];
                System.arraycopy(wantedAirports, 0, newWantedAirports, 0, wantedAirports.length);
                wantedAirports = newWantedAirports;
                wantedAirports[wantedAirports.length - 1] = airport;
            }
        }
        return wantedAirports;
    }

}
