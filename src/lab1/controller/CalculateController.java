package lab1.controller;

import lab1.model.Airport;
import lab1.view.CalculateView;
import lab1.model.Model;
import lab1.validator.Validator;
import lab1.exceptions.FakeNumberException;
import lab1.exceptions.TimeOutOfBoundaryException;

import java.time.LocalTime;
import java.util.Scanner;

public class CalculateController {

    private static final Scanner sc = new Scanner(System.in);
    private final CalculateView view = new CalculateView();
    private final Model model = new Model();

    public void runProgram() {
        String action;
        view.printMessage(view.INPUT_DATA);
        while(true) {
            action = sc.nextLine();
            calculateAction(action);
        }
    }

    public void calculateAction(String action) {

        try {

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

        } catch (FakeNumberException | TimeOutOfBoundaryException err) {
            System.out.println(err.getMessage());
        }

        view.printMessage(view.INPUT_DATA);
    }

    public void showAllAirports() {
        Airport[] airports = model.getAllAirports();
        view.printMessageAndResult(airports);
    }

    public void showAirportsByDestination() {
        view.printMessage(view.FILTER_DESTINATION);
        String parameter = sc.nextLine();
        Airport[] airports = model.getAirportsByDestination(parameter);
        view.printMessageAndResult(airports);
    }

    public void showAirportsByWeekDay() {
        view.printMessage(view.FILTER_WEEKDAY);
        String parameter = sc.nextLine();
        Airport[] airports = model.getAirportsByWeekDay(parameter);
        view.printMessageAndResult(airports);
    }

    public void showAirportsByWeekDayAndTime() throws FakeNumberException, TimeOutOfBoundaryException {
        view.printMessage(view.FILTER_WEEKDAY);
        String weekDay = sc.nextLine();
        view.printMessage(view.FILTER_HOUR);
        String hour = sc.nextLine();
        if (Validator.isNotInteger(hour)) {
            throw new FakeNumberException("Incorrect hour. Must be integer!");
        }
        if (!Validator.isCorrectHour(hour)) {
            throw new TimeOutOfBoundaryException("Incorrect hour. (0 - 23)");
        }
        view.printMessage(view.FILTER_MINUTE);
        String minute = sc.nextLine();
        if (Validator.isNotInteger(minute)) {
            throw new FakeNumberException("Incorrect minute. Must be integer!");
        }
        if (!Validator.isCorrectMinute(minute)) {
            throw new TimeOutOfBoundaryException("Incorrect minute. (0 - 59)");
        }
        LocalTime startTime = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
        System.out.println(startTime);
        Airport[] airports = model.getAirportsByWeekDayAndTime(weekDay, startTime);
        view.printMessageAndResult(airports);
    }
}
