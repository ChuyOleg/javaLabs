package lab1.model;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private final FileInteracting fileInteracting;
    private List<Airport> airports;

    public Model() throws IOException, ParseException {
        fileInteracting = new FileInteracting();
        airports = fileInteracting.parseSourceData();
    }

    public void saveIntermediateData(List<Airport> result) throws IOException {
        fileInteracting.saveResult(result);
    }

    public void rewriteSourceFile() throws IOException {
        fileInteracting.rewriteSourceFile(airports);
    }

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
