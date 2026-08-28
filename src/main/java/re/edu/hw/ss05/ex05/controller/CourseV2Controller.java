package re.edu.hw.ss05.ex05.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import re.edu.hw.ss05.ex05.dto.ApiResponse;
import re.edu.hw.ss05.ex05.dto.CourseDto.CourseResponseV2;
import re.edu.hw.ss05.ex05.dto.PageResponse;
import re.edu.hw.ss05.ex05.entity.CourseStatus;
import re.edu.hw.ss05.ex05.service.CourseService;

@RestController
@RequestMapping("/api/v2/courses")
@RequiredArgsConstructor
public class CourseV2Controller {
    private final CourseService courseService;

    @GetMapping
    @RequestMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseV2>>> getAllCoursesV2(
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
                        courseService.getPagedCoursesByStatusV2(page, size, sortBy, direction, status)
                ));
    }
}
