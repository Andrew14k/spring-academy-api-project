package com.sparta.uq.springapiproject.dtos;

import com.sparta.uq.springapiproject.entities.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDTO(Course course);
    Course toEntity(CourseDTO courseDTO);
}
