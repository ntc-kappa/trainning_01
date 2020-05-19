package com.tas.excel;

import com.tas.exception.NoSuchTypeCellException;
import org.apache.poi.ss.usermodel.CellType;

public enum CellTypeWrapper  {
    _DOUBLE(0,"Double"),NUMERIC(0,"Number"), STRING(1,"String"),_NONE(-1,"None"), BOOLEAN(4,"bool"),_INTEGER(0,"Integer");
    private int code;
    private String name;

    CellTypeWrapper(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static CellTypeWrapper getCellTypeFromName(String name){
        CellTypeWrapper[]  typeWrapper=values();

        for (CellTypeWrapper wrapper: typeWrapper
             ) {
            if(wrapper.getName().equals(name)){
                return wrapper;
            }
        }
        throw new NoSuchTypeCellException();
    }
}
