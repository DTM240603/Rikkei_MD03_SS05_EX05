package re.edu.hw.ss05.ex05.dto.EnrollmentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrollmentResponse {
    private Long id;
    private EnrollmentCourseResponse course;
    private EnrollmentStudentResponse student;
}
