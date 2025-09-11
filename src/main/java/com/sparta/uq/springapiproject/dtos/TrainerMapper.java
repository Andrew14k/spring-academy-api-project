package com.sparta.uq.springapiproject.dtos;

import com.sparta.uq.springapiproject.entities.Trainer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
    TrainerDTO toDTO(Trainer trainer);
    Trainer toEntity(TrainerDTO dto);
}
