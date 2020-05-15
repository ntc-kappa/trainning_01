package com.tas.dto;


public class CustomCell {
    private int col;
    private String title;
    private String type;

    public CustomCell() {

    }

    public CustomCell(int col, String title, String type) {
        this.col = col;
        this.title = title;
        this.type = type;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "col=" + col +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
