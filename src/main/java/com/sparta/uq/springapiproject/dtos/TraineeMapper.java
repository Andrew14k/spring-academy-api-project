package com.sparta.uq.springapiproject.dtos;

import com.sparta.uq.springapiproject.entities.Trainee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TraineeMapper {
    TraineeDTO toDTO(Trainee trainee);
    Trainee toEntity(TraineeDTO traineeDTO);
}
