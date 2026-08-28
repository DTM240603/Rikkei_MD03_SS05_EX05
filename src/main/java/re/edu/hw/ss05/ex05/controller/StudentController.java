package re.edu.hw.ss05.ex05.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.hw.ss05.ex05.dto.*;
import re.edu.hw.ss05.ex05.dto.StudentDto.StudentCreateRequest;
import re.edu.hw.ss05.ex05.dto.StudentDto.StudentResponse;
import re.edu.hw.ss05.ex05.dto.StudentDto.StudentUpdateRequest;
import re.edu.hw.ss05.ex05.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Lấy sinh viên thành công",
                        studentService.getAllStudent()
                ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createStudent(@RequestBody StudentCreateRequest req) {
        studentService.createStudent(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        "Thêm sinh viên thành công",
                        null
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateStudent(@PathVariable Long id, @RequestBody StudentUpdateRequest req) {
        studentService.updateStudent(id, req);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Cập nhật sinh viên thành công",
                        null
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>(
                        HttpStatus.NO_CONTENT.value(),
                        "Xóa sinh viên thành công",
                        null
                ));
    }
}
