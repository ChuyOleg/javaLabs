package lab1.service;

import lab1.model.Airport;
import lab1.view.CalculateView;

import java.io.*;

public class FileInteractingUtility {

    public static String[] getRowFromSource() {

        String[] data = new String[10];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("source.txt"))) {
            String str;

            while ( (str = br.readLine()) != null) {
                if (index == (data.length - 1)) {
                    String[] copyData = data;
                    data = new String[(data.length * 2)];
                    System.arraycopy(copyData, 0, data, 0, index);
                }
                data[index++] = str;
            }

        } catch (IOException err) {
            return data;
        }

        String[] correctData = new String[index];
        System.arraycopy(data, 0, correctData, 0, index);

        return correctData;
    }

    public static void saveResult(Airport[] result, String answer, CalculateView view) {

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
