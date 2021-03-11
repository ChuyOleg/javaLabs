package lab1.service;

import lab1.view.CalculateView;

import java.util.Scanner;

public class InputUtility {

    private static final Scanner sc = new Scanner(System.in);

    public static String inputStringValueWithScanner(CalculateView view, String message) {
        view.printMessage(message);
        return sc.nextLine();
    }

    public static int inputIntValueWithScanner(CalculateView view, String message, String messageForWrongType) {
        view.printMessage(message);
        while(!sc.hasNextInt()) {
            view.printMessage(messageForWrongType);
            view.printMessage(message);
            sc.nextLine();
        }
        return sc.nextInt();
    }

}
