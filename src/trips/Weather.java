package trips;

public class Weather {
    private double maxTemp;
    private double minTemp;
    private double avgTemp;
    private String description;
    private String conditions;
    private String dateTime;

    public Weather(double maxTemp, double minTemp, double avgTemp, String description, String conditions, String dateTime) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.avgTemp = avgTemp;
        this.description = description;
        this.conditions = conditions;
        this.dateTime = dateTime;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public String getDescription() {
        return description;
    }

    public String getConditions() {
        return conditions;
    }

    public String getDateTime() {
        return dateTime;
    }
}
