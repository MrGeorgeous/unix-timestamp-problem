package util;

public class TimestampHelper {

    private static final int[] leapDays = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] notLeapDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * Определяет, является ли год високосным
     * <p>Например,
     * <blockquote><pre>
     *     assert(isLeap(2020));
     *     assert(!isLeap(2021));
     * </pre></blockquote></p>
     *
     * @param year номер года
     * @return true, если год был високосный; false, если невисокосный
     */
    public static boolean isLeap(int year) {
        return ((year % 4 == 0) & (year % 100 != 0)) | (year % 400 == 0);
    }

    /**
     * Возвращает количество дней в месяцах года по его номеру
     * <p>Например,
     * <blockquote><pre>
     *     assert(isLeap(2020));
     *     assert(!isLeap(2021));
     * </pre></blockquote></p>
     *
     * @param year номер года
     * @return int[12] с количеством дней от января до декабря в году N
     */
    public static int[] daysInMonths(int year) {
        if (isLeap(year)) {
            return leapDays;
        } else {
            return notLeapDays;
        }
    }

    /**
     * Возвращает количество секунд, прошедших с начала эпохи Unix
     * @param d массив чисел [day, month, year]
     * @return int с количеством секунд, прошедших с начала эпохи Unix
     * @throws IllegalArgumentException если массив содержит некорректные данные или время выходит за рамки Unix
     */
    public static int timestampWithDate(Integer[] d) {
        // TODO: реализовать метод
        // Подсказка: просуммируйте секунды, за прошедшее количество полных лет
        // затем за количество полных месяцев (учитывайте високосность с помощью daysInMonths(int year))
        // затем за количество полных дней и т.д.

        // Совет: считайте секунды long, а затем проверьте, не вышли ли вы за время Unix
        return 0;
    }

    /**
     * Возвращает количество секунд, прошедших с начала эпохи Unix
     * @param d массив чисел [day, month, year, hours, minutes, seconds]
     * @return int с количеством секунд, прошедших с начала эпохи Unix
     * @throws IllegalArgumentException если массив содержит некорректные данные или время выходит за рамки Unix
     */
    public static int timestampWithDateAndTime(Integer[] d) {
        // TODO: реализовать метод, используя timestampWithDate()
        // Подсказка: просуммируйте секунды, за прошедшее количество полных лет
        // затем за количество полных месяцев (учитывайте високосность с помощью daysInMonths(int year))
        // затем за количество полных дней и т.д.

        // Совет: считайте секунды long, а затем проверьте, не вышли ли вы за время Unix
        return 0;
    }

}
