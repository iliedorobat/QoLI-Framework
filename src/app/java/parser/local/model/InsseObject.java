package app.java.parser.local.model;

public class InsseObject {
    String description, region, value, year;

    public InsseObject(String region, String year, String value, String description) {
        this.description = description;
        this.region = region;
        this.value = value;
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
