package re.edu.hw.ss05.ex05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import re.edu.hw.ss05.ex05.dto.StudentDto.StudentCreateRequest;
import re.edu.hw.ss05.ex05.dto.StudentDto.StudentEnrollmentResponse;
import re.edu.hw.ss05.ex05.dto.StudentDto.StudentResponse;
import re.edu.hw.ss05.ex05.dto.StudentDto.StudentUpdateRequest;
import re.edu.hw.ss05.ex05.entity.Student;
import re.edu.hw.ss05.ex05.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentResponse> getAllStudent() {
        return studentRepository.findAll().stream().map(
            s -> new StudentResponse(
                    s.getId(),
                    s.getName(),
                    s.getEmail(),
                    s.getStudentEnrollments().stream().map(
                            e -> new StudentEnrollmentResponse(
                                    e.getId(),
                                    e.getCourse().getId(),
                                    e.getCourse().getTitle(),
                                    e.getCourse().getStatus()
                            )
                    ).toList()
            )
        ).toList();
    }



    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Không tìm thấy sinh viên với id = " + id
        ));
    }

    public void createStudent(StudentCreateRequest req) {
        Student student = new Student();
        student.setName(req.getName());
        student.setEmail(req.getEmail());
        studentRepository.save(student);
    }

    public void updateStudent(Long id, StudentUpdateRequest req) {
        Student student = findStudentById(id);

        student.setName(req.getName());
        student.setEmail(req.getEmail());

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = findStudentById(id);
        studentRepository.delete(student);
    }


}
