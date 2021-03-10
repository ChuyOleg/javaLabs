package lab1.model;

import java.time.LocalTime;

public class Factory {

    public Airport[] createAirports(int numberOfObjects) {
        Airport[] airports = new Airport[numberOfObjects];
        for (int number = 0; number < numberOfObjects; number++) {
            String rDestination = getRandomDestination();
            String rFlightNumber = getRandomFlightNumber();
            String rPlaneType = getRandomPlaneType();
            LocalTime rStartTime = getRandomStartTime();
            String[] rWeekDays = getRandomWeekDays();
            int rPlaneNumber = getRandomPlaneNumber();
            airports[number] = new Airport(rDestination, rFlightNumber, rPlaneType, rStartTime, rWeekDays, rPlaneNumber);
        }
        return airports;
    }

    private int getRandomNumber(int end) {
        return (int) Math.floor(Math.random() * (end + 1));
    }

    private String getRandomDestination() {
        int randomNum = getRandomNumber(DataSource.destinations.length - 1);
        return DataSource.destinations[randomNum];
    }

    private String getRandomFlightNumber() {
        int randomNum = getRandomNumber(DataSource.flightNumbers.length - 1);
        return DataSource.flightNumbers[randomNum];
    }

    private String getRandomPlaneType() {
        int randomNum = getRandomNumber(DataSource.planeTypes.length - 1);
        return DataSource.planeTypes[randomNum];
    }

    private LocalTime getRandomStartTime() {
        int randomNum = getRandomNumber(DataSource.startTimes.length - 1);
        return DataSource.startTimes[randomNum];
    }

    private String[] getRandomWeekDays() {
        String[] weekDays = new String[2];
        int randomNum1 = getRandomNumber(DataSource.weekDays.length - 1);
        int randomNum2 = getRandomNumber(DataSource.weekDays.length - 1);
        if (randomNum1 == randomNum2) {
            if (randomNum2 != (DataSource.weekDays.length - 1)) {
                randomNum2++;
            } else {
                randomNum2--;
            }
        }
        weekDays[0] = DataSource.weekDays[randomNum1];
        weekDays[1] = DataSource.weekDays[randomNum2];
        return weekDays;
    }

    private int getRandomPlaneNumber() {
        int randomNum = getRandomNumber(DataSource.planeNumbers.length - 1);
        return DataSource.planeNumbers[randomNum];
    }

}
