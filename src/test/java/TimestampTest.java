import impl.AdvancedTimestamp;
import impl.UnixTimestamp;
import interfaces.IUnixTimestamp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.TimestampHelper;
import util.TimestampStringParser;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class TimestampTest {

    private final long STEP = 1000000;
    private final int[] days = new int[]{Integer.MIN_VALUE, -1000000000, -50000, -10000, -2, -1, 0, 1, 2, 10000, 50000, 1000000000, Integer.MAX_VALUE};

    @Test
    void strings() {
        assertArrayEquals(new Integer[]{12, 2, 124, 12, 433}, TimestampStringParser.stringToInts(" 12,2 124 --$12d433"));
        assertArrayEquals(new Integer[]{12, 2, 124, 12, 433}, TimestampStringParser.stringToInts("12,2 124 --$12d433 "));
        assertArrayEquals(new Integer[]{}, TimestampStringParser.stringToInts(""));
        assertArrayEquals(new Integer[]{}, TimestampStringParser.stringToInts(" "));
        assertArrayEquals(new Integer[]{123, 4}, TimestampStringParser.stringToInts(" 123 4"));

        assertThrows(IllegalArgumentException.class, () -> TimestampHelper.timestampWithDate(new Integer[]{1, 12, 1959}));
        assertThrows(IllegalArgumentException.class, () -> TimestampHelper.timestampWithDate(new Integer[]{29, 2, 1962}));
        assertThrows(IllegalArgumentException.class, () -> TimestampHelper.timestampWithDate(new Integer[]{-5, 5, 1962}));
        assertThrows(IllegalArgumentException.class, () -> TimestampHelper.timestampWithDate(new Integer[]{5, 5, 2062}));
        assertThrows(IllegalArgumentException.class, () -> TimestampHelper.timestampWithDate(new Integer[]{5, 13, 1962}));
        assertThrows(IllegalArgumentException.class, () -> TimestampHelper.timestampWithDate(new Integer[]{5, -1, 1962}));

        assertThrows(IllegalArgumentException.class, () -> TimestampStringParser.isFormatWithDateAndTime("01x12x1963"));
        assertThrows(IllegalArgumentException.class, () -> TimestampStringParser.isFormatWithDateAndTime(" 01x12x1963"));
        assertThrows(IllegalArgumentException.class, () -> TimestampStringParser.isFormatWithDateAndTime("01x12x1963 8:12:22"));
        assertThrows(IllegalArgumentException.class, () -> TimestampStringParser.isFormatWithDateAndTime("01.12.1963 8:12:22"));
    }


    @Test
    void unix() {

        // creation by int
        for (long i = - 100 * STEP; i < Integer.MAX_VALUE + 100 * STEP; i += STEP) {
            long finalI = i;
            try {
                final RightAdvancedTimestamp r = (RightAdvancedTimestamp) RightAdvancedTimestamp.create((int)i);
                final String f1 = r.get().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
                final String f2 = r.get().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                final RightAdvancedTimestamp r2 = (RightAdvancedTimestamp) RightAdvancedTimestamp.create(f2);

                assertDoesNotThrow(() -> {
                    var a = UnixTimestamp.create((int) finalI);
                    assertEquals(a.getSecondsFrom1970(), r.getSecondsFrom1970());
                });

                assertEquals(UnixTimestamp.create(f1).getSecondsFrom1970(), r.getSecondsFrom1970());
                assertEquals(UnixTimestamp.create(f2).getSecondsFrom1970(), r2.getSecondsFrom1970());

                checkDays(UnixTimestamp.create((int) finalI), r);
                checkHours(UnixTimestamp.create((int) finalI), r);

            } catch (IllegalArgumentException e) {
                assertThrows(IllegalArgumentException.class, () -> {
                    var a = UnixTimestamp.create((int) finalI);
                }, "Не брошено исключение в конструкторе временной метки для " + finalI);
            } catch (Exception e) {
                Assertions.fail("Сообщите мне об этом случае: " + e.getMessage());
            }

        }

        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create("01.01.189"));
        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create("01.01.2000 12:2:333"));
        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create(" 01X01X1990 12:2O333"));
        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create("01X01X1990 12:2O333"));
        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create("01X01X1990 12:2O!33"));

        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create("01X13.1990 12:2O:33"));
        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create("00X00X1990 12:2O:33"));
        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create("01X0X2068 12:2O:33"));
        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create("01X0X1968 12:2O:33"));
        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create("01 48 1999 12 11"));
        assertThrows(IllegalArgumentException.class, () -> UnixTimestamp.create("01481999"));

    }

    void checkDays(final IUnixTimestamp a, final RightAdvancedTimestamp r) {
        for (int _i = 0; _i < days.length; _i++) {
            int i = _i;
            try {
                final var newDate = r.addDays(days[i]);
                assertDoesNotThrow(() -> {
                    final var _newDate = a.addDays(days[i]);
                    assertEquals(newDate.getSecondsFrom1970(), _newDate.getSecondsFrom1970());
                });
            } catch (IllegalArgumentException e) {
                assertThrows(IllegalArgumentException.class, () -> {
                    a.addDays(days[i]);
                }, "Не брошено исключение при добавлении " + days[i] + " дней для " + r.getSecondsFrom1970());
            } catch (Exception e) {
                Assertions.fail("Сообщите мне об этом случае: " + e.getMessage());
            }
        }
    }

    void checkHours(final IUnixTimestamp a, final RightAdvancedTimestamp r) {
        for (int _i = 0; _i < days.length; _i++) {
            int i = _i;
            try {
                final var newDate = r.addHours(days[i]);
                assertDoesNotThrow(() -> {
                    final var _newDate = a.addHours(days[i]);
                });
                final var _newDate = a.addHours(days[i]);
                assertEquals(newDate.getSecondsFrom1970(), _newDate.getSecondsFrom1970());
            } catch (IllegalArgumentException e) {
                assertThrows(IllegalArgumentException.class, () -> {
                    a.addHours(days[i]);
                }, "Не брошено исключение при добавлении " + days[i]  + " часов для " + r.getSecondsFrom1970());
            } catch (Exception e) {
                Assertions.fail("Сообщите мне об этом случае: " + e.getMessage());
            }
        }
    }


    @Test
    void advanced() {

        for (long i = - 100 * STEP; i < Integer.MAX_VALUE + 100 * STEP; i += STEP) {
            long finalI = i;
            try {
                final RightAdvancedTimestamp r = (RightAdvancedTimestamp) RightAdvancedTimestamp.create((int)i);

                assertDoesNotThrow(() -> {
                    var a = AdvancedTimestamp.create((int) finalI);
                });

                var a = AdvancedTimestamp.create((int) finalI);
                assertEquals(r.getSecondsFrom1970(), a.getSecondsFrom1970());
                assertEquals(r.getWeekday(), a.getWeekday());
                assertEquals(r.getYear(), a.getYear());
                assertEquals(r.getMonth(), a.getMonth());
                assertEquals(r.getDay(), a.getDay());
                assertEquals(r.getHour(), a.getHour());
                assertEquals(r.getMinutes(), a.getMinutes());
                assertEquals(r.getSeconds(), a.getSeconds());

            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
                Assertions.fail("Сообщите мне об этом случае: " + e.getMessage());
            }

        }

    }


}
