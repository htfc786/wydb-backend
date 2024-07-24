package com.htfc786.wydb.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class WyCollection {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("集合名称")
    private String name;

    @ApiModelProperty("集合描述（可选）")
    private String description;

    @ApiModelProperty("文集创建时间")
    private Date createdAt;

    public WyCollection() {
    }

    public WyCollection(Integer id, String name, String description, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "WyCollection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
