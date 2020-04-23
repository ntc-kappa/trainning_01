package com.tas.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project_position")
public class ProjectPositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "projectPositionEntity")
    private Set<UserProjectPositionEntity> userProjectPositionEntities;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserProjectPositionEntity> getUserProjectPositionEntities() {
        return userProjectPositionEntities;
    }

    public void setUserProjectPositionEntities(Set<UserProjectPositionEntity> userProjectPositionEntities) {
        this.userProjectPositionEntities = userProjectPositionEntities;
    }
}
