package re.edu.hw.ss05.ex05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import re.edu.hw.ss05.ex05.dto.InstructorDto.InstructorCourseResponse;
import re.edu.hw.ss05.ex05.dto.InstructorDto.InstructorCreateRequest;
import re.edu.hw.ss05.ex05.dto.InstructorDto.InstructorResponse;
import re.edu.hw.ss05.ex05.dto.InstructorDto.InstructorUpdateRequest;
import re.edu.hw.ss05.ex05.entity.Instructor;
import re.edu.hw.ss05.ex05.repository.CourseRepository;
import re.edu.hw.ss05.ex05.repository.InstructorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    public List<InstructorResponse> getAllInstructors() {
        return instructorRepository.findAll().stream()
                .map(i -> new InstructorResponse(
                        i.getId(),
                        i.getName(),
                        i.getEmail(),
                        i.getCourses().stream().map(
                                c -> new InstructorCourseResponse(
                                        c.getId(),
                                        c.getTitle(),
                                        c.getStatus()
                                )
                        ).toList())
                ).toList();
    }

    public Instructor findInstructorById(Long id) {
        return instructorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Không tìm thấy giảng viên với id = " + id
        ));
    }

    public void createInstructor(InstructorCreateRequest req) {
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());
        instructorRepository.save(instructor);
    }

    public void updateInstructor(Long id, InstructorUpdateRequest req) {
        Instructor instructor = findInstructorById(id);

        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());

        instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id) {
        Instructor instructor = findInstructorById(id);
        instructorRepository.delete(instructor);
    }

}
