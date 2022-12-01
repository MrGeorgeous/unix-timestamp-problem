import impl.AdvancedTimestamp;
import impl.UnixTimestamp;
import interfaces.IUnixTimestamp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

            } catch (IllegalArgumentException e) {
                assertThrows(IllegalArgumentException.class, () -> {
                    var a = UnixTimestamp.create((int) finalI);
                }, "Не брошено исключение в конструкторе временной метки для " + finalI);
            } catch (Exception e) {
                Assertions.fail("Сообщите мне об этом случае: " + e.getMessage());
            }

        }

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
                assertEquals(a.getSecondsFrom1970(), r.getSecondsFrom1970());
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
