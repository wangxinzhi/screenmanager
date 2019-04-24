package com.system.pojo;

public class ScreenByFrontFormat {

    private int id;
    private String value = "";
    private String label = "";
    private boolean state = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" [ id = ");
        stringBuilder.append(id);
        stringBuilder.append(" ip = ");
        stringBuilder.append(value);
        stringBuilder.append(" location = ");
        stringBuilder.append(label);
        stringBuilder.append(" state = ");
        stringBuilder.append(state);
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
}
