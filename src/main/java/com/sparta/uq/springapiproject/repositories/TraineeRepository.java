package com.sparta.uq.springapiproject.repositories;


import com.sparta.uq.springapiproject.entities.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
}
