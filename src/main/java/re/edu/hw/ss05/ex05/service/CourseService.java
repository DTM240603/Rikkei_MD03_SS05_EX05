package re.edu.hw.ss05.ex05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import re.edu.hw.ss05.ex05.dto.CourseDto.*;
import re.edu.hw.ss05.ex05.dto.PageResponse;
import re.edu.hw.ss05.ex05.entity.Course;
import re.edu.hw.ss05.ex05.entity.CourseStatus;
import re.edu.hw.ss05.ex05.entity.Instructor;
import re.edu.hw.ss05.ex05.repository.CourseRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstructorService instructorService;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "title", "status");

    public PageResponse<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction) {
        int safePage = Math.max(page, 0);
        int safeSize = size > 0 ? size : 10;
        String safeSortBy = sortBy != null && ALLOWED_SORT_FIELDS.contains(sortBy) ? sortBy : "id";
        Sort.Direction safeDirection = direction != null ? direction : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(safePage, safeSize, Sort.by(safeDirection, safeSortBy));

        Page<CourseResponse> coursePage = courseRepository.findAll(pageable)
                .map(this::toCourseResponse);

        return new PageResponse<>(
                coursePage.getContent(),
                coursePage.getNumber(),
                coursePage.getSize(),
                Math.toIntExact(coursePage.getTotalElements()),
                coursePage.getTotalPages(),
                coursePage.isLast()
        );
    }

    public PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status) {
        int safePage = Math.max(page, 0);
        int safeSize = size > 0 ? size : 10;
        String safeSortBy = sortBy != null && ALLOWED_SORT_FIELDS.contains(sortBy) ? sortBy : "id";
        Sort.Direction safeDirection = direction != null ? direction : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(safePage, safeSize, Sort.by(safeDirection, safeSortBy));

        Page<CourseResponse> coursePage = courseRepository.findAllByStatus(status, pageable)
                .map(this::toCourseResponse);

        return new PageResponse<>(
                coursePage.getContent(),
                coursePage.getNumber(),
                coursePage.getSize(),
                Math.toIntExact(coursePage.getTotalElements()),
                coursePage.getTotalPages(),
                coursePage.isLast()
        );
    }

    public PageResponse<CourseResponseV2> getPagedCoursesByStatusV2(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status) {
        int safePage = Math.max(page, 0);
        int safeSize = size > 0 ? size : 10;
        String safeSortBy = sortBy != null && ALLOWED_SORT_FIELDS.contains(sortBy) ? sortBy : "id";
        Sort.Direction safeDirection = direction != null ? direction : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(safePage, safeSize, Sort.by(safeDirection, safeSortBy));

        Page<CourseResponseV2> coursePage = courseRepository.findAllByStatusV2(status, pageable);

        return new PageResponse<>(
                coursePage.getContent(),
                coursePage.getNumber(),
                coursePage.getSize(),
                Math.toIntExact(coursePage.getTotalElements()),
                coursePage.getTotalPages(),
                coursePage.isLast()
        );
    }

    private CourseResponse toCourseResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getStatus(),
                new CourseInstructorResponse(
                        course.getInstructor().getId(),
                        course.getInstructor().getName()
                )
        );
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Không tìm thấy khóa học có id = " + id
        ));
    }

    public void createCourse(CourseCreateRequest req) {
        Instructor instructor = instructorService.findInstructorById(req.getInstructorId());

        Course course = new Course();
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    public void updateCourse(Long id, CourseUpdateRequest req) {
        Course existCourse = findCourseById(id);

        Instructor instructor = instructorService.findInstructorById(req.getInstructorId());

        existCourse.setTitle(req.getTitle());
        existCourse.setStatus(req.getStatus());
        existCourse.setInstructor(instructor);

        courseRepository.save(existCourse);
    }

    public void deleteCourse(Long id) {
        Course existCourse = findCourseById(id);

        courseRepository.delete(existCourse);
    }
}
