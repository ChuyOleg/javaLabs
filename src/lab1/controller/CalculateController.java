package lab1.controller;

import lab1.model.Airport;
import lab1.service.InputUtility;
import lab1.view.CalculateView;
import lab1.model.Model;
import lab1.controller.validator.Validator;
//import lab1.controller.exceptions.FakeNumberException;
import lab1.controller.exceptions.TimeOutOfBoundaryException;

import java.time.LocalTime;
// винести сканнер в окремий клас
// з перевіркою на стрінг і інтежер

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
    }

    public void showAirportsByDestination() {
        String parameter = InputUtility.inputStringValueWithScanner(view, view.FILTER_DESTINATION);
        Airport[] airports = model.getAirportsByDestination(parameter);
        view.printMessageAndResult(airports);
    }

    public void showAirportsByWeekDay() {
        String parameter = InputUtility.inputStringValueWithScanner(view, view.FILTER_WEEKDAY);
        Airport[] airports = model.getAirportsByWeekDay(parameter);
        view.printMessageAndResult(airports);
    }

    public void showAirportsByWeekDayAndTime() {

        String weekDay = InputUtility.inputStringValueWithScanner(view, view.FILTER_WEEKDAY);

        int hour;
        while (true) {
            try {
                hour = getHourFromUser();
                break;
            } catch (TimeOutOfBoundaryException err) {
                System.out.println(err.getMessage());
            }
        }

        int minute;
        while (true) {
            try {
                minute = getMinuteFromUser();
                break;
            } catch (TimeOutOfBoundaryException err) {
                System.out.println(err.getMessage());
            }
        }

        LocalTime startTime = LocalTime.of(hour, minute);
        Airport[] airports = model.getAirportsByWeekDayAndTime(weekDay, startTime);
        view.printMessageAndResult(airports);
    }

     private int getHourFromUser() throws TimeOutOfBoundaryException {
        int hour = InputUtility.inputIntValueWithScanner(view, view.FILTER_HOUR, view.HOUR_MUST_BE_INTEGER);
        Validator.checkForCorrectHour(hour, view);
        return hour;
    }

    private int getMinuteFromUser() throws TimeOutOfBoundaryException {
        int minute = InputUtility.inputIntValueWithScanner(view, view.FILTER_MINUTE, view.MINUTE_MUST_BE_INTEGER);
        Validator.checkForCorrectMinute(minute, view);
        return minute;
    }
}
