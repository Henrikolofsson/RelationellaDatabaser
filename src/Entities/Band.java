package Entities;

public class Band {
    private String band_id;
    private String band_name;
    private String band_country_of_origin;
    private String band_info;
    private String contact_person_id;

    public Band(String band_id, String band_name, String band_country_of_origin, String band_info, String contact_person_id) {
        this.band_id = band_id;
        this.band_name = band_name;
        this.band_country_of_origin = band_country_of_origin;
        this.band_info = band_info;
        this.contact_person_id = contact_person_id;
    }

    public String getBand_name() {
        return band_name;
    }

    public String getBand_info() {
        return band_info;
    }

    public String getContact_person_id() {
        return contact_person_id;
    }

    public String getBand_country_of_origin() {
        return band_country_of_origin;
    }

    public String getBand_id() {
        return band_id;
    }

    public void setBand_id(String band_id) {
        this.band_id = band_id;
    }

    @Override
    public String toString() {
        return "Band{" +
                "band_id='" + band_id + '\'' +
                ", band_name='" + band_name + '\'' +
                ", band_country_of_origin='" + band_country_of_origin + '\'' +
                ", band_info='" + band_info + '\'' +
                ", contact_person_id='" + contact_person_id + '\'' +
                '}';
    }
}
