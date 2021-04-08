package lab1.model;


//public class Factory {

//    public Airport[] createAirports() {
//
//        Airport[] airports = FileInteractingUtility.getRowFromSource();
//
////        String[] data = FileInteractingUtility.getRowFromSource();
////        Airport[] airports = new Airport[data.length];
////
////        int index = 0;
////        for (String raw : data) {
////            airports[index++] = getAirportFromString(raw);
////        }
//
//        return airports;
//    }

//    private Airport getAirportFromString(String raw) {
//        String[] values = raw.split("; ");
//        String destination = values[0];
//        String flightNumber = values[1];
//        String planeType = values[2];
//        LocalTime startTime = getStartTime(values[3]);
//        String[] weekDays = getWeekDays(values[4]);
//        int planeNumber = Integer.parseInt(values[5]);
//        return new Airport(destination, flightNumber, planeType, startTime, weekDays, planeNumber);
//    }

//}
