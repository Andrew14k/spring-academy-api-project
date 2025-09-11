package com.sparta.uq.springapiproject.controllers;


import com.sparta.uq.springapiproject.entities.Course;
import com.sparta.uq.springapiproject.repositories.CourseRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Course>> getAllCourses()
    {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @Operation(summary = "Get course by ID", description = "return the course with the IDD specified in the path variable")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Course> getCourseByID(@PathVariable int id)
    {
        Course course = service.getCourseByID(id);
        if (course == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @Operation(summary = "Adds a course to the database", description = "Creates a new cusstome rin the database with the body data")
    @PostMapping(value = "/")
    public ResponseEntity<Course> addCourse(@RequestBody Course course)
    {
        Course savedCourse = service.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    @Operation(summary = "Updates a course already present in the database", description = "Updates a course, with id provided in the path. fails if the object is not present")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable int id)
    {
        //
    }

    @Operation(summary = "Delete a course", description = "Ddeletes a course specificed by ID")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id)
    {
        if (service.deleteCourseByID())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    /*
    @Operation(summary = "", description = "")
    @GetMapping(value = "/")
    */
}
