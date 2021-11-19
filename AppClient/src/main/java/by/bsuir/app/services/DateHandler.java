package by.bsuir.app.services;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHandler {

    public static String convertDateForDB(String date) throws DateTimeException{

        if (date.length() < 10)
            throw new DateTimeException("Неверная дата. (dd/MM/yyyy)");

        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,10));

        if (year > 2021 || year < 2015)
            throw new DateTimeException("Некорректный год.");

        System.out.println(day + "." + month + "." + year);
        isDateValid(year,month,day);

        return year + "-" + month + "-" + day;
    }

    public static boolean isDateValid(int year, int month, int day) throws DateTimeException{

        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new DateTimeException("Некорректная дата.");
        }
        return true;
    }

    public static String getCurrentTimeStampString() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        return  formattedDate;
    }
}
