package interfaces;

public interface IUnixTimestamp {
    int getSecondsFrom1970();

    /**
     * Создает новую временную метку с большим или меньшим количеством дней
     * @param n - положительное или отрицательное количество дней
     * @return новая временная метка
     * @throws IllegalArgumentException если новая временная метка вышла за пределы эпохи Unix
     */
    IUnixTimestamp addDays(int n);

    /**
     * Создает новую временную метку с большим или меньшим количеством часов
     * @param n - положительное или отрицательное количество часов
     * @return новая временная метка
     * @throws IllegalArgumentException если новая временная метка вышла за пределы эпохи Unix
     */
    IUnixTimestamp addHours(int n);

}
