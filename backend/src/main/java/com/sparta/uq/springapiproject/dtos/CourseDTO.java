package com.sparta.uq.springapiproject.dtos;

import com.sparta.uq.springapiproject.entities.Trainee;

import java.util.List;

public class CourseDTO {
    private Integer id;
    private String title;
    private List<TraineeDTO> trainees;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TraineeDTO> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<TraineeDTO> trainees) {
        this.trainees = trainees;
    }
}
