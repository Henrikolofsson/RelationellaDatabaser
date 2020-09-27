package Entities;

public class BandMember {
    private String band_member_id;
    private String band_member_name;
    private String band_member_info;

    public BandMember(String band_member_id, String band_member_name, String band_member_info){
        this.band_member_id = band_member_id;
        this.band_member_name = band_member_name;
        this.band_member_info = band_member_info;
    }

    public String getBand_member_id() {
        return band_member_id;
    }

    public void setBand_member_id(String band_member_id) {
        this.band_member_id = band_member_id;
    }

    public String getBand_member_name() {
        return band_member_name;
    }

    public void setBand_member_name(String band_member_name) {
        this.band_member_name = band_member_name;
    }

    public String getBand_member_info() {
        return band_member_info;
    }

    public void setBand_member_info(String band_member_info) {
        this.band_member_info = band_member_info;
    }

    @Override
    public String toString() {
        return "BandMember{" +
                "band_member_id='" + band_member_id + '\'' +
                ", band_member_name='" + band_member_name + '\'' +
                ", band_member_info='" + band_member_info + '\'' +
                '}';
    }
}
