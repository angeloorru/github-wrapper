package helper.date;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*
Helper class used for formatting a date.
 */
public class DateFormatConverter {

    /**
     * @return String date
     * @desc Get the current date.
     */
    public String getCurrentDate() {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        return today.format(dateFormatter);
    }

    /**
     * @param date
     * @return String converted date
     */
    public String convertDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return formatter.format(date);
    }
}
