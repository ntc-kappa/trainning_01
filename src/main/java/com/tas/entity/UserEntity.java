package com.tas.entity;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "gender")
    private boolean gender;
    @CreatedDate
    @Column(name = "create_time")
    private Timestamp createTime;
    @LastModifiedDate
    @Column(name = "modify_time")
    private Timestamp modifyTime;
    @CreatedBy
    @Column(name = "create_by")
    private String createBy;
    @LastModifiedBy
    @Column(name = "modify_by")
    private String modifyBy;

    @Column(name = "appro_by")
    private String approBy;

    @ManyToMany(mappedBy = "userEntities")
    private Set<RoleEntity> roleEntities;

    @ManyToMany(mappedBy = "userEntities")
    private Set<DepartmentEntity> departmentEntities;

    @ManyToMany(mappedBy = "userEntities")
    private Set<PositionEntity> positionEntities;

    @OneToMany(mappedBy = "userEntity")
    private Set<UserProjectPositionEntity> userProjectPositionEntities;

    @OneToMany(mappedBy = "userEntity")
    private Set<DeviceEntity> deviceEntities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setRoleEntities(Set<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }

    public Set<DepartmentEntity> getDepartmentEntities() {
        return departmentEntities;
    }

    public void setDepartmentEntities(Set<DepartmentEntity> departmentEntities) {
        this.departmentEntities = departmentEntities;
    }

    public Set<PositionEntity> getPositionEntities() {
        return positionEntities;
    }

    public void setPositionEntities(Set<PositionEntity> positionEntities) {
        this.positionEntities = positionEntities;
    }

    public Set<UserProjectPositionEntity> getUserProjectPositionEntities() {
        return userProjectPositionEntities;
    }

    public void setUserProjectPositionEntities(Set<UserProjectPositionEntity> userProjectPositionEntities) {
        this.userProjectPositionEntities = userProjectPositionEntities;
    }

    public Set<DeviceEntity> getDeviceEntities() {
        return deviceEntities;
    }

    public void setDeviceEntities(Set<DeviceEntity> deviceEntities) {
        this.deviceEntities = deviceEntities;
    }
}
