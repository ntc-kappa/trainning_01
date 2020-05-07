package com.tas.dto;

import com.tas.validator.annotation.EmailValid;
import com.tas.validator.annotation.UsernameFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


import java.sql.Timestamp;

public class UserDto {
    private Integer id;



    @NotBlank(message = "Enter Full Name")
    private String fullName;

    @UsernameFormat
    @NotBlank(message = "Enter UserName")
    private String username;

    @NotBlank(message = "Enter PassWord")
    @Length(min = 6,message = "password more than 6 charactor")
    private String password;

    @EmailValid
    private String email;

    private String address;
    private boolean isActive;
    private String avatar;
    private String phoneNumber;
    private boolean gender;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private String createBy;
    private String modifyBy;
    private String approBy;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
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

    public String getApproBy() {
        return approBy;
    }

    public void setApproBy(String approBy) {
        this.approBy = approBy;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                ", avatar='" + avatar + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", createBy='" + createBy + '\'' +
                ", modifyBy='" + modifyBy + '\'' +
                ", approBy='" + approBy + '\'' +
                '}';
    }
}
