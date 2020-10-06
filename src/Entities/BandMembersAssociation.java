package Entities;

public class BandMembersAssociation {
    private int band_id;
    private int band_member_id;

    public BandMembersAssociation(int band_id, int band_member_id) {
        this.band_id = band_id;
        this.band_member_id = band_member_id;
    }

    public int getBand_id() {
        return band_id;
    }

    public void setBand_id(int band_id) {
        this.band_id = band_id;
    }

    public int getBand_member_id() {
        return band_member_id;
    }

    public void setBand_member_id(int band_member_id) {
        this.band_member_id = band_member_id;
    }


}

