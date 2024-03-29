package lab1.controller;

import lab1.controller.exceptions.NonExistentAnswerException;
import lab1.controller.exceptions.NonExistentDayException;
import org.json.simple.parser.ParseException;
import lab1.model.Airport;
import lab1.service.InputUtility;
import lab1.view.CalculateView;
import lab1.model.Model;
import lab1.controller.validator.Validator;
import lab1.controller.exceptions.TimeOutOfBoundaryException;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class CalculateController {

    private final CalculateView view;
    private final Model model;

    public CalculateController() {
        view = new CalculateView();

        Model fakeModel = null;
        try {
            fakeModel = new Model();
        } catch (IOException | ParseException err) {
            view.printLNMessage(view.SOURCE_FILE_PROBLEM);
            view.printLNMessage(view.END_DATA);
            System.exit(0);
        }
        model = fakeModel;
    }

    public void runProgram() {
        while(true) {
            String action = InputUtility.inputStringValueWithScanner(view, view.INPUT_DATA);
            calculateAction(action);
        }
    }

    public void calculateAction(String action) {

        if (action.equalsIgnoreCase("getAll") || action.equals("1")) {
            showAllAirports();
        } else if (action.equalsIgnoreCase("getByDestination") || action.equals("2")) {
            showAirportsByDestination();
        } else if (action.equalsIgnoreCase("getByWeekDay") || action.equals("3")) {
            showAirportsByWeekDay();
        } else if (action.equalsIgnoreCase("GetByWeekDayAndTime") || action.equals("4")) {
            showAirportsByWeekDayAndTime();
        } else if (action.equalsIgnoreCase("Exit") || action.equals("5")) {
            closeProgram();
        } else {
            view.printMessage(view.WRONG_INPUT_DATA);
        }

    }

    public void showAllAirports() {

        List<Airport> airports = model.getAllAirports();
        view.printMessageAndResult(airports);

        saveIntermediateData(airports);

    }

    public void showAirportsByDestination() {

        String destination = InputUtility.inputStringValueWithScanner(view, view.FILTER_DESTINATION);
        List<Airport> airports = model.getAirportsByDestination(destination);
        view.printMessageAndResult(airports);

        saveIntermediateData(airports);

    }

    public void showAirportsByWeekDay() {

        String weekDay = getWeekDayFromUser();
        List<Airport> airports = model.getAirportsByWeekDay(weekDay);
        view.printMessageAndResult(airports);

        saveIntermediateData(airports);

    }

    public void showAirportsByWeekDayAndTime() {

        String weekDay = getWeekDayFromUser();
        LocalTime startTime = getLocalTimeFromUser();

        List<Airport> airports = model.getAirportsByWeekDayAndTime(weekDay, startTime);
        view.printMessageAndResult(airports);

        saveIntermediateData(airports);

    }

    private void saveIntermediateData(List<Airport> airports) {
        String answer = getAnswer();
        if (answer.equalsIgnoreCase("yes")) {
            try {
                model.saveIntermediateData(airports);
                view.printLNMessage(view.SUCCESSFULLY_SAVING);
            } catch (IOException err) {
                view.printLNMessage(view.SAVING_ERROR);
            }
        }
    }

    private String getAnswer() {

        while (true) {
            try {
                String answer = InputUtility.inputStringValueWithScanner(view, view.SAVE_OR_NOT);
                Validator.checkForCorrectAnswer(answer, view);
                return answer;
            } catch (NonExistentAnswerException err) {
                view.printLNMessage(err.getMessage());
            }
        }

    }

    private void closeProgram() {

        try {
            model.rewriteSourceFile();
            view.printLNMessage(view.SUCCESSFULLY_SAVING_END);
        } catch (IOException err) {
            view.printLNMessage(view.SOURCE_FILE_PROBLEM_END);
        }

        view.printMessage(view.END_DATA);
        System.exit(0);

    }

    private LocalTime getLocalTimeFromUser() {

        int hour;
        while (true) {
            try {
                hour = InputUtility.inputIntValueWithScanner(view, view.FILTER_HOUR, view.HOUR_MUST_BE_INTEGER);
                Validator.checkForCorrectHour(hour, view);
                break;
            } catch (TimeOutOfBoundaryException err) {
                view.printLNMessage(err.getMessage());
            }
        }

        int minute;
        while (true) {
            try {
                minute = InputUtility.inputIntValueWithScanner(view, view.FILTER_MINUTE, view.MINUTE_MUST_BE_INTEGER);
                Validator.checkForCorrectMinute(minute, view);
                break;
            } catch (TimeOutOfBoundaryException err) {
                view.printLNMessage(err.getMessage());
            }
        }

        return LocalTime.of(hour, minute);
    }

    private String getWeekDayFromUser() {
        while (true) {
            try {
                String weekDay = InputUtility.inputStringValueWithScanner(view, view.FILTER_WEEKDAY);
                Validator.checkForCorrectDay(weekDay, view);
                return weekDay;
            } catch (NonExistentDayException err) {
                view.printLNMessage(err.getMessage());
            }
        }
    }

}
