package com.example.exsession05.Controller;
import com.example.exsession05.Models.DTO.ApiResponse;
import com.example.exsession05.Models.DTO.CourseResponse;
import com.example.exsession05.Models.Services.CourseServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v2/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseServices courseServices;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CourseResponse>>> getPagedCourses(

            @RequestParam(name = "page", defaultValue = "0")
            int page,

            @RequestParam(name = "size", defaultValue = "10")
            int size,

            @RequestParam(name = "sortBy", required = false)
            String sortBy,

            @RequestParam(name = "direction", defaultValue = "DESC")
            Sort.Direction direction

    ) {

        Page<CourseResponse> courses = courseServices.getPagedCourses(
                page,
                size,
                sortBy,
                direction
        );

        ApiResponse<Page<CourseResponse>> response =
                new ApiResponse<>(
                        true,
                        "Lấy danh sách khóa học thành công",
                        courses
                );
        return ResponseEntity.ok(response);
    }
}