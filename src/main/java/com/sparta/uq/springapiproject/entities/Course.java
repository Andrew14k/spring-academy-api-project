package com.sparta.uq.springapiproject.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "Title", nullable = false)
    private String title;

    @OneToMany(
            mappedBy = "trainees",
            cascade = CascadeType.ALL,
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

    public List<Trainee> getTrainee() {
        return trainees;
    }

    public void setTrainee(List<Trainee> trainee) {
        this.trainees = trainee;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", trainee=" + trainees +
                '}';
    }
}