package com.sparta.uq.springapiproject.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Trainer trainer;

    public Trainee(String fullName, Trainer trainer) {
        this.fullName = fullName;
        this.trainer = trainer;
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

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", trainer=" + trainer +
                '}';
    }
}