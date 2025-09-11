package com.sparta.uq.springapiproject.services;

import com.sparta.uq.springapiproject.dtos.TraineeDTO;
import com.sparta.uq.springapiproject.dtos.TrainerDTO;
import com.sparta.uq.springapiproject.dtos.TrainerMapper;
import com.sparta.uq.springapiproject.entities.Trainer;
import com.sparta.uq.springapiproject.repositories.TrainerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    public TrainerService(TrainerRepository trainerRepository, TrainerMapper trainerMapper) {
        if (trainerRepository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.trainerRepository = trainerRepository;
        this.trainerMapper =  trainerMapper;
    }

    public List<TrainerDTO> getAllTrainers() {
        return trainerRepository.findAll().stream().map(trainerMapper::toDTO).toList();
    }

    public TrainerDTO getTrainerByID(Integer id) {
        return trainerMapper.toDTO(trainerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Trainer not found")));
    }

    public TrainerDTO saveTrainer(TrainerDTO trainer) {
        if (trainer == null) {
            throw new IllegalArgumentException("Trainer cannot be null");
        }
        return trainerMapper.toDTO(trainerRepository.save(trainerMapper.toEntity(trainer)));
    }

    public boolean deleteTrainer(Integer id) {
        if (trainerRepository.existsById(id)) {
            trainerRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public TrainerDTO updateTrainer(TrainerDTO trainer) {
        if (!trainerRepository.existsById(trainer.getId())) {
            throw new IllegalArgumentException("Trainer does not exist");
        }
        return trainerMapper.toDTO(trainerRepository.save(trainerMapper.toEntity(trainer)));
    }
}

