package enums;


public enum Frequency {
    DEMO_10_SEC(10),
    HOURLY(3600),
    DAILY(86400),
    WEEKLY(604800);

    private final int seconds;

    Frequency(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
