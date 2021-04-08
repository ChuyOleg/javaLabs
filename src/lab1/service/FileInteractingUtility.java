package lab1.service;

import lab1.model.Airport;
import lab1.view.CalculateView;
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

public class FileInteractingUtility {

    private static final String SOURCE_FILE_NAME = "source.json";
    private static final String JOURNAL_PATH = "src/lab1/journal.txt";

    private static final String FIELD_NAME = "airports";

    private static final String DESTINATION_TAG = "destination";
    private static final String FIGHT_NUMBER_TAG = "flightNumber";
    private static final String PLANE_TYPE_TAG = "planeType";
    private static final String START_TIME_TAG = "startTime";
    private static final String WEEK_DAYS_TAG = "weekDays";
    private static final String PLANE_NUMBER_TAG = "planeNumber";


    public static List<Airport> parseSourceData(CalculateView view) {

        JSONParser jsonParser = new JSONParser();

        List<Airport> airports = new ArrayList<>();

        try (FileReader reader = new FileReader(SOURCE_FILE_NAME)) {

            JSONObject mainObj = (JSONObject) jsonParser.parse(reader);
            JSONArray airportsData = (JSONArray) mainObj.get(FIELD_NAME);

            for (Object airportData: airportsData) {

                JSONObject airportObj = (JSONObject) airportData;
                String dest = (String) airportObj.get(DESTINATION_TAG);
                String flightNumber = (String) airportObj.get(FIGHT_NUMBER_TAG);
                String planeType = (String) airportObj.get(PLANE_TYPE_TAG);
                LocalTime startTime = parseStartTime((String) airportObj.get(START_TIME_TAG));
                List<String> weekDays = parseWeekDays((JSONArray) airportObj.get(WEEK_DAYS_TAG));
                long planeNumber = (long) airportObj.get(PLANE_NUMBER_TAG);

                airports.add(new Airport(dest, flightNumber, planeType, startTime, weekDays, (int) planeNumber));
            }

        } catch (IOException | ParseException err) {
            view.printLNMessage(view.SOURCE_FILE_PROBLEM);
            view.printLNMessage(view.END_DATA);
            System.exit(0);
        }

        return airports;

    }

    private static List<String> parseWeekDays(JSONArray jsonArray) {
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

    public static void saveResult(List<Airport> result, String answer, CalculateView view) {

        if (answer.equalsIgnoreCase("yes")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(JOURNAL_PATH, true))) {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime nowDate = LocalDateTime.now();
                writer.write(dtf.format(nowDate));
                writer.newLine();

                for (Airport airport : result) {
                    writer.write(airport.toString());
                    writer.newLine();
                }
                writer.newLine();
                view.printLNMessage(view.SUCCESSFULLY_SAVING);
            } catch (IOException err) {
                view.printLNMessage(view.SAVING_ERROR);
            }
        }

    }

    // JSONObject uses raw type collections internally
    @SuppressWarnings("unchecked")
    public static void rewriteSourceFile(String answer, List<Airport> airports, CalculateView view) {

        if (answer.equalsIgnoreCase("yes")) {
            try (FileWriter writer = new FileWriter(SOURCE_FILE_NAME)) {

                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();

                for (Airport airport : airports) {
                    JSONObject airportObject = new JSONObject();

                    airportObject.put(DESTINATION_TAG, airport.getDestination());
                    airportObject.put(FIGHT_NUMBER_TAG, airport.getFlightNumber());
                    airportObject.put(PLANE_TYPE_TAG, airport.getPlaneType());
                    airportObject.put(START_TIME_TAG, airport.getStartTime().toString());
                    airportObject.put(WEEK_DAYS_TAG, airport.getWeekDays());
                    airportObject.put(PLANE_NUMBER_TAG, airport.getPlaneNumber());

                    jsonArray.add(airportObject);
                }

                jsonObject.put(FIELD_NAME, jsonArray);

                writer.write(jsonObject.toJSONString());

                view.printLNMessage(view.SUCCESSFULLY_SAVING);

            } catch (IOException err) {
                view.printLNMessage(view.SOURCE_FILE_PROBLEM_END);
            }
        }

    }
}
