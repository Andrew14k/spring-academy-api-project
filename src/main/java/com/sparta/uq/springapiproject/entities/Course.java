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
    private List<Trainee> trainee;

    public Course(String title, Trainee trainee) {
        this.title = title;
        this.trainee = trainee;
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
        return trainee;
    }

    public void setTrainee(List<Trainee> trainee) {
        this.trainee = trainee;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", trainee=" + trainee +
                '}';
    }
}