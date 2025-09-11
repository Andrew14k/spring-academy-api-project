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

    @OneToMany(
            mappedBy = "course_id",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private List<Course> courses = new ArrayList<>();

    public Trainer(String fullName, List<Course>  courses) {
        this.fullName = fullName;
        this.courses = courses;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", courses=" + courses +
                '}';
    }
}
