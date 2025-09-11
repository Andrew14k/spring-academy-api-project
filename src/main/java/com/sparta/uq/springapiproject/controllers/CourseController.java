package com.sparta.uq.springapiproject.controllers;


import com.sparta.uq.springapiproject.dtos.CourseDTO;
import com.sparta.uq.springapiproject.entities.Course;
import com.sparta.uq.springapiproject.repositories.CourseRepository;
import com.sparta.uq.springapiproject.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "course-controller", description = "Operations on course table")
@RestController
@RequestMapping("course")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service)
    {
        this.service = service;
    }

    @Operation(summary = "Get all Courses", description = "Returns a list of all courses")
    @GetMapping(value = "/")
    public ResponseEntity<List<CourseDTO>> getAllCourses()
    {
        List<CourseDTO> courses = service.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @Operation(summary = "Get course by ID", description = "return the course with the IDD specified in the path variable")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseDTO> getCourseByID(@PathVariable int id)
    {
        CourseDTO courseDTO = service.getCourseById(id);
        if (courseDTO == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseDTO);
    }

    @Operation(summary = "Adds a course to the database", description = "Creates a new cusstome rin the database with the body data")
    @PostMapping(value = "/")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO)
    {
        courseDTO.setId(null);
        CourseDTO savedCourse = service.saveCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

//    @Operation(summary = "Updates a course already present in the database", description = "Updates a course, with id provided in the path. fails if the object is not present")
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable int id)
//    {
//        //
//    }

    @Operation(summary = "Delete a course", description = "Ddeletes a course specificed by ID")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id)
    {
        service.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }


    /*
    @Operation(summary = "", description = "")
    @GetMapping(value = "/")
    */
}
