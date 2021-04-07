package lab1.controller;

import lab1.controller.exceptions.NonExistentAnswerException;
import lab1.controller.exceptions.NonExistentDayException;
import lab1.model.Airport;
import lab1.service.FileInteractingUtility;
import lab1.service.InputUtility;
import lab1.view.CalculateView;
import lab1.model.Model;
import lab1.controller.validator.Validator;
import lab1.controller.exceptions.TimeOutOfBoundaryException;

import java.time.LocalTime;

public class CalculateController {

    private final CalculateView view = new CalculateView();
    private final Model model = new Model();

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
            view.printMessage(view.END_DATA);
            System.exit(0);
        } else {
            view.printMessage(view.WRONG_INPUT_DATA);
        }

    }

    public void showAllAirports() {
        Airport[] airports = model.getAllAirports();
        view.printMessageAndResult(airports);
        String answer = getAnswer();
        FileInteractingUtility.saveResult(airports, answer, view);
    }

    public void showAirportsByDestination() {
        String destination = InputUtility.inputStringValueWithScanner(view, view.FILTER_DESTINATION);
        Airport[] airports = model.getAirportsByDestination(destination);
        view.printMessageAndResult(airports);
        String answer = getAnswer();
        FileInteractingUtility.saveResult(airports, answer, view);
    }

    public void showAirportsByWeekDay() {

        String weekDay = getWeekDayFromUser();
        Airport[] airports = model.getAirportsByWeekDay(weekDay);
        view.printMessageAndResult(airports);
        String answer = getAnswer();
        FileInteractingUtility.saveResult(airports, answer, view);

    }

    public void showAirportsByWeekDayAndTime() {

        String weekDay = getWeekDayFromUser();
        LocalTime startTime = getLocalTimeFromUser();

        Airport[] airports = model.getAirportsByWeekDayAndTime(weekDay, startTime);
        view.printMessageAndResult(airports);
        String answer = getAnswer();
        FileInteractingUtility.saveResult(airports, answer, view);

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
