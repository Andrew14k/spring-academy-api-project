package com.sparta.uq.springapiproject.dtos;

import com.sparta.uq.springapiproject.entities.Trainee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-12T10:37:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class TraineeMapperImpl implements TraineeMapper {

    @Override
    public TraineeDTO toDTO(Trainee trainee) {
        if ( trainee == null ) {
            return null;
        }

        TraineeDTO traineeDTO = new TraineeDTO();

        traineeDTO.setFullName( trainee.getFullName() );
        traineeDTO.setId( trainee.getId() );
        traineeDTO.setCourse_id( trainee.getCourse_id() );

        return traineeDTO;
    }

    @Override
    public Trainee toEntity(TraineeDTO traineeDTO) {
        if ( traineeDTO == null ) {
            return null;
        }

        Trainee trainee = new Trainee();

        trainee.setId( traineeDTO.getId() );
        trainee.setFullName( traineeDTO.getFullName() );
        trainee.setCourse_id( traineeDTO.getCourse_id() );

        return trainee;
    }
}
