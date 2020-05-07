package com.tas.dto;

import org.hibernate.validator.constraints.NotBlank;

public class DeviceDto {

    private Integer id;

    @NotBlank(message = "{manager.device.notify.code.required}")
    private String code;

    @NotBlank(message = "{manager.device.notify.name.required}")
    private String name;

    private double price;

    private String description;

    private String nameCategory;

    @NotBlank(message = "{manager.device.notify.codecategory.required}")
    private String codeCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getCodeCategory() {
        return codeCategory;
    }

    public void setCodeCategory(String codeCategory) {
        this.codeCategory = codeCategory;
    }
}
