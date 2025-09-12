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
import java.util.NoSuchElementException;
import java.util.Optional;

public class TrainerTests {

    private final TrainerMapper trainerMapper = Mockito.mock(TrainerMapper.class);
    private final TrainerRepository mockRepository = Mockito.mock(TrainerRepository.class);
    private TrainerService sut = new TrainerService(mockRepository,trainerMapper);


    private static List<Trainer> trainers = new ArrayList<>();
    private static Trainer trainer1 = new Trainer();
    private static Trainer trainer2 = new Trainer();
    private static Trainer nullTrainer = null;
    private static TrainerDTO trainerDTO1 = new TrainerDTO();
    private static Integer courseID = 1;

    @BeforeAll
    public static void setup(){
        List<Trainee> dummyTrainees = new  ArrayList<>();
        List<TraineeDTO> dummyTraineesDTO = new  ArrayList<>();


        trainer1.setId(1);
        trainer1.setFullName("Michael");
        trainer1.setCourse(courseID);
        trainers.add(trainer1);

        trainer2.setId(2);
        trainer2.setFullName("Priya");
        trainer2.setCourse(courseID);
        trainers.add(trainer2);

        trainerDTO1.setId(1);
        trainerDTO1.setFullName("Michael");
        trainerDTO1.setCourse(courseID);

        System.out.println(trainer1.getId());
        System.out.println(trainer2.getId());

    }

    @Test
    @DisplayName("Ensure TrainerService is constructed correctly")
    public void constructServiceTest(){
        Assertions.assertInstanceOf(TrainerService.class, sut);
    }

    @Test
    @DisplayName("Constructor should throw exception with null repository")
    public void constructorWtihNullRepositoryTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new TrainerService(null, trainerMapper));
    }

    @Test
    @DisplayName("Get all trainers test")
    public void getAllTrainersTest(){
        //// Define mock beahviour - create the STUB
        Mockito.when(mockRepository.findAll()).thenReturn(trainers);
        Mockito.when(trainerMapper.toEntity(Mockito.any())).thenReturn(trainer1, trainer2);

        // Act
        List<Trainer> result = sut.getAllTrainers().stream().map(trainerMapper::toEntity).toList();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(result.get(0).getId(), trainer1.getId());
        Assertions.assertEquals(result.get(1).getId(), trainer2.getId());
    }

    @Test
    @DisplayName("Get Trainer Happy Path")
    public void getTrainerHappyPath(){

        // Arrange
        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(trainer1));
        Mockito.when(trainerMapper.toEntity(Mockito.any())).thenReturn(trainer1);

        // Act

        Trainer result = trainerMapper.toEntity(sut.getTrainerByID(1));

        Assertions.assertNotNull(result, "Trainer should not be null");
        Assertions.assertEquals(1, result.getId(), "Trainer ID should match");

    }

    @Test
    @DisplayName("Get Trainer Unhappy Path")
    public void getTrainerUnhappyPath(){
        Mockito.when(mockRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> sut.getTrainerByID(67));
    }

    @Test
    @DisplayName("Save trainer happy path")
    public void saveTrainerHappyPath(){
        Mockito.when(trainerMapper.toEntity(Mockito.any())).thenReturn(trainer1);
        Mockito.when(trainerMapper.toDTO(Mockito.any())).thenReturn(trainerDTO1);
        Mockito.when(mockRepository.save(trainer1)).thenReturn(trainer1);

        Trainer result = trainerMapper.toEntity(sut.saveTrainer(trainerMapper.toDTO(trainer1)));
        Assertions.assertNotNull(result, "Trainer should not be null");
        Assertions.assertEquals(trainer1.getId(), result.getId(), "Trainer ID should match");
        Mockito.verify(mockRepository, Mockito.times(1)).save(trainer1);
    }

    @Test
    @DisplayName("Save trainer sad path")
    public void saveTrainerSadPath(){
        Mockito.when(mockRepository.save(nullTrainer)).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> sut.saveTrainer(trainerMapper.toDTO(nullTrainer)));
        Mockito.verify(mockRepository, Mockito.never()).save(trainer1);
    }

    @Test
    @DisplayName("Delete trainer happy path")
    public void deleteTrainerHappyPath(){
        Trainer delTrainer = new Trainer();
        delTrainer.setId(3);

        Mockito.when(mockRepository.existsById(3)).thenReturn(true);

        boolean result = sut.deleteTrainer(3);

        Assertions.assertTrue(result, "The trainer should be deleted successfully");
        Mockito.verify(mockRepository, Mockito.times(1)).deleteById(3);
    }

    @Test
    @DisplayName("Delete trainer sad path")
    public void deleteTrainerSadPath(){
        Mockito.when(mockRepository.existsById(Mockito.anyInt())).thenReturn(false);
        Assertions.assertFalse(sut.deleteTrainer(89));
        Mockito.verify(mockRepository, Mockito.never()).deleteById(89);
    }

    @Test
    @DisplayName("Update Trainer Happy Path")
    public void updateTrainerHappyPath() {

        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(trainer1));
        Mockito.when(mockRepository.save(trainer1)).thenReturn(trainer1);
        Mockito.when(trainerMapper.toDTO(Mockito.any())).thenReturn(trainerDTO1);

        TrainerDTO updatedTrainer = sut.updateTrainer(trainerMapper.toDTO(trainer1));

        Assertions.assertNotNull(updatedTrainer, "Updated trainer should not be null");
        Assertions.assertEquals(1, updatedTrainer.getId(), "Trainer ID should match");
        Mockito.verify(mockRepository, Mockito.times(1)).save(trainer1);

    }

    @Test
    @DisplayName("Update trainer sad path")
    public void updateTrainerSadPath() {

        Mockito.when(mockRepository.existsById(40)).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class, () -> sut.updateTrainer(trainerDTO1),
                "Expected IllegalArgumentException when updating a non-existent customer");
        Mockito.verify(mockRepository, Mockito.never()).save(trainer1);
    }

    }
