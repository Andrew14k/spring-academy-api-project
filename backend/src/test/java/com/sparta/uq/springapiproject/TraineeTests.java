package com.sparta.uq.springapiproject;

import com.sparta.uq.springapiproject.dtos.CourseDTO;
import com.sparta.uq.springapiproject.dtos.TraineeDTO;
import com.sparta.uq.springapiproject.dtos.TraineeMapper;
import com.sparta.uq.springapiproject.dtos.TrainerDTO;
import com.sparta.uq.springapiproject.entities.Course;
import com.sparta.uq.springapiproject.entities.Trainee;
import com.sparta.uq.springapiproject.entities.Trainer;
import com.sparta.uq.springapiproject.repositories.TraineeRepository;
import com.sparta.uq.springapiproject.services.TraineeService;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TraineeTests {

    private final TraineeMapper traineeMapper = Mockito.mock(TraineeMapper.class);
    private final TraineeRepository mockRepo = Mockito.mock(TraineeRepository.class);
    private TraineeService sut = new TraineeService(mockRepo, traineeMapper);


    private static List<Trainee> trainees = new ArrayList<>();
    private static Trainee trainee1 = new Trainee();
    private static Trainee trainee2 = new Trainee();
    private static Trainee nullTrainee = null;
    private static TraineeDTO traineeDTO1 = new TraineeDTO();
    private static TraineeDTO traineeDTO2 = new TraineeDTO();

    @BeforeEach
    public void setUp()
    {
        List<Trainee> dummyTrainees = new  ArrayList<>();
        List<TraineeDTO> dummyTraineesDTO = new  ArrayList<>();

        trainee1.setId(1);
        trainee1.setFullName("Abigail");
        trainee1.setCourse_id(1);
        trainees.add(trainee1);

        trainee2.setId(2);
        trainee2.setFullName("Carroll");
        trainee2.setCourse_id(1);
        trainees.add(trainee2);

        traineeDTO1.setId(1);
        traineeDTO1.setFullName("Abigail");
        traineeDTO1.setCourse_id(1);

        traineeDTO2.setId(2);
        traineeDTO2.setFullName("Carroll");
        traineeDTO2.setCourse_id(1);

        System.out.println("Finished Setup");
    }


    @Test
    @DisplayName("Get all Trainees Test")
    public void GetAllTraineesTest()
    {
        List<Trainee> traineeList = new ArrayList<>();
        Trainee trainee1 = new Trainee();
        Trainee trainee2 = new Trainee();
        trainee1.setId(1);
        trainee2.setId(2);
        traineeList.add(trainee1);
        traineeList.add(trainee2);

        TraineeDTO dto1 = new TraineeDTO();
        TraineeDTO dto2 = new TraineeDTO();

        dto1.setId(1);
        dto2.setId(2);

        Mockito.when(traineeMapper.toDTO(trainee1)).thenReturn(dto1);
        Mockito.when(traineeMapper.toDTO(trainee2)).thenReturn(dto2);

        Mockito.when(mockRepo.findAll()).thenReturn(traineeList);

        //act
        List<TraineeDTO> result = sut.getAllTrainees();

        //assert
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertEquals(result.get(0).getId(), 1);
        Assertions.assertEquals(result.get(1).getId(), 2);


    }

    @Test
    @DisplayName("Get Trainee Happy Path")
    public void GetTraineeHappyTest()
    {
        Trainee trainee = new Trainee("Abigail Carroll", 1, 5);
        TraineeDTO dto = new TraineeDTO();
        dto.setFullName("Abigail Carroll");
        //dto.setFullName("Abigail Carroll");
        Mockito.when(traineeMapper.toDTO(trainee)).thenReturn(dto);

        Mockito.when(mockRepo.findById(1)).thenReturn(Optional.of(trainee));


        TraineeDTO result = sut.getTraineeById(1);

        Assertions.assertNotNull(result, "Should not be null");
        Assertions.assertEquals("Abigail Carroll", result.getFullName());

    }

    @Test
    @DisplayName("Get Trainee Sad Path")
    public void GetTraineeSadTest()
    {
        Mockito.when(mockRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> sut.getTraineeById(1));
    }

    @Test
    @DisplayName("Update Trainee Happy Path")
    public void updateTraineeHappyTest()
    {
        Mockito.when(traineeMapper.toEntity(Mockito.any())).thenReturn(trainee1);
        Mockito.when(traineeMapper.toDTO(Mockito.any())).thenReturn(traineeDTO1);
        Mockito.when(mockRepo.save(trainee1)).thenReturn(trainee1);
        Mockito.when(mockRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(trainee1));

        Trainee result = traineeMapper.toEntity(sut.updateTrainee(traineeMapper.toDTO(trainee1)));
        Assertions.assertNotNull(result, "Should not be null");
        Assertions.assertEquals(trainee1.getFullName(), result.getFullName());
        Mockito.verify(mockRepo, Mockito.times(1)).save(trainee1);

    }

    @Test
    @DisplayName("Update Trainee Sad Path")
    public void updateTraineeSadTest()
    {
        //Mockito.when(mockRepo.save(nullTrainee)).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(NullPointerException.class, () -> sut.updateTrainee(traineeMapper.toDTO(nullTrainee)));
        Mockito.verify(mockRepo, Mockito.never()).save(trainee1);
    }

    @Test
    @DisplayName("Delete trainee happy path")
    public void deleteTraineeHappyTest()
    {
        Mockito.when(mockRepo.existsById(1)).thenReturn(true);

        boolean result = sut.deleteTrainee(1);

        Assertions.assertTrue(result, "Should be true");
        Mockito.verify(mockRepo, Mockito.times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Delete trainee sad path")
    public void deleteTraineeSadTest()
    {
        Mockito.when(mockRepo.existsById(100)).thenReturn(false);

        boolean result = sut.deleteTrainee(100);

        Assertions.assertFalse(result, "Should be false");
    }
}
