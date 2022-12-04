package impl;

import interfaces.IAdvancedTimestamp;
import util.TimestampHelper;

/**
 * Класс, хранящий временную метку в часовом поясе UTC+00:00
 * c дополнительными методами
 */
public class AdvancedTimestamp extends UnixTimestamp implements IAdvancedTimestamp {

    protected class DatetimeExtractor {
        int day = 0; // от 0 до 30
        int month = 0; // от 0 до 11
        int year = 1970;
        int hour = 0;
        int minute = 0;
        int second = 0;
        int weekday = 3; // 0..6, 1 января 1970 - это четверг

        final static int SECONDS_IN_MINUTE = 60;
        final static int SECONDS_IN_HOUR = 60 * SECONDS_IN_MINUTE;
        final static int SECONDS_IN_DAY = 24 * SECONDS_IN_HOUR;

        int SECONDS_IN_MONTH(int month, int year) {
            return TimestampHelper.daysInMonths(year)[month];
        }

        int SECONDS_IN_YEAR(int year) {
            return SECONDS_IN_DAY * (TimestampHelper.isLeap(year) ? 366 : 365);
        }

        void extract() {
            int s = secondsSince1970;
            // если в секундах есть времечко на целый год, то нужно его вычесть
            while (s >= SECONDS_IN_YEAR(year)) {
                s -= SECONDS_IN_YEAR(year);
                year++;
            }
            // TODO: доделать аналогично с другими компонентами даты
        }
    }

    private final DatetimeExtractor extractor = new DatetimeExtractor();

    public AdvancedTimestamp() {
        super();
    }

    public AdvancedTimestamp(int secondsSince1970) {
        super(secondsSince1970);
    }

    public AdvancedTimestamp(String str) {
        super(str);
    }

    public Weekday getWeekday() { return Weekday.values()[extractor.weekday];}
    public int getDay() { return 1 + extractor.day; }
    public int getMonth() {
        return 1 + extractor.month;
    }
    public int getYear() {
        return extractor.year;
    }
    public int getHour() { return extractor.hour; }
    public int getMinutes() {
        return extractor.minute;
    }
    public int getSeconds() {
        return extractor.second;
    }

    public static IAdvancedTimestamp create(int secondsSince1970) { return new AdvancedTimestamp(secondsSince1970); }
    public static IAdvancedTimestamp create(String s) { return new AdvancedTimestamp(s); }

}
