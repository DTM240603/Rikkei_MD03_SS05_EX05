package re.edu.hw.ss05.ex05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import re.edu.hw.ss05.ex05.dto.EnrollmentDto.EnrollmentCourseResponse;
import re.edu.hw.ss05.ex05.dto.EnrollmentDto.EnrollmentCreateRequest;
import re.edu.hw.ss05.ex05.dto.EnrollmentDto.EnrollmentResponse;
import re.edu.hw.ss05.ex05.dto.EnrollmentDto.EnrollmentStudentResponse;
import re.edu.hw.ss05.ex05.entity.Course;
import re.edu.hw.ss05.ex05.entity.Student;
import re.edu.hw.ss05.ex05.entity.StudentEnrollment;
import re.edu.hw.ss05.ex05.repository.StudentEnrollmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentEnrollmentService {
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;


    public List<EnrollmentResponse> getAllStudentEnrollments() {
        return studentEnrollmentRepository.findAll().stream()
                .map(e -> new EnrollmentResponse(
                        e.getId(),
                        new EnrollmentCourseResponse(e.getCourse().getId(), e.getCourse().getTitle(), e.getCourse().getStatus()),
                        new EnrollmentStudentResponse(e.getStudent().getId(), e.getStudent().getName(), e.getStudent().getEmail())
                        )
                ).toList();
    }

    public StudentEnrollment findEnrollmentById(Long id) {
         return studentEnrollmentRepository.findById(id).orElseThrow(
                 () -> new ResponseStatusException(
                         HttpStatus.NOT_FOUND, "Không tìm thấy"
                 )
         );
    }

    public void enrollStudent(EnrollmentCreateRequest req) {
        Student student = studentService.findStudentById(req.getStudentId());

        Course course = courseService.findCourseById(req.getCourseId());

        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        studentEnrollmentRepository.save(enrollment);
    }

    public void deleteEnrollStudent(Long id) {
        StudentEnrollment studentEnrollment = findEnrollmentById(id);

        studentEnrollmentRepository.delete(studentEnrollment);
    }
}
