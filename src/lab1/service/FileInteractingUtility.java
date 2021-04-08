package lab1.service;

import lab1.model.Airport;
import lab1.view.CalculateView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileInteractingUtility {

    public static List<Airport> getRowFromSource() {

        JSONParser jsonParser = new JSONParser();

        List<Airport> airports = new ArrayList<>();

        try (FileReader reader = new FileReader("source.json")) {

            JSONObject mainObj = (JSONObject) jsonParser.parse(reader);
            JSONArray airportsData = (JSONArray) mainObj.get("airports");

            JSONObject obj = (JSONObject) airportsData.get(1);
            String dest = (String) obj.get("destination");
            String flightNumber = (String) obj.get("flightNumber");
            String planeType = (String) obj.get("planeType");
            LocalTime startTime = parseStartTime((String) obj.get("startTime"));
            List<String> weekDays = parseWeekDays((JSONArray) obj.get("weekDays"));
            long planeNumber = (long) obj.get("planeNumber");

            airports.add(new Airport(dest, flightNumber, planeType, startTime, weekDays, (int) planeNumber));

        } catch (IOException | ParseException err) {
            System.out.println("EXCEPTION!");
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
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("journal.txt"))) {
                // fix saving
                for (Airport airport : result) {
                    writer.write(airport.toString());
                }
                writer.newLine();
                view.printLNMessage(view.SUCCESSFUL_SAVING);
            } catch (IOException err) {
                view.printLNMessage(view.SAVING_ERROR);
            }
        }

    }
}
