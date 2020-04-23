package com.tas.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_project_position")
public class UserProjectPositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project")
    private ProjectEntity projectEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project_position")
    private ProjectPositionEntity projectPositionEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public ProjectPositionEntity getProjectPositionEntity() {
        return projectPositionEntity;
    }

    public void setProjectPositionEntity(ProjectPositionEntity projectPositionEntity) {
        this.projectPositionEntity = projectPositionEntity;
    }
}
