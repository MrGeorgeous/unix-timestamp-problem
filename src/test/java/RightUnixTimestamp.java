import impl.AdvancedTimestamp;
import impl.UnixTimestamp;
import interfaces.IUnixTimestamp;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class RightUnixTimestamp implements IUnixTimestamp {

    protected ZoneId utc = ZoneId.of("Etc/UTC");
    protected int s;

    @Override
    public int getSecondsFrom1970() {
        return s;
    }

    @Override
    public IUnixTimestamp addDays(int n) {
        Instant i = Instant.ofEpochSecond(s);
        ZonedDateTime z = ZonedDateTime.ofInstant(i, utc);
        long r = s;
        if (n > 0) {
            r = z.plusDays(n).toEpochSecond();
        } else if (n < 0) {
            r = z.minusDays(-n).toEpochSecond();
        }
        if (r < 0) {
            throw new IllegalArgumentException();
        }
        if (r > Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        return create((int) r);
    }

    @Override
    public IUnixTimestamp addHours(int n) {
        Instant i = Instant.ofEpochSecond(s);
        ZonedDateTime z = ZonedDateTime.ofInstant(i, utc);
        long r = s;
        if (n > 0) {
            r = z.plusHours(n).toEpochSecond();
        } else if (n < 0) {
            r = z.minusHours(-n).toEpochSecond();
        }
        if (r < 0) {
            throw new IllegalArgumentException();
        }
        if (r > Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        return create((int) r);
    }

    public static IUnixTimestamp create(int secondsSince1970) {
        return RightAdvancedTimestamp.create(secondsSince1970);
    }

    public static IUnixTimestamp create(String s) {
        return RightAdvancedTimestamp.create(s);
    }

}
