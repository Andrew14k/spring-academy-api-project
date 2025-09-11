package com.sparta.uq.springapiproject;

import com.sparta.uq.springapiproject.entities.Course;
import com.sparta.uq.springapiproject.entities.Trainee;
import com.sparta.uq.springapiproject.entities.Trainer;
import com.sparta.uq.springapiproject.repositories.CourseRepository;
import com.sparta.uq.springapiproject.repositories.TraineeRepository;
import com.sparta.uq.springapiproject.repositories.TrainerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringApiProjectApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringApiProjectApplication.class, args);

        TrainerRepository trainerRepository = context.getBean(TrainerRepository.class);
        TraineeRepository traineeRepository = context.getBean(TraineeRepository.class);
        CourseRepository courseRepository = context.getBean(CourseRepository.class);

        List<Trainee> traineeList = traineeRepository.findAll();
        List<Course> courseList = courseRepository.findAll();
        List<Trainer> trainerList = trainerRepository.findAll();

        for (Trainee trainee : traineeList) {
            System.out.println(trainee);
        }
        for (Course course : courseList) {
            System.out.println(course);
        }
        for (Trainer trainer : trainerList) {
            System.out.println(trainer);
        }


    }

}
