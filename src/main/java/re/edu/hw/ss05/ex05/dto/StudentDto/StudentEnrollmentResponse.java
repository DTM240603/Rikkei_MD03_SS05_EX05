package re.edu.hw.ss05.ex05.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.hw.ss05.ex05.entity.CourseStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentEnrollmentResponse {
    private Long id;       // id của enrollment
    private Long courseId;
    private String courseTitle;
    private CourseStatus courseStatus;
}
