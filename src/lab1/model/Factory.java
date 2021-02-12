package lab1.model;

import java.time.LocalTime;

public class Factory {

    private Airport[] airports;

    public Factory(int numberOfObjects) {
        airports = new Airport[numberOfObjects];
        DataSource data = new DataSource();
        for (int number = 0; number < numberOfObjects; number++) {
            String rDestination = getRandomDestination(data);
            String rFlightNumber = getRandomFlightNumber(data);
            String rPlaneType = getRandomPlaneType(data);
            LocalTime rStartTime = getRandomStartTime(data);
            String[] rWeekDays = getRandomWeekDays(data);
            int rPlaneNumber = getRandomPlaneNumber(data);
            airports[number] = new Airport(rDestination, rFlightNumber, rPlaneType, rStartTime, rWeekDays, rPlaneNumber);
        }
    }

    public int getRandomNumber(int end) {
        return (int) Math.floor(Math.random() * (end + 1));
    }

    public String getRandomDestination(DataSource data) {
        int randomNum = getRandomNumber(data.destinations.length - 1);
        return data.destinations[randomNum];
    }

    public String getRandomFlightNumber(DataSource data) {
        int randomNum = getRandomNumber(data.flightNumbers.length - 1);
        return data.flightNumbers[randomNum];
    }

    public String getRandomPlaneType(DataSource data) {
        int randomNum = getRandomNumber(data.planeTypes.length - 1);
        return data.planeTypes[randomNum];
    }

    public LocalTime getRandomStartTime(DataSource data) {
        int randomNum = getRandomNumber(data.startTimes.length - 1);
        return data.startTimes[randomNum];
    }

    public String[] getRandomWeekDays(DataSource data) {
        String[] weekDays = new String[2];
        int randomNum1 = getRandomNumber(data.weekDays.length - 1);
        int randomNum2 = getRandomNumber(data.weekDays.length - 1);
        if (randomNum1 == randomNum2) {
            if (randomNum2 != (data.weekDays.length - 1)) {
                randomNum2++;
            } else {
                randomNum2--;
            }
        }
        weekDays[0] = data.weekDays[randomNum1];
        weekDays[1] = data.weekDays[randomNum2];
        return weekDays;
    }

    public int getRandomPlaneNumber(DataSource data) {
        int randomNum = getRandomNumber(data.planeNumbers.length - 1);
        return data.planeNumbers[randomNum];
    }

    public Airport[] getAllAirports() {
        return airports;
    }

    public Airport[] getAirportsByDestination(String destination) {
        Airport[] wantedAirports = new Airport[0];
        for (Airport airport : airports) {
            if (airport.getDestination() == destination) {
                Airport[] newWantedAirports = new Airport[wantedAirports.length + 1];
                System.arraycopy(wantedAirports, 0, newWantedAirports, 0, wantedAirports.length);
                wantedAirports = newWantedAirports;
                wantedAirports[wantedAirports.length - 1] = airport;
            }
        }
        return wantedAirports;
    }

    public Airport[] getAirportsByWeekDay(String weekDay) {
        Airport[] wantedAirports = new Airport[0];
        outer:
        for (Airport airport : airports) {
            String[] weekDays = airport.getWeekDays();
            for (String day : weekDays) {
                if (day.equals(weekDay)) {
                    Airport[] newWantedAirports = new Airport[wantedAirports.length + 1];
                    System.arraycopy(wantedAirports, 0, newWantedAirports, 0, wantedAirports.length);
                    wantedAirports = newWantedAirports;
                    wantedAirports[wantedAirports.length - 1] = airport;
                    continue outer;
                }
            }
        }
        return wantedAirports;
    }

    public Airport[] getAirportsByWeekDayAndTime(String weekDay, LocalTime startTime) {
        Airport[] airportsByWeekDay = getAirportsByWeekDay(weekDay);
        Airport[] wantedAirports = new Airport[0];
        for (Airport airport : airportsByWeekDay) {
            if (airport.getStartTime().compareTo(startTime) == 1) {
                Airport[] newWantedAirports = new Airport[wantedAirports.length + 1];
                System.arraycopy(wantedAirports, 0, newWantedAirports, 0, wantedAirports.length);
                wantedAirports = newWantedAirports;
                wantedAirports[wantedAirports.length - 1] = airport;
            }
        }
        return wantedAirports;
    }
}
