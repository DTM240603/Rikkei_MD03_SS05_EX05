package re.edu.hw.ss05.ex05.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.hw.ss05.ex05.dto.ApiResponse;
import re.edu.hw.ss05.ex05.dto.InstructorDto.InstructorCreateRequest;
import re.edu.hw.ss05.ex05.dto.InstructorDto.InstructorResponse;
import re.edu.hw.ss05.ex05.dto.InstructorDto.InstructorUpdateRequest;
import re.edu.hw.ss05.ex05.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InstructorResponse>>> getAllInstructors() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Lấy giảng viên thành công",
                        instructorService.getAllInstructors()
                ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createInstructor(@RequestBody InstructorCreateRequest req) {
        instructorService.createInstructor(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        "Thêm giảng viên thành công",
                        null
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateInstructor(@PathVariable Long id, @RequestBody InstructorUpdateRequest req) {
        instructorService.updateInstructor(id, req);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Cập nhật giảng viên thành công",
                        null
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>(
                        HttpStatus.NO_CONTENT.value(),
                        "Xóa giảng viên thành công",
                        null
                ));
    }
}
