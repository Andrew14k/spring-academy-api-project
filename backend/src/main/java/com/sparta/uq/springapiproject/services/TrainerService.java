package com.sparta.uq.springapiproject.services;

import com.sparta.uq.springapiproject.dtos.TraineeDTO;
import com.sparta.uq.springapiproject.dtos.TrainerDTO;
import com.sparta.uq.springapiproject.dtos.TrainerMapper;
import com.sparta.uq.springapiproject.entities.Course;
import com.sparta.uq.springapiproject.entities.Trainee;
import com.sparta.uq.springapiproject.entities.Trainer;
import com.sparta.uq.springapiproject.repositories.TrainerRepository;
import com.sparta.uq.springapiproject.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final CourseRepository courseRepository;
    private final TrainerMapper trainerMapper;

    public TrainerService(TrainerRepository trainerRepository, TrainerMapper trainerMapper, CourseRepository courseRepository) {
        if (trainerRepository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.trainerRepository = trainerRepository;
        this.trainerMapper =  trainerMapper;
        this.courseRepository = courseRepository;
    }

    public List<TrainerDTO> getAllTrainers() {
        return trainerRepository.findAll().stream().map(trainerMapper::toDTO).toList();
    }

    public TrainerDTO getTrainerByID(Integer id) {
        return trainerMapper.toDTO(trainerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Trainer not found")));
    }

    public TrainerDTO saveTrainer(TrainerDTO trainerDTO) {
        trainerDTO.setId(null);
        Trainer trainer = trainerMapper.toEntity(trainerDTO);
        Trainer newTrainer = trainerRepository.save(trainer);
        return trainerMapper.toDTO(newTrainer);
    }

    public boolean deleteTrainer(Integer id) {
        if (trainerRepository.existsById(id)) {
            trainerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public TrainerDTO updateTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = trainerRepository.findById(trainerDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Trainee with id " + trainerDTO.getId() + " not found"));
        trainer.setFullName(trainerDTO.getFullName());
        trainer.setCourse(trainerDTO.getCourse());
        Trainer updatedTrainer = trainerRepository.save(trainer);
        return  trainerMapper.toDTO(updatedTrainer);
    }
}

