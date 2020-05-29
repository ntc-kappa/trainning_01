package com.tas.excel;

import org.apache.poi.ss.usermodel.CellType;

import java.lang.reflect.Field;

public class Header {
//    public  static int rowStart;
    private int id;
    private String name;
    private CellTypeWrapper type;
    private String value;
    private Field field;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Header(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CellTypeWrapper getType() {
        return type;
    }

    public void setType(CellTypeWrapper type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
