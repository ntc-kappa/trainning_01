package com.tas.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "checkin")
    private Date checkin;

    @Column(name = "checkout")
    private Date checkout;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private int status;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "modify_time")
    private Timestamp modifyTime;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "modify_by")
    private String modifyBy;

    @ManyToOne
    @JoinColumn(name = "id_type_project", nullable = false)
    private TypeProjectEntity typeProjectEntity;

    @OneToMany(mappedBy = "projectEntity")
    private Set<UserProjectPositionEntity> userProjectPositions;

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

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public TypeProjectEntity getTypeProjectEntity() {
        return typeProjectEntity;
    }

    public void setTypeProjectEntity(TypeProjectEntity typeProjectEntity) {
        this.typeProjectEntity = typeProjectEntity;
    }

    public Set<UserProjectPositionEntity> getUserProjectPositions() {
        return userProjectPositions;
    }

    public void setUserProjectPositions(Set<UserProjectPositionEntity> userProjectPositions) {
        this.userProjectPositions = userProjectPositions;
    }
}
