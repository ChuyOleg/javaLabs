package lab1.main;

import lab1.model.Airport;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MVCMain {
    public static void main(String[] args) {
        String[] weekDays = {"Monday", "Thursday"};
        Airport airport = new Airport("Dubai", "AC777", "Boeing", LocalTime.of(22,13), weekDays, 7);
        System.out.format("%32s%10s%16s", airport.getDestination(), airport.getFlightNumber(), airport.getPlaneType());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalTime time1 = LocalTime.of(14, 59);
        LocalTime time2 = LocalTime.of(15, 59);
        LocalTime time3 = LocalTime.of(16, 59);
        System.out.println("");
//        System.out.println(airport);
//        System.out.println(time2.compareTo(time1));
        airport.getByDestination("Dubai");
    }
}
