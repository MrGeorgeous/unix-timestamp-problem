package impl;

import interfaces.IUnixTimestamp;
import util.TimestampHelper;
import util.TimestampStringParser;

/**
 * Класс, хранящий временную метку в часовом поясе UTC+00:00
 */
public class UnixTimestamp implements IUnixTimestamp {

    /**
     * Количество секунд, прошедших с 01.01.1970 00:00:00 UTC
     */
    protected final int secondsSince1970;

    /**
     * Пустой конструктор создает текущую временную метку
     */
    public UnixTimestamp() {
        this((int) (System.currentTimeMillis() / 1000));
    }

    /**
     * Конструктор через количество секунд UNIX
     * @param secondsSince1970 количество секунд, прошедших с 01.01.1970 00:00:00 UTC
     * @throws IllegalArgumentException если количество секунд отрицательное
     */
    public UnixTimestamp(int secondsSince1970) {
        if (secondsSince1970 < 0) {
            throw new IllegalArgumentException();
        }
        this.secondsSince1970 = secondsSince1970;
    }

    /**
     * Конструктор для создания временной метки из строки
     * DD.MM.YYYY или DD.MM.YYYY HH:MM:SS
     * @param str строка формата DD.MM.YYYY или DD.MM.YYYY HH:MM:SS
     * @throws IllegalArgumentException если строка не соответствует формату или выходит за рамки Unix timestamp
     */
    public UnixTimestamp(String str) {
        boolean hasDateAndTime = TimestampStringParser.isFormatWithDateAndTime(str);
        Integer[] numericComponents = TimestampStringParser.stringToInts(str);
        if (hasDateAndTime) {
            secondsSince1970 = TimestampHelper.timestampWithDateAndTime(numericComponents);
        } else {
            secondsSince1970 = TimestampHelper.timestampWithDate(numericComponents);
        }
    }

    public int getSecondsFrom1970() {
        return secondsSince1970;
    }

    /**
     * Создает новую временную метку с большим или меньшим количеством дней
     * @param n - положительное или отрицательное количество дней
     * @return новая временная метка
     * @throws IllegalArgumentException если новая временная метка вышла за пределы эпохи Unix
     */
    public UnixTimestamp addDays(int n) {
        // TODO:
        return new UnixTimestamp();
    }

    /**
     * Создает новую временную метку с большим или меньшим количеством часов
     * @param n - положительное или отрицательное количество часов
     * @return новая временная метка
     * @throws IllegalArgumentException если новая временная метка вышла за пределы эпохи Unix
     */
    public UnixTimestamp addHours(int n) {
        // TODO:
        return new UnixTimestamp();
    }

    public static IUnixTimestamp create(int secondsSince1970) { return new UnixTimestamp(secondsSince1970); }
    public static IUnixTimestamp create(String s) { return new UnixTimestamp(s); }

}
