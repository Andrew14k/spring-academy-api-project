package com.sparta.uq.springapiproject.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer trainee_id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @JoinColumn(name = "course_id", nullable = false)
    private Integer course_id;

    public Trainee(String fullName_, Integer course_id_, Integer, trainee_id_)
    {
        this.fullName = fullName_;
        this.course_id = course_id_;
        this.trainee_id = trainee_id_;
    }

    public Trainee(String fullName) {
        this.fullName = fullName;

    }

    public Trainee() {

    }

    public Integer getId() {
        return trainee_id;
    }

    public void setId(Integer id) {
        this.trainee_id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + trainee_id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
