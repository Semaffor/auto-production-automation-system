package by.bsuir.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class Services {

    public static String generateNewPassword(String login) {
        Random rand = new Random();
        char nextChar;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 20; i++) {
            // lowercase characters go from 97 to 122
            nextChar = (char) (rand.nextInt(26) + 97);
            sb.append(nextChar);
            if ((i + 1) % 5 == 0 && i != 19) sb.append('-');
        }

        return sb.toString();
//        return Math.abs(rand.nextInt() * Integer.parseInt(String.valueOf(login.hashCode())));
    }

//    public static Date convertTimestampInDate(Timestamp)
}
