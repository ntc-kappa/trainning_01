package com.tas.dto;

import com.tas.validator.annotation.CodeDeviceValid;
import com.tas.validator.annotation.ColumnExcel;
import org.hibernate.validator.constraints.NotBlank;

public class DeviceDto {

    private Integer id;

    @ColumnExcel(title = "Mã thiết bị", col = 0, type = CustomCellType._STRING)
    @NotBlank(message = "{manager.device.notify.code.required}")
    @CodeDeviceValid
    private String code;

    @ColumnExcel(title = "Tên thiết bị", col = 1, type = CustomCellType._STRING)
    @NotBlank(message = "{manager.device.notify.name.required}")
    private String name;

    @ColumnExcel(title = "Giá thiết bị", col = 2, type = CustomCellType._DOUBLE)
    private double price;

    private String description;

    private String nameCategory;

    @ColumnExcel(title = "Mã loại thiết bị", col = 3, type = CustomCellType._STRING)
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
