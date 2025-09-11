package com.sparta.uq.springapiproject.repositories;

import com.sparta.uq.springapiproject.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}