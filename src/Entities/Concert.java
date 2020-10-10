package Entities;

public class Concert {
    private String concert_id;
    private String day;
    private String time;
    private String scene;
    private int band_id;

    public Concert(String concert_id, int band_id, String day, String time, String scene){
        this.concert_id = concert_id;
        this.band_id = band_id;
        this.day = day;
        this.time = time;
        this.scene = scene;
    }

    public int getBand_id() {
        return band_id;
    }

    public void setBand_id(int band_id) {
        this.band_id = band_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Override
    public String toString() {
        return "Concerts{" +
                "band_id='" + concert_id + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", scene='" + scene + '\'' +
                '}';
    }
}
