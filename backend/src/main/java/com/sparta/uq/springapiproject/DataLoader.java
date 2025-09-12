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
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    @Transactional
    public CommandLineRunner loadData(TraineeRepository traineeRepository, TrainerRepository trainerRepository, CourseRepository courseRepository) {
        return args -> {
            System.out.println("DataLoader running...");
            if (traineeRepository.count() == 0) {
                // --- Trainees ---
                var sleve   = new Trainee("Sleve McDichael");
                var willie  = new Trainee("Willie Dustice");
                var jeromy  = new Trainee("Jeremy Dowell");
                var dean    = new Trainee("Dean Wesrey");
                var todd    = new Trainee("Todd Bonzalez");
                var mike    = new Trainee("Mike Truck");
                var bob     = new Trainee("Bobson Dugnutt");
                var jimmy   = new Trainee("Jimmy Hatt");
                var daniel  = new Trainee("Daniel Mortensen");
                var frank   = new Trainee("Frank Wobama");
                var peter   = new Trainee("Peter Gozinya");
                var rick    = new Trainee("Rick Astley");
                var larry   = new Trainee("Larry Tupper");
                var sam     = new Trainee("Sam Saran");
                var kevin   = new Trainee("Kevin Bantam");
                var emily   = new Trainee("Emily Roberts");
                var sarah   = new Trainee("Sarah Connor");
                var jessica = new Trainee("Jessica White");
                var raj     = new Trainee("Rajesh Kumar");
                var lin     = new Trainee("Lin Wei");

// --- Courses ---
                List<Trainee> javaTrainees = Arrays.asList(sleve, willie, todd, mike);
                var java = new Course("Java 101", javaTrainees);
                courseRepository.save(java);
                javaTrainees.forEach(t -> {
                    t.setCourse_id(java.getId());
                    traineeRepository.save(t);
                });

                List<Trainee> devopsTrainees = Arrays.asList(jeromy, dean, bob, jimmy);
                var devops = new Course("DevOps 101", devopsTrainees);
                courseRepository.save(devops);
                devopsTrainees.forEach(t -> {
                    t.setCourse_id(devops.getId());
                    traineeRepository.save(t);
                });

                List<Trainee> webTrainees = Arrays.asList(daniel, frank, peter, rick);
                var web = new Course("Web Development 101", webTrainees);
                courseRepository.save(web);
                webTrainees.forEach(t -> {
                    t.setCourse_id(web.getId());
                    traineeRepository.save(t);
                });

                List<Trainee> dataTrainees = Arrays.asList(larry, sam, kevin, emily);
                var data = new Course("Data Science 101", dataTrainees);
                courseRepository.save(data);
                dataTrainees.forEach(t -> {
                    t.setCourse_id(data.getId());
                    traineeRepository.save(t);
                });

                List<Trainee> aiTrainees = Arrays.asList(sarah, jessica, raj, lin);
                var ai = new Course("AI & Machine Learning 101", aiTrainees);
                courseRepository.save(ai);
                aiTrainees.forEach(t -> {
                    t.setCourse_id(ai.getId());
                    traineeRepository.save(t);
                });

// --- Trainers ---
                List<Course> andrewCourses = Arrays.asList(java);
                trainerRepository.save(new Trainer("Andrew", java.getId()));

                List<Course> sophiaCourses = Arrays.asList(devops);
                trainerRepository.save(new Trainer("Sophia", devops.getId()));

                List<Course> michaelCourses = Arrays.asList(web);
                trainerRepository.save(new Trainer("Michael", web.getId()));

                List<Course> priyaCourses = Arrays.asList(data);
                trainerRepository.save(new Trainer("Priya", data.getId()));

                List<Course> davidCourses = Arrays.asList(ai);
                trainerRepository.save(new Trainer("David", ai.getId()));

                System.out.println("Seed data added");
            } else {
                System.out.println("Seed skipped");
            }
        };
    }
}