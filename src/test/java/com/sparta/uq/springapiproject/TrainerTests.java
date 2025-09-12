package com.sparta.uq.springapiproject;

import com.sparta.uq.springapiproject.dtos.CourseDTO;
import com.sparta.uq.springapiproject.dtos.TraineeDTO;
import com.sparta.uq.springapiproject.dtos.TrainerDTO;
import com.sparta.uq.springapiproject.dtos.TrainerMapper;
import com.sparta.uq.springapiproject.entities.Course;
import com.sparta.uq.springapiproject.entities.Trainee;
import com.sparta.uq.springapiproject.entities.Trainer;
import com.sparta.uq.springapiproject.repositories.TrainerRepository;
import com.sparta.uq.springapiproject.services.TrainerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class TrainerTests {

    private static final TrainerMapper trainerMapper = Mockito.mock(TrainerMapper.class);
    private final TrainerRepository mockRepository = Mockito.mock(TrainerRepository.class);
    private TrainerService sut = new TrainerService(mockRepository,trainerMapper);


    private static List<Trainer> trainers = new ArrayList<>();
    private static Trainer trainer1 = new Trainer();
    private static Trainer trainer2 = new Trainer();
    private static Trainer nullTrainer = null;
    private static TrainerDTO trainerDTO1 = new TrainerDTO();
    private static List<Course> courses = new ArrayList<>();
    private static Course course = new Course();
    private static List<CourseDTO> coursesDTO = new ArrayList<>();
    private static CourseDTO courseDTO = new CourseDTO();

    @BeforeAll
    public static void setup(){
        List<Trainee> dummyTrainees = new  ArrayList<>();
        List<TraineeDTO> dummyTraineesDTO = new  ArrayList<>();
        course.setTrainees(dummyTrainees);
        courses.add(course);
        courseDTO.setTrainees(dummyTraineesDTO);
        coursesDTO.add(courseDTO);

        trainer1.setFullName("Michael");
        trainer1.setCourses(courses);
        trainers.add(trainer1);

        trainer2.setFullName("Priya");
        trainer2.setCourses(courses);
        trainers.add(trainer2);

        trainerDTO1.setFullName("David");
        trainerDTO1.setCourses(coursesDTO);

    }

    @Test
    @DisplayName("Ensure TrainerService is constructed correctly")
    public void constructServiceTest(){
        Assertions.assertInstanceOf(TrainerService.class, sut);
    }

    }
