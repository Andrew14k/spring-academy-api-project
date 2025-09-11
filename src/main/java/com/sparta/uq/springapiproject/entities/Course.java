package com.sparta.uq.springapiproject.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "course")
public class Course{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer course_id;

    @Column(name = "Title", nullable = false)
    private String title;

    @OneToMany(
            mappedBy = "trainee_id",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private List<Trainee> trainees;

    public Course(String title, List<Trainee> trainees) {
        this.title = title;
        this.trainees = trainees;
    }

    public Course() {

    }

    public Integer getId() {
        return course_id;
    }

    public void setId(Integer id) {
        this.course_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Trainee> getTrainee() {
        return trainees;
    }

    public void setTrainee(List<Trainee> trainee) {
        this.trainees = trainee;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + course_id +
                ", title='" + title + '\'' +
                ", trainee=" + trainees +
                '}';
    }
}