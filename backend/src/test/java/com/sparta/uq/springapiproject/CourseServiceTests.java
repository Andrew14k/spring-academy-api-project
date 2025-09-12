package com.sparta.uq.springapiproject;

import com.sparta.uq.springapiproject.dtos.CourseDTO;
import com.sparta.uq.springapiproject.dtos.CourseMapper;
import com.sparta.uq.springapiproject.entities.Course;
import com.sparta.uq.springapiproject.repositories.CourseRepository;
import com.sparta.uq.springapiproject.services.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseServiceTests {

    private final CourseMapper courseMapper = Mockito.mock(CourseMapper.class);
    private final CourseRepository mockRepository = Mockito.mock(CourseRepository.class);
    private final CourseService sut = new CourseService(mockRepository, courseMapper);

    private static List<Course> courses = new ArrayList<>();
    private static Course course1 = new Course();
    private static Course course2 = new Course();
    private static Course course3 = new Course();
    private static Course nullCourse = null;

    private static CourseDTO courseDTO = new CourseDTO();

    @BeforeAll
    public static void setup() {
        course1.setId(1);
        course1.setTitle("Automation Engineering");

        course2.setId(2);
        course2.setTitle("DevOps");

        course3.setId(3);
        course3.setTitle("Full-Stack Development");

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        courseDTO.setId(1);
        courseDTO.setTitle("Automation Engineering");
    }

    @Test
    @DisplayName("Ensure CourseService is constructed correctly")
    public void constructServiceTest() {
        Assertions.assertInstanceOf(CourseService.class, sut);
    }

    @Test
    @DisplayName("Constructor should throw exception with null repository")
    public void constructorWithNullRepositoryTest() {
        Assertions.assertThrows(NullPointerException.class, () -> new CourseService(null, courseMapper));
    }

    @Test
    @DisplayName("Get all courses test")
    public void getAllCoursesTest() {
        // Arrange

        //// Define mock beahviour - create the STUB
        Mockito.when(mockRepository.findAll()).thenReturn(courses);
        Mockito.when(courseMapper.toEntity(Mockito.any())).thenReturn(course1, course2, course3);


        // Act
        List<Course> result = sut.getAllCourses().stream().map(courseMapper::toEntity).toList();

        // Assert
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(result.get(0).getId(), 1);
        Assertions.assertEquals(result.get(1).getId(), 2);
        Assertions.assertEquals(result.get(2).getId(), 3);

    }


    @Test
    @DisplayName("Get Course Happy Path")
    public void getCourseTest() {

        // Arrange
        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(course1));
        Mockito.when(courseMapper.toEntity(Mockito.any())).thenReturn(course1);

        // Act

        Course result = courseMapper.toEntity(sut.getCourseById(1));

        Assertions.assertNotNull(result, "Course should not be null");
        Assertions.assertEquals(1, result.getId(), "Course ID should match");

    }


    @Test
    @DisplayName("Get Course Unhappy Path")
    public void findCourseUnhappyPathTests(){
        Mockito.when(mockRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        CourseDTO result = sut.getCourseById(122);
        Assertions.assertNull(result);
    }


    @Test
    @DisplayName("Save course happy path")
    public void saveCustomerTest(){
        Mockito.when(courseMapper.toEntity(Mockito.any())).thenReturn(course1);
        Mockito.when(courseMapper.toDTO(Mockito.any())).thenReturn(courseDTO);
        Mockito.when(mockRepository.save(course1)).thenReturn(course1);

        Course result = courseMapper.toEntity(sut.saveCourse(courseMapper.toDTO(course1)));
        Assertions.assertNotNull(result, "Course should not be null");
        Assertions.assertEquals(course1.getId(), result.getId(), "Course ID should match");
        Mockito.verify(mockRepository, Mockito.times(1)).save(course1);
    }

    @Test
    @DisplayName("Save course sad path")
    public void saveCustomerSadPathTest(){
        Mockito.when(mockRepository.save(null)).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(NullPointerException.class, () -> sut.saveCourse(courseMapper.toDTO(nullCourse)));
    }

    @Test
    @DisplayName("Delete course happy path")
    public void deleteCourseTest() {
        // Arrange
        Course delCourse = new Course();
        delCourse.setId(10);
        Mockito.when(mockRepository.findById(10)).thenReturn(Optional.of(delCourse));
        Mockito.when(courseMapper.toEntity(Mockito.any())).thenReturn(delCourse);
        Mockito.when(mockRepository.existsById(10)).thenReturn(true);

        // Act
        Course result = courseMapper.toEntity(sut.getCourseById(10));

        // Assert before delete
        Assertions.assertNotNull(result, "Course should not be null");
        Assertions.assertEquals(10, result.getId(), "Course ID should match");

        // Act: delete the course
        boolean deleted = sut.deleteCourseById(10);

        // Assert deletion was successful
        Assertions.assertTrue(deleted, "The course should be deleted successfully");
        Mockito.verify(mockRepository).deleteById(10);

        // Simulate repository state after deletion
        Mockito.when(mockRepository.findById(10)).thenReturn(Optional.empty());
        Mockito.when(mockRepository.existsById(10)).thenReturn(false);

        // Assert that course no longer exists
        Assertions.assertNull(sut.getCourseById(10), "Deleted course should return null");
    }


    @Test
    @DisplayName("Delete Course Sad Path")
    public void deleteCourseSadPathTest() {
        Mockito.when(mockRepository.existsById(222)).thenReturn(false);

        boolean result = sut.deleteCourseById(222);

        Assertions.assertFalse(result, "The course should not be deleted as it does not exist");
        Mockito.verify(mockRepository, Mockito.never()).deleteById(222);
    }

    @Test
    @DisplayName("Save Course — Happy Path")
    void saveCourseHappyPathTest() {
        Mockito.when(courseMapper.toEntity(courseDTO)).thenReturn(course1);
        Mockito.when(mockRepository.save(course1)).thenReturn(course1);
        Mockito.when(courseMapper.toDTO(course1)).thenReturn(courseDTO);

        CourseDTO savedCourse = sut.saveCourse(courseDTO);

        Assertions.assertNotNull(savedCourse);
        Assertions.assertEquals(1, savedCourse.getId());

        Mockito.verify(courseMapper).toEntity(courseDTO);
        Mockito.verify(mockRepository).save(course1);
        Mockito.verify(courseMapper).toDTO(course1);
        Mockito.verifyNoMoreInteractions(mockRepository, courseMapper);
    }


    @Test
    @DisplayName("Update course Unhappy Path")
    public void updateCourseUnhappyPathTest() {

        Mockito.when(mockRepository.existsById(1)).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class, () -> sut.updateCourse(courseDTO),
                "Expected IllegalArgumentException when updating a non-existent customer");
    }

}