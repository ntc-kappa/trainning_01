package com.tas.dto;

import com.tas.validator.annotation.DateFormat;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

public class ProjectDto {
    private Integer id;

    @NotBlank(message = "{manager.project.notify.title}")
    private String title;

    @DateFormat(message = "{manager.project.notify.dateformat}")
    private String checkin;

    @DateFormat(message = "{manager.project.notify.dateformat}")
    private String checkout;

    @Min(value = 0, message = "{manager.project.notify.status.min}")
    @Max(value = 100, message = "{manager.project.notify.status.max}")
    private int status;

    private String description;

    private Timestamp createTime;
    private Timestamp modifyTime;
    private String createBy;
    private String modifyBy;

    private String typeProjectName;

    @NotBlank(message = "{manager.project.notify.typeprojectcode}")
    private String typeProjectCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getTypeProjectName() {
        return typeProjectName;
    }

    public void setTypeProjectName(String typeProjectName) {
        this.typeProjectName = typeProjectName;
    }

    public String getTypeProjectCode() {
        return typeProjectCode;
    }

    public void setTypeProjectCode(String typeProjectCode) {
        this.typeProjectCode = typeProjectCode;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", createBy='" + createBy + '\'' +
                ", modifyBy='" + modifyBy + '\'' +
                ", typeProjectName='" + typeProjectName + '\'' +
                ", typeProjectCode='" + typeProjectCode + '\'' +
                '}';
    }
}
