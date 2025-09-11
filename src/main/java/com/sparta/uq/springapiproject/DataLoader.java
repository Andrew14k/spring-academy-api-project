package com.sparta.uq.springapiproject;




import com.sparta.uq.springapiproject.entities.Course;
import com.sparta.uq.springapiproject.entities.Trainee;
import com.sparta.uq.springapiproject.entities.Trainer;
import com.sparta.uq.springapiproject.repositories.CourseRepository;
import com.sparta.uq.springapiproject.repositories.TraineeRepository;
import com.sparta.uq.springapiproject.repositories.TrainerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    @Transactional
    public CommandLineRunner loadData(TraineeRepository traineeRepository, TrainerRepository trainerRepository, CourseRepository courseRepository) {
        return args -> {
            System.out.println("DataLoader running...");
            if (traineeRepository.count() == 0) {
                var sleve = new Trainee("Sleve Mcdicheal");
                var willie = new Trainee("Willie Dustice");
                var jeromy = new Trainee("Jeremy Dowell");
                var dean = new Trainee("Dean Wesrey");

                List<Trainee> javaTrainees = new ArrayList<Trainee>();
                javaTrainees.add(sleve);
                javaTrainees.add(willie);
                var java = new Course("Java 101", javaTrainees);
                courseRepository.save(java);

                sleve.setCourse_id(java.getId());
                willie.setCourse_id(java.getId());
                traineeRepository.save(sleve);
                traineeRepository.save(willie);

                List<Trainee> devopsTrainees = new ArrayList<Trainee>();
                devopsTrainees.add(jeromy);
                javaTrainees.add(dean);
                var devops = new Course("DevOps 101", devopsTrainees);
                courseRepository.save(devops);

                jeromy.setCourse_id(devops.getId());
                dean.setCourse_id(devops.getId());
                traineeRepository.save(jeromy);
                traineeRepository.save(dean);

                List<Course> andrewCourses = new ArrayList<>();
                andrewCourses.add(java);
                trainerRepository.save(new Trainer("Andrew", andrewCourses));


                System.out.println("Seed data added");
            } else {
                System.out.println("Seed skipped");
            }
        };
    }
}