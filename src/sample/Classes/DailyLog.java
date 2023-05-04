package sample.Classes;

public class DailyLog {
    private String DailyDescription;
    private double DailyPaid;
    private String DailyDate;

    public DailyLog(String dailyDescription, double dailyPaid, String dailyDate) {
        DailyDescription = dailyDescription;
        DailyPaid = dailyPaid;
        DailyDate = dailyDate;
    }

    public String getDailyDescription() {
        return DailyDescription;
    }

    public void setDailyDescription(String dailyDescription) {
        DailyDescription = dailyDescription;
    }

    public double getDailyPaid() {
        return DailyPaid;
    }

    public void setDailyPaid(int dailyPaid) {
        DailyPaid = dailyPaid;
    }

    public String getDailyDate() {
        return DailyDate;
    }

    public void setDailyDate(String dailyDate) {
        DailyDate = dailyDate;
    }
}
