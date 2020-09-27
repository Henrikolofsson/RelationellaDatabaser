package Entities;

public class Concerts {
    private String concert_id;
    private String day;
    private String time;
    private String scene;
    private String band_name;

    public Concerts(String concert_id, String day, String time, String scene){
        this.concert_id = concert_id;
        this.day = day;
        this.time = time;
        this.scene = scene;
    }

    public String getBand_id() {
        return concert_id;
    }

    public void setBand_id(String band_id) {
        this.concert_id = band_id;
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

    public String getBand_name() {
        return band_name;
    }

    public void setBand_name(String band_name) {
        this.band_name = band_name;
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
