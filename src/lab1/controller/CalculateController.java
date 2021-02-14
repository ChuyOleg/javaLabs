package lab1.controller;

import lab1.model.Airport;
import lab1.model.Factory;
import lab1.view.CalculateView;

import java.time.LocalTime;
import java.util.Scanner;

public class CalculateController {

    private static final Scanner sc = new Scanner(System.in);
    private final CalculateView view = new CalculateView();
    private final Factory factory = new Factory(7);

    public void runScanner() {
        String action;
        view.printMessage(view.INPUT_DATA);
        while(!sc.hasNext("Exit") && !sc.hasNext("exit")) {
            action = sc.nextLine();
            calculate(action);
        }
        view.printMessage(view.END_DATA);
    }

    public void calculate(String action) {

        Airport[] airports = new Airport[0];

        if (action.equalsIgnoreCase("getAll") || action.equalsIgnoreCase("1")) {
            airports = showAllAirports();
        } else if (action.equalsIgnoreCase("getByDestination") || action.equalsIgnoreCase("2")) {
            airports = showAirportsByDestination();
        } else if (action.equalsIgnoreCase("getByWeekDay") || action.equalsIgnoreCase("3")) {
            airports = showAirportsByWeekDay();
        } else if (action.equalsIgnoreCase("GetByWeekDayAndTime") || action.equalsIgnoreCase("4")) {
            airports = showAirportsByWeekDayAndTime();
        } else {
            view.printMessage(view.WRONG_INPUT_DATA);
        }

        if (airports.length > 0) {
            view.printMessageAndResult(view.RESULT + System.lineSeparator() + view.COLUMNS, airports);
            view.printMessage(System.lineSeparator());
        } else {
            view.printMessage(view.NO_DATA + System.lineSeparator());
        }

        view.printMessage(view.INPUT_DATA);
    }

    public Airport[] showAllAirports() {
        return factory.getAllAirports();
    }

    public Airport[] showAirportsByDestination() {
        view.printMessage(view.FILTER_DESTINATION);
        String parameter = sc.nextLine();
        return factory.getAirportsByDestination(parameter);
    }

    public Airport[] showAirportsByWeekDay() {
        view.printMessage(view.FILTER_WEEKDAY);
        String parameter = sc.nextLine();
        return factory.getAirportsByWeekDay(parameter);
    }

    public Airport[] showAirportsByWeekDayAndTime() {
        view.printMessage(view.FILTER_WEEKDAY);
        String weekDay = sc.nextLine();
        view.printMessage(view.FILTER_HOUR);
        int hour = sc.nextInt();
        view.printMessage(view.FILTER_MINUTE);
        int minute = sc.nextInt();
        sc.nextLine();
        LocalTime startTime = LocalTime.of(hour, minute);
        return factory.getAirportsByWeekDayAndTime(weekDay, startTime);
    }
}
