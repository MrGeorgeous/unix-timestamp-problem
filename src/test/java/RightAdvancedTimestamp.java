import impl.AdvancedTimestamp;
import interfaces.IAdvancedTimestamp;
import interfaces.IUnixTimestamp;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class RightAdvancedTimestamp extends RightUnixTimestamp implements IAdvancedTimestamp {

    public ZonedDateTime get() {
        Instant i = Instant.ofEpochSecond(s);
        return ZonedDateTime.ofInstant(i, utc);
    }

    @Override
    public Weekday getWeekday() {
        return Weekday.values()[get().getDayOfWeek().getValue() - 1];
    }

    @Override
    public int getDay() {
        return get().getDayOfMonth();
    }

    @Override
    public int getMonth() {
        return get().getMonthValue();
    }

    @Override
    public int getYear() {
        return get().getYear();
    }

    @Override
    public int getHour() {
        return get().getHour();
    }

    @Override
    public int getMinutes() {
        return get().getMinute();
    }

    @Override
    public int getSeconds() {
        return get().getSecond();
    }

    public static IAdvancedTimestamp create(int secondsSince1970) {
        if (secondsSince1970 < 0){
            throw new IllegalArgumentException("" + secondsSince1970);
        }
        RightAdvancedTimestamp ts = new RightAdvancedTimestamp();
        ts.s = secondsSince1970;
        return ts;
    }

    public static IAdvancedTimestamp create(String s) {
        long unix = -1;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDateTime r = LocalDate.parse(s, df).atStartOfDay();
            ZonedDateTime z = r.atZone(ZoneId.of("Etc/UTC"));
            unix = z.toEpochSecond();
        } catch (DateTimeParseException e) {
        }
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            LocalDateTime r = LocalDateTime.parse(s, df);
            ZonedDateTime z = r.atZone(ZoneId.of("Etc/UTC"));
            unix = z.toEpochSecond();
        } catch (DateTimeParseException e) {
        }
        if (unix < 0) {
            throw new IllegalArgumentException(s + " " + unix);
        }
        if (unix > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(s + " " + unix);
        }
        return create((int) unix);
    }

}
