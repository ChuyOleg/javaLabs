package lab1.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileInteracting {

    private final String SOURCE_FILE_NAME = "source.json";

    public List<Airport> parseSourceData() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();

        List<Airport> airports = new ArrayList<>();

        try (FileReader reader = new FileReader(SOURCE_FILE_NAME)) {

            JSONObject mainObj = (JSONObject) jsonParser.parse(reader);
            JSONArray airportsData = (JSONArray) mainObj.get(JSON_Tags.MAIN_FIELD_NAME);

            for (Object airportData : airportsData) {

                JSONObject airportObj = (JSONObject) airportData;

                airports.add(new Airport(
                        (String) airportObj.get(JSON_Tags.DESTINATION_TAG),
                        (String) airportObj.get(JSON_Tags.FIGHT_NUMBER_TAG),
                        (String) airportObj.get(JSON_Tags.PLANE_TYPE_TAG),
                        parseStartTime((String) airportObj.get(JSON_Tags.START_TIME_TAG)),
                        parseWeekDays((JSONArray) airportObj.get(JSON_Tags.WEEK_DAYS_TAG)),
                        (int) (long) airportObj.get(JSON_Tags.PLANE_NUMBER_TAG)
                ));

            }
        }

        return airports;

    }

    private List<String> parseWeekDays(JSONArray jsonArray) {
        List<String> weekDay = new ArrayList<>();
        for (Object day : jsonArray) {
            weekDay.add((String) day);
        }
        return weekDay;
    }

    private static LocalTime parseStartTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));
        return LocalTime.of(hour, minute);
    }

    public void saveResult(List<Airport> result) throws IOException {

        final String JOURNAL_PATH = "src/lab1/journal.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JOURNAL_PATH, true))) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime nowDate = LocalDateTime.now();
            writer.write(dtf.format(nowDate));
            writer.newLine();

            // ?
            writer.write(String.format("%20s%20s%20s%20s%30s%20s",
            "Destination",
            "FlightNumber",
            "PlaneType",
            "StartTime",
            "WeekDays",
            "PlaneNumber"));

            writer.newLine();
            for (Airport airport : result) {
                writer.write(airport.toString());
                writer.newLine();
            }
            writer.newLine();
        }

    }

    // JSONObject uses raw type collections internally
    @SuppressWarnings("unchecked")
    public void rewriteSourceFile(List<Airport> airports) throws IOException {

        try (FileWriter writer = new FileWriter(SOURCE_FILE_NAME)) {

            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            for (Airport airport : airports) {
                JSONObject airportObject = new JSONObject();

                airportObject.put(JSON_Tags.DESTINATION_TAG, airport.getDestination());
                airportObject.put(JSON_Tags.FIGHT_NUMBER_TAG, airport.getFlightNumber());
                airportObject.put(JSON_Tags.PLANE_TYPE_TAG, airport.getPlaneType());
                airportObject.put(JSON_Tags.START_TIME_TAG, airport.getStartTime().toString());
                airportObject.put(JSON_Tags.WEEK_DAYS_TAG, airport.getWeekDays());
                airportObject.put(JSON_Tags.PLANE_NUMBER_TAG, airport.getPlaneNumber());

                jsonArray.add(airportObject);
            }

            jsonObject.put(JSON_Tags.MAIN_FIELD_NAME, jsonArray);

            writer.write(jsonObject.toJSONString());
        }

    }
}
