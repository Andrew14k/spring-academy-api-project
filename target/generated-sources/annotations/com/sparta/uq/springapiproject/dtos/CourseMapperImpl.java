package com.sparta.uq.springapiproject.dtos;

import com.sparta.uq.springapiproject.entities.Course;
import com.sparta.uq.springapiproject.entities.Trainee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-12T10:37:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTO toDTO(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setTitle( course.getTitle() );
        courseDTO.setId( course.getId() );
        courseDTO.setTrainees( traineeListToTraineeDTOList( course.getTrainees() ) );

        return courseDTO;
    }

    @Override
    public Course toEntity(CourseDTO courseDTO) {
        if ( courseDTO == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( courseDTO.getId() );
        course.setTitle( courseDTO.getTitle() );
        course.setTrainees( traineeDTOListToTraineeList( courseDTO.getTrainees() ) );

        return course;
    }

    protected TraineeDTO traineeToTraineeDTO(Trainee trainee) {
        if ( trainee == null ) {
            return null;
        }

        TraineeDTO traineeDTO = new TraineeDTO();

        traineeDTO.setFullName( trainee.getFullName() );
        traineeDTO.setId( trainee.getId() );
        traineeDTO.setCourse_id( trainee.getCourse_id() );

        return traineeDTO;
    }

    protected List<TraineeDTO> traineeListToTraineeDTOList(List<Trainee> list) {
        if ( list == null ) {
            return null;
        }

        List<TraineeDTO> list1 = new ArrayList<TraineeDTO>( list.size() );
        for ( Trainee trainee : list ) {
            list1.add( traineeToTraineeDTO( trainee ) );
        }

        return list1;
    }

    protected Trainee traineeDTOToTrainee(TraineeDTO traineeDTO) {
        if ( traineeDTO == null ) {
            return null;
        }

        Trainee trainee = new Trainee();

        trainee.setId( traineeDTO.getId() );
        trainee.setFullName( traineeDTO.getFullName() );
        trainee.setCourse_id( traineeDTO.getCourse_id() );

        return trainee;
    }

    protected List<Trainee> traineeDTOListToTraineeList(List<TraineeDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Trainee> list1 = new ArrayList<Trainee>( list.size() );
        for ( TraineeDTO traineeDTO : list ) {
            list1.add( traineeDTOToTrainee( traineeDTO ) );
        }

        return list1;
    }
}
