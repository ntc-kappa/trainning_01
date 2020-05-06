package com.tas.dto;

import org.hibernate.validator.constraints.NotBlank;

public class DepartmentDto {
    private Integer id;
    @NotBlank(message = "Input department name!!!")
    private String name;
    @NotBlank(message = "Input loacation")
    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "DepartmentDto{ " + "id = " +id+ ", name " +name+ ", location " + location+ "\t" +"}";
    }
}
