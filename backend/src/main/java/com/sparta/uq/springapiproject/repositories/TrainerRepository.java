package com.sparta.uq.springapiproject.repositories;

import com.sparta.uq.springapiproject.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
}