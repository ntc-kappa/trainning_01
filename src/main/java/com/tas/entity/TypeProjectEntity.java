package com.tas.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "type_project")
public class TypeProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "typeProjectEntity")
    private Set<ProjectEntity> projectEntities;

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

    public Set<ProjectEntity> getProjectEntities() {
        return projectEntities;
    }

    public void setProjectEntities(Set<ProjectEntity> projectEntities) {
        this.projectEntities = projectEntities;
    }
}
