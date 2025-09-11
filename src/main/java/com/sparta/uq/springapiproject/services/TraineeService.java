package com.sparta.uq.springapiproject.services;

import com.sparta.uq.springapiproject.dtos.TraineeDTO;
import com.sparta.uq.springapiproject.dtos.TraineeMapper;
import com.sparta.uq.springapiproject.entities.Trainee;
import com.sparta.uq.springapiproject.repositories.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;
    private final TraineeMapper traineeMapper;

    public TraineeService(TraineeRepository traineeRepository, TraineeMapper traineeMapper) {
        if (traineeRepository == null) {
            throw new IllegalArgumentException("traineeRepository cannot be null");
        }
        this.traineeRepository = traineeRepository;
        this.traineeMapper = traineeMapper;
    }

    public List<TraineeDTO> getAllTrainees() {
        return traineeRepository.findAll().stream().map(traineeMapper::toDTO).toList();
    }

    public TraineeDTO getTraineeById(Integer id) {
        Trainee trainee = traineeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Trainee with id " + id + " not found"));
        return traineeMapper.toDTO(trainee);
    }

    public TraineeDTO addTrainee(TraineeDTO traineeDTO) {
        traineeDTO.setId(null);
        Trainee trainee = traineeMapper.toEntity(traineeDTO);
        Trainee newTrainee = traineeRepository.save(trainee);
        return traineeMapper.toDTO(newTrainee);
    }

    public TraineeDTO updateTrainee(TraineeDTO traineeDTO) {
        Trainee trainee = traineeRepository.findById(traineeDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Trainee with id " + traineeDTO.getId() + " not found"));
        trainee.setFullName(traineeDTO.getFullName());
        trainee.setCourse_id(traineeDTO.getCourse_id());
        Trainee updatedTrainee = traineeRepository.save(trainee);
        return  traineeMapper.toDTO(updatedTrainee);
    }

    public boolean deleteTrainee(Integer id) {
        if (traineeRepository.existsById(id)) {
            traineeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}