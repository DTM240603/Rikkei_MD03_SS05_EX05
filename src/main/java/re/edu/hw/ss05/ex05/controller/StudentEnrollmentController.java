package re.edu.hw.ss05.ex05.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.hw.ss05.ex05.dto.ApiResponse;
import re.edu.hw.ss05.ex05.dto.EnrollmentDto.EnrollmentCreateRequest;
import re.edu.hw.ss05.ex05.dto.EnrollmentDto.EnrollmentResponse;
import re.edu.hw.ss05.ex05.service.StudentEnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class StudentEnrollmentController {
    private final StudentEnrollmentService studentEnrollmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EnrollmentResponse>>> getAllStudentEnrollments() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Lấy đăng ký khóa học thành công",
                        studentEnrollmentService.getAllStudentEnrollments()
                ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> enrollStudent(@RequestBody EnrollmentCreateRequest req) {
        studentEnrollmentService.enrollStudent(req);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        "Đăng ký khóa học thành công",
                        null
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEnrollStudent(@PathVariable Long id) {
        studentEnrollmentService.deleteEnrollStudent(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>(
                        HttpStatus.NO_CONTENT.value(),
                        "Xóa đăng ký khóa học thành công",
                        null
                ));
    }

}
