package com.sparta.uq.springapiproject.entities;



import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "full_name", length = 40)
    private String fullName;

    @Column(name = "course", nullable = false)
    private Integer course;

    public Trainer(String fullName, Integer course) {
        this.fullName = fullName;
        this.course = course;
    }

    public Trainer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
