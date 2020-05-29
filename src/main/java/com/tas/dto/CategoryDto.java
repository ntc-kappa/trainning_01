package com.tas.dto;

import com.tas.entity.CategoryEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class CategoryDto {

    private Integer id;
    @NotBlank(message = "{manager.category.notify.name.required}")
    private String name;
    @NotBlank(message = "{manager.category.notify.code.required}")
    private String code;

    private String description;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
