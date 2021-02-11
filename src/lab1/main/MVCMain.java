package lab1.main;

import lab1.model.Airport;
import lab1.model.Factory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MVCMain {
    public static void main(String[] args) {
        Factory factory = new Factory(5);
        Airport[] airports = factory.getAllAirports();
        for (Airport airport : airports) {
            System.out.println(airport);
        }
        Airport[] airportsByDestination = factory.getAirportsByDestination("Berlin");
        System.out.println(airportsByDestination.length);
        for (Airport airport : airportsByDestination) {
            System.out.println(airport);
        }
//        System.out.format("%32s%10s%16s", airport.getDestination(), airport.getFlightNumber(), airport.getPlaneType());
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalTime time3 = LocalTime.of(16, 59);
//        System.out.println(time2.compareTo(time1));
//        airport.getByDestination("Dubai");
    }
}
