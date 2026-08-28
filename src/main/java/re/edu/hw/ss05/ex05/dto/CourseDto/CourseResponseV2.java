package re.edu.hw.ss05.ex05.dto.CourseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.hw.ss05.ex05.entity.CourseStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
}
