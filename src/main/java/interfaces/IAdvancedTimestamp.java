package interfaces;

public interface IAdvancedTimestamp extends IUnixTimestamp {
    enum Weekday {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }

    Weekday getWeekday();
    int getDay();
    int getMonth();
    int getYear();
    int getHour();
    int getMinutes();
    int getSeconds();

}
