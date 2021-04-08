package lab1.model;

import lab1.service.FileInteractingUtility;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<Airport> airports;

    public List<Airport> getAllAirports() {
        return airports;
    }

    public List<Airport> getAirportsByDestination(String destination) {

        List<Airport> wantedAirports = new ArrayList<>();

        for (Airport airport : airports) {
            if (airport.getDestination().equalsIgnoreCase(destination)) {
                wantedAirports.add(airport);
            }
        }

        return wantedAirports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public List<Airport> getAirportsByWeekDay(String weekDay) {

        List<Airport> wantedAirports = new ArrayList<>();

        outer:
        for (Airport airport : airports) {
            List<String> weekDays = airport.getWeekDays();
            for (String day : weekDays) {
                if (day.equalsIgnoreCase(weekDay)) {
                    wantedAirports.add(airport);
                    continue outer;
                }
            }
        }

        return wantedAirports;
    }

    public List<Airport> getAirportsByWeekDayAndTime(String weekDay, LocalTime startTime) {

        List<Airport> wantedAirports = new ArrayList<>();

        List<Airport> airportsByWeekDay = getAirportsByWeekDay(weekDay);

        for (Airport airport : airportsByWeekDay) {
            if (airport.getStartTime().compareTo(startTime) > 0) {
                wantedAirports.add(airport);
            }
        }

        return wantedAirports;
    }

}
