package re.edu.hw.ss05.ex05.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.hw.ss05.ex05.dto.ApiResponse;
import re.edu.hw.ss05.ex05.dto.CourseDto.CourseCreateRequest;
import re.edu.hw.ss05.ex05.dto.CourseDto.CourseResponse;
import re.edu.hw.ss05.ex05.dto.CourseDto.CourseUpdateRequest;
import re.edu.hw.ss05.ex05.dto.PageResponse;
import re.edu.hw.ss05.ex05.entity.CourseStatus;
import re.edu.hw.ss05.ex05.service.CourseService;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseV1Controller {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam CourseStatus status
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Lấy danh sách khóa học thành công",
                        courseService.getPagedCoursesByStatus(page, size, sortBy, direction, status)
                ));
    }



    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createCourse(@RequestBody CourseCreateRequest req) {
        courseService.createCourse(req);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        "Tạo khóa học thành công",
                        null
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> createCourse(@PathVariable Long id, @RequestBody CourseUpdateRequest req) {
        courseService.updateCourse(id, req);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Cập nhật khóa học thành công",
                        null
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>(
                        HttpStatus.NO_CONTENT.value(),
                        "Xóa khóa học thành công",
                        null
                ));
    }
}
