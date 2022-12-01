package util;

public class TimestampStringParser {

    /**
     * Превращает цифровой символ в число
     * <p>Например,
     * <blockquote><pre>
     *     int d = charToInt('0');
     *     assert(d == 0);
     * </pre></blockquote></p>
     *
     * @param c символ в диапазоне от '0' до '9'
     * @return число от 0 до 9, соответствующее символу-цифре
     * @throws IllegalArgumentException если символ не является цифровым
     */
    private static int charToInt(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        throw new IllegalArgumentException("Символ '" + c + "' не является цифрой.");
    }

    /**
     * Превращает строку, состоящую из натурального (вкл. 0) десятичного числа, в число
     * <p>Например,
     * <blockquote><pre>
     *     int d = stringToInt("1234");
     *     assert(d == 1234);
     * </pre></blockquote></p>
     *
     * @param s натуральное десятичное число, записанное в строке, или "0"
     * @return целое число, соответствующее строке
     * @throws IllegalArgumentException если в строке содержатся нецифровые символы, пустая строка или число превышает int
     */
    private static int stringToInt(String s) {
        // TODO: написать свой метод, использующий charToInt()
        return 0;
    }

    /**
     * Разбивает строку, состоящую из целочисленных строк, в массив
     * <p>Например,
     * <blockquote><pre>
     *     int[] d = stringToInts("1234 , 1:31 -,12 1-");
     *     d соответствует new int[]{1234, 1, 31, 12, 1}
     * </pre></blockquote>
     *
     * Например, "21.11.2021 16:24:52" => [21, 11, 2021, 16, 24, 52]
     *
     * </p>
     *
     * @param s целочисленная десятичная строка
     * @return целое число, соответствующее строке
     */
    public static Integer[] stringToInts(String s) {
        // TODO: написать свой метод, использующий stringToInt()
        // Подсказка 1: задача аналогична поиску слов в предложений,
        // только там разделитель был пробел, а здесь - другой символ
        // Подсказка 2: добавьте к строке пустой символ, чтобы не забыть учесть последнее число
        int N = 0; // количество чисел в строке зависит от количества разделителей в строке
        // --> найти количество чисел
        Integer[] numbers = new Integer[N];
        int lastNumberIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            // --> бросить исключение или узнать индексы очередного числа
            int firstIndex = 0;
            int lastIndex = s.length() - 1;
            String number = s.substring(firstIndex, lastNumberIndex);
            assert(lastNumberIndex < N);
            numbers[lastNumberIndex++] = stringToInt(number);
        }
        return numbers;
    }

    /**
     * В данном задании рассматриваются временные метки UTC (в часовом поясе +00:00),
     * а также два строковых формата записи строк:
     * <p>
     *     1. DD.MM.YYYY <br>
     *     2. DD.MM.YYYY HH:MM:SS
     * </p>
     * Корректные примеры:
     * <p>
     *     1. 01.12.2022 <br>
     *     2. 01.12.2022 16:39:23
     * </p>
     * Некорректные примеры:
     * <p>
     *     1. 1.12.2022 <br>
     *     2. 01.12.2022 16:9:23
     * </p>
     *
     * @param s строка
     * @return true, если строка соответствует формату №2, false, если соответствует формату №1
     * @throws IllegalArgumentException
     */
    public static boolean isFormatWithDateAndTime(String s) {
        // TODO: написать свой метод
        throw new IllegalArgumentException("Строка не соответствует форматам");
    }


}
