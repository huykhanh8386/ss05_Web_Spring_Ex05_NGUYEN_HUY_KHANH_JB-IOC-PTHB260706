package com.example.exsession05.Models.DTO;

import com.example.exsession05.Models.Entities.CourseStatus;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {
    private Long id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;
}