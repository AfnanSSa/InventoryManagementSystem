package com.TRA.tra24Springboot.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelperUtils {
    // Default date format pattern
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    // method to convert a given Date object into a string based on the default date format
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return sdf.format(date);
    }

    //method to convert string date format to Date object
    public static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return sdf.parse(dateString);
    }

    /*
     * method to add a specified number of days to a given Date object.
     * @param date the original Date object.
     * @param days the number of days to be added.
     * @return the new Date object with the added days.
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /*
     * method to subtract a specified number of days from a given Date object.
     * @param date the original Date object.
     * @param days the number of days to be subtracted.
     * @return the new Date object with the subtracted days.
     */
    public static Date subtractDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        return calendar.getTime();
    }

    /*
     * method to calculate the number of days between two Date objects.
     * @param startDate the start Date object.
     * @param endDate the end Date object.
     * @return the number of days between the two dates.
     */
    public static int daysBetween(Date startDate, Date endDate) {
        long diffInMillis = endDate.getTime() - startDate.getTime();
        return (int) (diffInMillis / (1000 * 60 * 60 * 24));
    }

    /*
     * method to format a given Date object into a string based on the specified date format.
     * @param date the Date object to be formatted.
     * @param format the date format pattern.
     * @return formatted date string.
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /*
     * method to parse a date string into a Date object based on the specified date format.
     * @param dateString the date string to be parsed.
     * @param format the date format pattern.
     * @return the parsed Date object.
     * @throws ParseException if the date string is not in the expected format.
     */
    public static Date parseDate(String dateString, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateString);
    }
}
