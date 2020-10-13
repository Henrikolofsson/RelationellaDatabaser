package Entities;

/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    An entity/model class for the items that is placed inside ComboBoxes(to keep track of the database id for each item)
 */
public class ComboBoxItem {
    private String dbId;
    private String text;

    public ComboBoxItem(String dbId, String text) {
        this.dbId = dbId;
        this.text = text;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text + "(" + dbId + ")";
    }
}
