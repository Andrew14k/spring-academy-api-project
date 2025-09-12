package com.sparta.uq.springapiproject.dtos;

import java.util.List;

public class TrainerDTO {
    private Integer id;
    private String fullName;
    private Integer course;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }
}
