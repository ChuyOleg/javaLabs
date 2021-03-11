package lab1.service;

import lab1.view.CalculateView;

import java.util.Scanner;

public class InputUtility {

    private static final Scanner sc = new Scanner(System.in);

    public static String inputStringValueWithScanner(CalculateView view, String inviteMessage) {
        view.printMessage(inviteMessage);
        return sc.nextLine();
    }

    public static int inputIntValueWithScanner(CalculateView view, String inviteMessage, String messageForWrongType) {
        view.printMessage(inviteMessage);
        while(!sc.hasNextInt()) {
            view.printMessage(messageForWrongType, inviteMessage);
            sc.nextLine();
        }
        return Integer.parseInt(sc.nextLine());
    }

}
