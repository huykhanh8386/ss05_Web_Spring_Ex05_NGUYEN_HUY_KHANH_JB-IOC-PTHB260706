package com.example.exsession05.Models.Services;

import com.example.exsession05.Models.DTO.CourseInstructorResponse;
import com.example.exsession05.Models.DTO.CourseResponse;
import com.example.exsession05.Models.Entities.Course;
import com.example.exsession05.Models.Entities.Intructor;
import com.example.exsession05.Models.Repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseServices {

    private final CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public Page<CourseResponse> getPagedCourses(
            int page,
            int size,
            String sortBy,
            Sort.Direction direction
    ) {
        if (page < 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 10;
        }
        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }
        if (!Set.of("id", "title", "status").contains(sortBy)) {
            sortBy = "id";
        }
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Course> coursePage = courseRepository.findAll(pageable);
        return coursePage.map(course -> {
            Intructor instructor = course.getInstructor();
            CourseInstructorResponse instructorResponse = null;
            if (instructor != null) {
                instructorResponse = new CourseInstructorResponse(
                        instructor.getId(),
                        instructor.getName()
                );
            }
            return new CourseResponse(
                    course.getId(),
                    course.getTitle(),
                    course.getStatus(),
                    instructorResponse
            );
        });
    }
}