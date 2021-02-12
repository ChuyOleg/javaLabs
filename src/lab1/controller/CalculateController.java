package lab1.controller;

import lab1.model.Airport;
import lab1.model.Factory;
import lab1.view.CalculateView;
import java.util.Scanner;

public class CalculateController {

    private static final Scanner sc = new Scanner(System.in);
    private final CalculateView view = new CalculateView();
    private final Factory factory = new Factory(5);

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
        if (action.equalsIgnoreCase("getAll")) {
            Airport[] airports = factory.getAllAirports();
            view.printMessageAndResult(view.RESULT, airports);
        } else if (action.equalsIgnoreCase("getByDestination")) {
            Airport[] airports = factory.getAirportsByDestination("Abu Dhabi");
            view.printMessageAndResult(view.RESULT, airports);
        }
    }
}
