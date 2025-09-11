package com.sparta.uq.springapiproject.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;


    public Trainee(String fullName) {
        this.fullName = fullName;

    }

    public Trainee() {

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



    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}