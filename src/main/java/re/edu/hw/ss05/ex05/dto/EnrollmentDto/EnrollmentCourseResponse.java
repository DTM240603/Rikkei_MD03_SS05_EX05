package re.edu.hw.ss05.ex05.dto.EnrollmentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.hw.ss05.ex05.entity.CourseStatus;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrollmentCourseResponse {
    private Long id;
    private String title;
    private CourseStatus status;
}
