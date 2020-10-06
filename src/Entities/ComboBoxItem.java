package Entities;

public class ComboBoxItem {
    private int dbId;
    private String text;

    public ComboBoxItem(int dbId, String text) {
        this.dbId = dbId;
        this.text = text;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
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
        return "ComboBoxItem{" +
                "dbId=" + dbId +
                ", text='" + text + '\'' +
                '}';
    }
}
