package com.sparta.uq.springapiproject.services;

import com.sparta.uq.springapiproject.dtos.CourseDTO;
import com.sparta.uq.springapiproject.dtos.CourseMapper;
import com.sparta.uq.springapiproject.entities.Course;
import com.sparta.uq.springapiproject.repositories.CourseRepository;
import com.sparta.uq.springapiproject.dtos.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        if (courseRepository == null) {
            throw new NullPointerException("Course Repository cannot be null");
        }
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(courseMapper::toDTO).toList();
    }

    public CourseDTO getCourseById(int course_id) {
        Course course = this.courseRepository.findById(course_id).orElse(null);
        return courseMapper.toDTO(course);
    }

    public CourseDTO saveCourse(CourseDTO courseDTO) {
        if (courseDTO == null) {
            throw new NullPointerException("Course DTO cannot be null");
        }
        Course course = this.courseMapper.toEntity(courseDTO);
        Course savedCourse = this.courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }

    public boolean deleteCourseById(int course_id) {
        if (this.courseRepository.existsById(course_id)) {
            this.courseRepository.deleteById(course_id);
            return true;
        }
        return false;
    }

//    public CourseDTO updateCourseById(int course_id, CourseDTO courseDTO) {
//        if (courseDTO == null || !courseRepository.existsById(course_id)) {
//            throw new NoSuchElementException("Course not found");
//        }
//        Course course = courseMapper.toEntity(courseDTO);
//        Course updatedCourse = courseRepository.save(course);
//        return courseMapper.toDTO(updatedCourse);
//    }
}
